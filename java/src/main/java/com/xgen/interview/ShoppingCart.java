package com.xgen.interview;

import java.util.HashMap;
import java.util.Map;


public class ShoppingCart implements IShoppingCart {

    private final HashMap<String, Integer> contents = new HashMap<>();
    private final IPricingDatabase pricingDatabase;
    private final IShoppingCartReceiptFormatter receiptFormatter;


    public ShoppingCart(PricingDatabaseInMemory pricer,
                        IShoppingCartReceiptFormatter receiptFormatter) {
        this.pricingDatabase = pricer;
        this.receiptFormatter = receiptFormatter;
    }

    public void addItem(String itemType, int number) {
        contents.compute(itemType,
                (key, existingValue) -> existingValue == null ? number : existingValue + number);
    }

    public void printReceipt() {
        receiptFormatter.printReceipt(this);
    }

    public Map<String, Integer> getContents() {
        return contents;
    }

    public IPricingDatabase getPricingDatabase() {
        return pricingDatabase;
    }
}
