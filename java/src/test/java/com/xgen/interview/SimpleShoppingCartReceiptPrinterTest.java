package com.xgen.interview;

import com.xgen.interview.pricingdatabase.PricingDatabaseInMemory;
import com.xgen.interview.receiptprinter.IShoppingCartReceiptPrinter;
import com.xgen.interview.receiptprinter.SimpleShoppingCartReceiptPrinter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SimpleShoppingCartReceiptPrinterTest {

    @Mock
    private PricingDatabaseInMemory pricer;

    private ShoppingCart shoppingCart;

    private final IShoppingCartReceiptPrinter receiptFormatter = new SimpleShoppingCartReceiptPrinter();

    @Before
    public void setup() {
        shoppingCart = new ShoppingCart(pricer, receiptFormatter);
    }

    @Test
    public void whenPrintingReceiptForCartWithOneItemReceiptIsCorrectlyPrinted() {
        shoppingCart.addItem("apple", 1);
        when(pricer.getPrice("apple")).thenReturn(100);

        assertTrue(getReceiptPrintResult().contains(String.format("apple - 1 - €1.00%n")));
        assertTrue(getReceiptPrintResult().contains(String.format("Total - €1.00%n")));
    }

    @Test
    public void whenPrintingReceiptForCartWithOneItemMultipleQuantitiesReceiptIsCorrectlyPrinted() {
        shoppingCart.addItem("apple", 2);
        when(pricer.getPrice("apple")).thenReturn(100);

        assertTrue(getReceiptPrintResult().contains(String.format("apple - 2 - €2.00%n")));
        assertTrue(getReceiptPrintResult().contains(String.format("Total - €2.00%n")));
    }

    @Test
    public void whenPrintingReceiptForCartWithMultipleItemsThenReceiptIsCorrectlyPrinted() {
        shoppingCart.addItem("apple", 2);
        shoppingCart.addItem("banana", 1);
        when(pricer.getPrice("apple")).thenReturn(100);
        when(pricer.getPrice("banana")).thenReturn(50);

        assertTrue(getReceiptPrintResult().contains(String.format("apple - 2 - €2.00%n")));
        assertTrue(getReceiptPrintResult().contains(String.format("banana - 1 - €0.50%n")));
        assertTrue(getReceiptPrintResult().contains(String.format("Total - €2.50%n")));
    }

    private String getReceiptPrintResult() {
        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        receiptFormatter.printReceipt(shoppingCart);
        return myOut.toString();
    }

}
