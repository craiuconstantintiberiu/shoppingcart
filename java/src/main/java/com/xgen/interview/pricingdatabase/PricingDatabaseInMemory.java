package com.xgen.interview.pricingdatabase;

import java.util.Map;


public class PricingDatabaseInMemory implements IPricingDatabase {
    private final Map<String, Integer> itemsAndPrices;

    public PricingDatabaseInMemory(Map<String, Integer> itemsAndPrices) {
        this.itemsAndPrices = itemsAndPrices;
    }

    public Integer getPrice(String itemType) {
        return itemsAndPrices.getOrDefault(itemType, 0);
    }

}
