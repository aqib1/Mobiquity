package com.mobiquity.service.weightcalculator.Impl;

import com.mobiquity.domain.Package;
import com.mobiquity.knapscak.KnapSackQueue;
import com.mobiquity.service.weightcalculator.WeightCalculationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class WeightCalculationServiceImpl implements WeightCalculationService {

    private static final Logger logger = LoggerFactory.getLogger(WeightCalculationServiceImpl.class);

    private final KnapSackQueue knapSackQueue = KnapSackQueue.getInstance();

    @Override
    public String calculateWeight(List<Package> packages) {
        if(Objects.isNull(packages) || packages.isEmpty()) {
            logger.error("Packages received as empty...");
            return "";
        }
        return knapSackQueue.init(packages)
                .calculate()
                .getCalculationsAsString();
    }
}
