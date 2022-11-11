package com.xgen.interview.receiptprinter;

import com.xgen.interview.model.ShoppingItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a printer for a shopping cart, printing to standard output the items in the shopping cart, with itemized
 * totals based on per-item quantity and price and a total for the entire cart. Prices are represented as floats with 2
 * decimals.
 * <p>
 * Example receipt:
 * €1.50 - Apple - 1
 * €3.40 - Banana - 2
 * Total - €4.90
 */
public class PriceFirstShoppingCartReceiptPrinter implements IShoppingCartReceiptPrinter {

    public static final String OUTPUT_FORMAT = "€%.2f - %s - %d";
    public static final String TOTAL_FORMAT = "Total - €%.2f";

    @Override
    public List<String> generateReceipt(List<ShoppingItem> items) {
        List<String> receipt = new ArrayList<>();
        int totalInCents = 0;
        for (ShoppingItem item : items) {
            int totalPriceForItem = item.getPrice() * item.getQuantity();
            float priceInEuros = totalPriceForItem / 100.0f;
            receipt.add(String.format(OUTPUT_FORMAT, priceInEuros, item.getName(),
                    item.getQuantity()));
            totalInCents += totalPriceForItem;
        }
        receipt.add(String.format(TOTAL_FORMAT, convertFromCentsToEuros(totalInCents)));
        return receipt;
    }

    private float convertFromCentsToEuros(int cents) {
        return cents / 100.0f;
    }
}
