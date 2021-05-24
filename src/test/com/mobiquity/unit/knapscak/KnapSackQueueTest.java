package com.mobiquity.unit.knapscak;

import com.mobiquity.helper.DataHelper;
import com.mobiquity.knapscak.KnapSackQueue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class KnapSackQueueTest {
    @Mock
    private KnapSackQueue knapSackQueue;

    @Test
    public void verifyInit() {
        when(knapSackQueue.init(anyList()))
                .thenReturn(any(KnapSackQueue.class));
        knapSackQueue.init(DataHelper.packages());

        verify(knapSackQueue, times(1))
                .init(DataHelper.packages());
    }

    @Test
    public void verifyCalculate() {
        when(knapSackQueue.calculate())
                .thenReturn(null);
        knapSackQueue.calculate();
        verify(knapSackQueue, times(1))
                .calculate();
    }

    @Test
    public void verifyGetCalculationsAsString() {
        when(knapSackQueue.getCalculationsAsString())
                .thenReturn("");
        knapSackQueue.getCalculationsAsString();
        verify(knapSackQueue, times(1))
                .getCalculationsAsString();
    }


    @Test
    public void verifyClear() {
        doNothing().when(knapSackQueue).clear();
        knapSackQueue.clear();
        verify(knapSackQueue, times(1))
                .clear();
    }
}
