package com.xgen.interview;

import java.lang.reflect.Array;
import java.util.*;


/**
 * This is the current implementation of ShoppingCart.
 * Please write a replacement
 */
public class ShoppingCart implements IShoppingCart {
    private final HashMap<String, Integer> contents = new HashMap<>();
    private final PricingDatabaseInMemory pricer;

    public ShoppingCart(PricingDatabaseInMemory pricer) {
        this.pricer = pricer;
    }

    public void addItem(String itemType, int number) {
        contents.compute(itemType,
                (key, existingValue) -> existingValue==null? number: existingValue+number);
    }

    public void printReceipt() {
        Object[] keys = contents.keySet().toArray();

        for (int i = 0; i < Array.getLength(keys) ; i++) {
            Integer price = pricer.getPrice((String)keys[i]) * contents.get(keys[i]);
            Float priceFloat =  price / 100.0f;
            String priceString = String.format("€%.2f", priceFloat);

            System.out.println(keys[i] + " - " + contents.get(keys[i]) + " - " + priceString);
        }
    }

    public Map<String, Integer> getContents() {
        return contents;
    }

    public PricingDatabaseInMemory getPricer() {
        return pricer;
    }
}
