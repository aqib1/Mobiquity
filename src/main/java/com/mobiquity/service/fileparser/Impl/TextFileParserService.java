package com.mobiquity.service.fileparser.Impl;

import com.mobiquity.domain.Package;
import com.mobiquity.domain.Product;
import com.mobiquity.exception.FileParserException;
import com.mobiquity.service.fileparser.FileParserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.mobiquity.helper.Constants.*;

@Service
public class TextFileParserService implements FileParserService {

    private static final Logger logger = LoggerFactory.getLogger(TextFileParserService.class);

    @Override
    public List<Package> parseFileData(List<String> data) {
        if (Objects.isNull(data) || data.isEmpty()) {
            logger.error("File data is empty...");
           return new ArrayList<>();
        }

        return data.stream()
                .map(this::parsePackage)
                .collect(Collectors.toList());
    }

    private Package parsePackage(String data) {
        String[] weightToProducts = data.split(WEIGHT_SPLIT_DELIMITER);
        if (weightToProducts.length != WEIGHT_PRODUCT_SPLIT_LEN) {
            logger.error("Line must contain exactly one `:` but {}", data);
            throw new FileParserException("Line must contain exactly one `:`");
        }
        try {
            double weight = Double.parseDouble(weightToProducts[0]);
            String[] rawProducts = weightToProducts[1].split(PRODUCTS_SPLIT_PATTERN);
            List<Product> products = Arrays.stream(rawProducts)
                    .map(this::parseProduct)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            if (products.size() > MAX_PRODUCTS_LIMIT) {
                logger.debug("Products limit is exceeding from {}, getting sublist from 0 to {}", MAX_PRODUCTS_LIMIT, MAX_PRODUCTS_LIMIT);
                products = products.subList(0, MAX_PRODUCTS_LIMIT);
            }

            return new Package(weight, products);
        } catch (NumberFormatException e) {
            logger.error("Invalid format defined in file", e);
            throw new FileParserException("Invalid format for weight", e);
        }
    }

    private Product parseProduct(String product) {
        if (product.isBlank()) {
            return null;
        }
        String[] productSplit = product.split(PRODUCT_SPLIT_DELIMITER);
        if (productSplit.length > MAX_PRODUCT_ATTR) {
            logger.error("Products are more than expected: {} products but expectation is {}", productSplit.length, MAX_PRODUCT_ATTR);
            throw new FileParserException("Invalid format for product, have missing attributes");
        }
        try {
            int index = Integer.parseInt(productSplit[0]);
            double weight = Double.parseDouble(productSplit[1]);
            double cost = Double.parseDouble(productSplit[2].substring(1));
            if (weight > MAX_WEIGHT_LIMIT) {
                logger.error("Weight is more than defined limit {}", MAX_WEIGHT_LIMIT);
                return null;
            }

            return new Product(index, weight, cost);
        } catch (NumberFormatException e) {
            logger.error("Invalid format defined in file", e);
            throw new FileParserException("Invalid format for product attribute", e);
        }
    }

}
