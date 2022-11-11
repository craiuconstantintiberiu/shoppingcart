package com.xgen.interview.pricingdatabase;

/**
 * Interface representing a pricing database for a store
 */
public interface IPricingDatabase {

    /**
     * Returns the price of the item passed, in Euro-cent. E.g. if an item costs €1, this will return 100
     * If itemType is an unknown string, store policy is that the item is free.
     */
    int getPrice(String itemType);
}
