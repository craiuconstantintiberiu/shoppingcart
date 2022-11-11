package com.xgen.interview.receiptprinter;

import com.xgen.interview.ShoppingCart;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public abstract class ReceiptPrinterTest {

    public String getReceiptPrintResult(IShoppingCartReceiptPrinter receiptPrinter,
                                        ShoppingCart shoppingCart) {
        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        receiptPrinter.printReceipt(shoppingCart);
        return myOut.toString();
    }
}
