package com.xgen.interview.receiptprinter;

import com.xgen.interview.ShoppingCart;

import java.util.Map;

public class SimpleShoppingCartReceiptPrinter implements IShoppingCartReceiptPrinter {

    public static final String TOTAL_FORMAT = "Total - €%.2f%n";
    public static final String OUTPUT_FORMAT = "%s - %d - €%.2f%n";

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
