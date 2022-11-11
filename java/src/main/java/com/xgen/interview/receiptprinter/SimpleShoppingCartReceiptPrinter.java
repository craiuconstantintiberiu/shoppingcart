package com.xgen.interview.receiptprinter;

import com.xgen.interview.ShoppingCart;

import java.util.Map;

/**
 * Represents a printer for a shopping cart, printing to standard output the items in the shopping cart, with itemized
 * totals based on per-item quantity and price and a total for the entire cart. Prices are represented as floats with 2
 * decimals.
 *
 * Example receipt:
 * Apple - 1 - €1.50
 * Banana - 2 - €3.40
 * Total - €4.90
 */
public class SimpleShoppingCartReceiptPrinter implements IShoppingCartReceiptPrinter {

    public static final String OUTPUT_FORMAT = "%s - %d - €%.2f%n";
    public static final String TOTAL_FORMAT = "Total - €%.2f%n";

    @Override
    public void printReceipt(ShoppingCart shoppingCart) {
        int totalInCents = 0;
        for (Map.Entry<String, Integer> itemAndQuantity : shoppingCart.getContents().entrySet()) {
            String itemType = itemAndQuantity.getKey();
            int quantity = itemAndQuantity.getValue();
            int priceInCents = shoppingCart.getPricingDatabase().getPrice(itemType) * quantity;

            totalInCents += priceInCents;
            float priceInEuros = convertFromCentsToEuros(priceInCents);

            System.out.printf(OUTPUT_FORMAT, itemAndQuantity.getKey(),
                    itemAndQuantity.getValue(), priceInEuros);
        }

        System.out.printf(TOTAL_FORMAT, convertFromCentsToEuros(totalInCents));
    }

    private float convertFromCentsToEuros(int cents) {
        return cents / 100.0f;
    }
}
