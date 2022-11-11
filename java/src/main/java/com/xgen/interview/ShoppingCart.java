package com.xgen.interview;

import com.xgen.interview.pricingdatabase.IPricingDatabase;
import com.xgen.interview.pricingdatabase.PricingDatabaseInMemory;
import com.xgen.interview.receiptprinter.IShoppingCartReceiptPrinter;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Represents a shopping cart, backed by an ordered collection. When printing the receipt of this shopping cart,
 * items are printed based on the order in which they were scanned. Note: Items are ordered based on their
 * insertion, not when their quantities were last modified.
 */
public class ShoppingCart implements IShoppingCart {

    private final HashMap<String, Integer> contents = new LinkedHashMap<>();
    private final IPricingDatabase pricingDatabase;
    private final IShoppingCartReceiptPrinter receiptFormatter;


    public ShoppingCart(PricingDatabaseInMemory pricer,
                        IShoppingCartReceiptPrinter receiptFormatter) {
        this.pricingDatabase = pricer;
        this.receiptFormatter = receiptFormatter;
    }

    public void addItem(String itemType, int quantity) {
        contents.compute(itemType,
                (key, existingValue) -> existingValue == null ? quantity : existingValue + quantity);
    }

    /**
     * Prints the receipt of the shopping cart, based on the {@code IShoppingCartReceiptPrinter} specified in the
     * constructor.
     */
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
