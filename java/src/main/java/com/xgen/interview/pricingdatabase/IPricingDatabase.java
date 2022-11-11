package com.xgen.interview.pricingdatabase;

public interface IPricingDatabase {

    /**
     * Returns the price of the item passed, in Euro-cent. Eg. if an item costs €1, this will return 100
     * If itemType is an unknown string, store policy is that the item is free.
     */
    Integer getPrice(String itemType);
}
