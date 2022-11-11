package com.xgen.interview;

import com.xgen.interview.pricingdatabase.IPricingDatabase;
import com.xgen.interview.pricingdatabase.PricingDatabaseInMemory;
import com.xgen.interview.receiptprinter.IShoppingCartReceiptPrinter;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class ShoppingCart implements IShoppingCart {

    private final HashMap<String, Integer> contents = new LinkedHashMap<>();
    private final IPricingDatabase pricingDatabase;
    private final IShoppingCartReceiptPrinter receiptFormatter;


    public ShoppingCart(PricingDatabaseInMemory pricer,
                        IShoppingCartReceiptPrinter receiptFormatter) {
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
