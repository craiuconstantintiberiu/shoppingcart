package com.xgen.interview.receiptprinter;

import com.xgen.interview.ShoppingCart;

public interface IShoppingCartReceiptPrinter {

    /**
     * Prints a shopping cart receipt to stdout.
     *
     * @param shoppingCart object implementing the {@code IShoppingCart} interface
     */
    void printReceipt(ShoppingCart shoppingCart);
}