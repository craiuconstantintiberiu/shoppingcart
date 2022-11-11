package com.xgen.interview;

public interface IShoppingCartReceiptFormatter {

    /**
     * Prints a shopping cart receipt to stdout.
     *
     * @param shoppingCart object implementing the {@code IShoppingCart} interface
     */
    void printReceipt(ShoppingCart shoppingCart);
}