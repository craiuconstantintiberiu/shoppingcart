package com.xgen.interview;

import java.util.Map;

public class SimpleShoppingCartReceiptFormatter implements IShoppingCartReceiptFormatter {

    private static final String PRICE_FORMAT = "€%.2f";

    @Override
    public void printReceipt(ShoppingCart shoppingCart) {
        int totalInCents = 0;
        for (Map.Entry<String, Integer> itemAndQuantity : shoppingCart.getContents().entrySet()) {
            String itemType = itemAndQuantity.getKey();
            int quantity = itemAndQuantity.getValue();
            int priceInCents = shoppingCart.getPricer().getPrice(itemType) * quantity;

            totalInCents += priceInCents;
            String priceString = String.format(PRICE_FORMAT, convertFromCentsToEuros(priceInCents));

            System.out.println(itemAndQuantity.getKey() + " - " + itemAndQuantity.getValue() + " - " + priceString);
        }

        System.out.println("Total - " + String.format(PRICE_FORMAT, convertFromCentsToEuros(totalInCents)));
    }

    private float convertFromCentsToEuros(int cents) {
        return cents / 100.0f;
    }
}
