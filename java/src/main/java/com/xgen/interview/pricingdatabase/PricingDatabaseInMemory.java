package com.xgen.interview.pricingdatabase;

import java.util.Map;


/**
 * Class representing an in-memory representation of a database.
 */
public class PricingDatabaseInMemory implements IPricingDatabase {
    private final Map<String, Integer> itemsAndPrices;

    /**
     * Initializes database given a map of ItemType, Price(as euro-cents) key-value pairs.
     * @param itemsAndPrices - map of items and respective prices
     */
    public PricingDatabaseInMemory(Map<String, Integer> itemsAndPrices) {
        this.itemsAndPrices = itemsAndPrices;
    }

    public int getPrice(String itemType) {
        return itemsAndPrices.getOrDefault(itemType, 0);
    }

}
