package com.xgen.interview.receiptprinter;

import com.xgen.interview.ShoppingCart;

import java.util.Map;

public class SimpleShoppingCartReceiptPrinter implements IShoppingCartReceiptPrinter {

    private static final String PRICE_FORMAT = "€%.2f";

    @Override
    public void printReceipt(ShoppingCart shoppingCart) {
        int totalInCents = 0;
        for (Map.Entry<String, Integer> itemAndQuantity : shoppingCart.getContents().entrySet()) {
            String itemType = itemAndQuantity.getKey();
            int quantity = itemAndQuantity.getValue();
            int priceInCents = shoppingCart.getPricingDatabase().getPrice(itemType) * quantity;

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
