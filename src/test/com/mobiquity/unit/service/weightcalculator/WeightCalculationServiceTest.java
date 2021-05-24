package com.mobiquity.unit.service.weightcalculator;

import com.mobiquity.service.weightcalculator.WeightCalculationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class WeightCalculationServiceTest {

    @Mock
    private WeightCalculationService service;

    @Test
    public void testCalculateWeight() {
        service.calculateWeight(null);

        verify(service, times(1)).calculateWeight(null);
    }
}
