package com.mobiquity.knapscak;

import com.mobiquity.domain.Package;
import com.mobiquity.domain.PackageCalculation;
import com.mobiquity.domain.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.Collectors;

import static com.mobiquity.helper.Constants.NEW_LINE_CONSTANT;

/**
 * * ========= Thread safety information =======
 *  * <p>
 *  * To make it thread-safe we will use synchronization as we are using Map and list, and
 *  * that will be updated, with requests. we have to make our data synchronize for
 *  * the sake of multiple requests (threads) safety, in the case of database we
 *  * have isolation to prevent our database. KnapSackQueue is a standalone singleton class
 *  * and its not annotated with any spring boot context annotation so it will not be taking
 *  * care by ThreadLocal of Spring framework, we need to take care of that ourself.
 *  * </p>
 *  * <p>
 *  * Synchronization can be acquired on method level (Coarse grain locking
 *  * mechanism) but this will make our method slow as no thread else can enter to
 *  * other methods (in database term we can say highest level of isolation) we
 *  * will use fine grain locking mechanism separately for read mechanism and
 *  * writing mechanism
 *  * </p>
 *  * <p>
 *  * I am using java 1.8 StampedLock @see {@link StampedLock} <br>
 *  * <strong> Reason of using StampedLock is one of its feature optimistic locking
 *  * in this lock as per documentation said, we do not need to apply full-fledged
 *  * read lock every time, some time lock is not held by any write operation, we
 *  * use tryOptimisticRead to check if the lock is hold by write operation and
 *  * then check result with validate method. </strong> <br>
 *  * Java 1.8 StampedLock is much more efficient and fast as compared to
 *  * ReentrantLock specially optimistic locking which make synchronization
 *  * overhead very slow. You can also use ReentrantLock but it very slow as
 *  * compared to new java 1.8 stamped lock
 *  * </p>
 *
 * */
public class KnapSackQueue {

    private static final Logger logger = LoggerFactory.getLogger(KnapSackQueue.class);

    private final StampedLock stampedLock = new StampedLock();
    private final Map<Package, Queue<Product>> packageMaxHeap = new LinkedHashMap<>();
    private final List<PackageCalculation> calculations = new ArrayList<>();

    public KnapSackQueue init(List<Package> packages) {
        long stamp = stampedLock.writeLock();
        logger.debug("Acquire write lock {}", stamp);
        try {
            logger.debug("Initializing max heap with product weight and cost constraints");
            packages.forEach(pkg -> {
                packageMaxHeap.put(pkg, getInitPackageMaxHeap());
                packageMaxHeap.get(pkg).addAll(pkg.getProducts());
            });
        } finally {
            stampedLock.unlockWrite(stamp);
            logger.debug("Unlock write lock {}", stamp);
        }
        return getInstance();
    }

    public KnapSackQueue calculate() {
        long stamp = stampedLock.writeLock();
        logger.debug("Acquire write lock {}", stamp);
        try {
            Set<Package> packageKeys = packageMaxHeap.keySet();
            packageKeys.forEach(key ->
                calculations.add(new PackageCalculation(key.getID(), getProducts(key))));
            logger.debug("Calculated package calculations {}", calculations);
        } finally {
            stampedLock.unlockWrite(stamp);
            logger.debug("Unlock write lock {}", stamp);
        }
        return getInstance();
    }

    private List<Product> getProducts(Package key) {
        logger.debug("Get all valid products against key {}", key.getID());
        double weightLimits = key.getWeightLimit();
        List<Product> products = new ArrayList<>();
        Queue<Product> productQueue = packageMaxHeap.get(key);
        logger.debug("Calculating all products within available weight {}", weightLimits);
        while (!productQueue.isEmpty() && weightLimits > 0) {
            Product product = productQueue.poll();
            if (weightLimits >= product.getWeight()) {
                products.add(product);
                weightLimits -= product.getWeight();
            }
        }
        return products;
    }

    public String getCalculationsAsString() {
        long optimisticRead = stampedLock.tryOptimisticRead();
        logger.debug("Acquire optimistic read lock {}", optimisticRead);
        if (stampedLock.validate(optimisticRead))
            return calculationsAsString();
        optimisticRead = stampedLock.readLock();
        try {
            return calculationsAsString();
        } finally {
            stampedLock.unlockRead(optimisticRead);
            logger.debug("Unlock optimistic read lock {}", optimisticRead);
        }
    }

    private String calculationsAsString() {
        logger.debug("Joining calculation as expected string result");
        return calculations.stream()
                .map(PackageCalculation::toString)
                .collect(Collectors.joining(NEW_LINE_CONSTANT));
    }

    /**
     * This heap will sort element on the base of cost (descending order) as cost have
     * more priority and if there are two products with same cost then we will try
     * to pick up light weight product
     * */
    private Queue<Product> getInitPackageMaxHeap() {
        logger.debug("Setting up customize heap with custom comparator");
        return new PriorityQueue<>((p1, p2) -> {
            int costComparison = Double.compare(p2.getCost(), p1.getCost());
            return (costComparison == 0) ?
                    Double.compare(p1.getWeight(), p2.getWeight()) :
                    costComparison > 0 ? 1 : -1;
        });
    }

    public void clear() {
        long stamp = stampedLock.writeLock();
        logger.debug("Acquire write lock {}", stamp);
        try {
            packageMaxHeap.clear();
            calculations.clear();
            logger.debug("Clear knapStackQueue....");
        } finally {
            stampedLock.unlockWrite(stamp);
            logger.debug("Unlock write lock {}", stamp);
        }
    }

    /* Initialization on demand holder pattern which is used
     * to create a singleton object which is thread-safe,
     * unlike double check locking, its also work fine with
     * multi-processor distributed applications.
     * PS: Its a lazy load algorithm
     * */
    private static class InstanceHolder {
        private static final KnapSackQueue INSTANCE = new KnapSackQueue();

        private InstanceHolder() {

        }
    }

    public static KnapSackQueue getInstance() {
        return KnapSackQueue.InstanceHolder.INSTANCE;
    }

    private KnapSackQueue() {

    }
}
