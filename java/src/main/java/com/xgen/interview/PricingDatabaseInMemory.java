package com.xgen.interview;

import java.util.HashMap;


/**
 * A stub implementation - for this exercise, you may disregard that this is incomplete.
 */
public class PricingDatabaseInMemory implements IPricingDatabase {
    HashMap<String, Integer> pricingDatabase = new HashMap<>(); // stub

    public PricingDatabaseInMemory() {
        pricingDatabase.put("apple", 100);
        pricingDatabase.put("banana", 200);
    }

    public Integer getPrice(String itemType) {
        return pricingDatabase.getOrDefault(itemType, 0);
    }

}
