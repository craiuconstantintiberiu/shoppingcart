package com.xgen.interview.receiptprinter;

import com.xgen.interview.ShoppingCart;

import java.util.Map;

/**
 * Represents a printer for a shopping cart, printing to standard output the items in the shopping cart, with itemized
 * totals based on per-item quantity and price and a total for the entire cart. Prices are represented as floats with 2
 * decimals.
 *
 * Example receipt:
 * €1.50 - Apple - 1
 * €3.40 - Banana - 2
 * Total - €4.90
 */
public class PriceFirstShoppingCartReceiptPrinter implements IShoppingCartReceiptPrinter {

    public static final String OUTPUT_FORMAT = "€%.2f - %s - %d%n";
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
            System.out.printf(OUTPUT_FORMAT, priceInEuros, itemAndQuantity.getKey(),
                    itemAndQuantity.getValue());
        }

        System.out.printf(TOTAL_FORMAT, convertFromCentsToEuros(totalInCents));
    }

    private float convertFromCentsToEuros(int cents) {
        return cents / 100.0f;
    }
}
