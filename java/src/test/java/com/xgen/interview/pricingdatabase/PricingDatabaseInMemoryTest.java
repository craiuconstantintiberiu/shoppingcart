package com.xgen.interview.pricingdatabase;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Map;

@RunWith(JUnit4.class)
public class PricingDatabaseInMemoryTest extends TestCase {

    @Test
    public void whenRetrievingItemItsValueIsReturned() {
        PricingDatabaseInMemory pricingDatabase =
                new PricingDatabaseInMemory(Map.of("apple", 100));
        assertEquals(100, pricingDatabase.getPrice("apple"));
    }

    @Test
    public void whenRetrievingItemThatDoesNotExist0IsReturned() {
        PricingDatabaseInMemory pricingDatabase =
                new PricingDatabaseInMemory(Map.of("apple", 100));
        assertEquals(0, pricingDatabase.getPrice("banana"));
    }
}