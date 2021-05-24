package com.mobiquity.integeration.service.weightcalculator;

import com.mobiquity.service.weightcalculator.WeightCalculationService;
import com.mobiquity.helper.DataHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.mobiquity.helper.Consts.EMPTY_PACKAGE;

@SpringBootTest
public class WeightCalculationServiceTest {

    @Autowired
    private WeightCalculationService calculationService;

    @Test
    public void calculateWeight_validPackages_shouldReturnResult() {
        String result = calculationService.calculateWeight(DataHelper.packagesForWightCal());
        Assertions.assertEquals("4", result);
    }

    @Test
    public void calculateWeight_nullPackages_shouldReturnEmpty() {
        String result = calculationService.calculateWeight(null);
        Assertions.assertEquals("", result);
    }

    @Test
    public void calculateWeight_emptyPackages_shouldReturnEmpty() {
        String result = calculationService.calculateWeight(EMPTY_PACKAGE);
        Assertions.assertEquals("", result);
    }
}
