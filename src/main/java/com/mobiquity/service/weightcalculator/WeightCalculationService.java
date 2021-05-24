package com.mobiquity.service.weightcalculator;

import com.mobiquity.domain.Package;
import java.util.List;

@FunctionalInterface
public interface WeightCalculationService {
    String calculateWeight(List<Package> packages);
}
