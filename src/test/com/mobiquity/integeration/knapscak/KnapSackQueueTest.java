package com.mobiquity.integeration.knapscak;

import com.mobiquity.knapscak.KnapSackQueue;
import com.mobiquity.helper.DataHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KnapSackQueueTest {
    private KnapSackQueue queue = KnapSackQueue.getInstance();

    @Test
    public void testCalculate() {
        String result = queue.init(DataHelper.packagesForWightCal())
                .calculate()
                .getCalculationsAsString();
        Assertions.assertEquals("4", result);
    }

    @BeforeEach
    public void clean() {
        queue.clear();
    }
}
