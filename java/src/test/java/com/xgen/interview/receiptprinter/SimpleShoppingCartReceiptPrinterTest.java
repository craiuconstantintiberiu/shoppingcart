package com.xgen.interview.receiptprinter;

import com.xgen.interview.ShoppingCart;
import com.xgen.interview.pricingdatabase.PricingDatabaseInMemory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static com.xgen.interview.receiptprinter.SimpleShoppingCartReceiptPrinter.OUTPUT_FORMAT;
import static com.xgen.interview.receiptprinter.SimpleShoppingCartReceiptPrinter.TOTAL_FORMAT;
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

        List<String> receipt = receiptFormatter.generateReceipt(shoppingCart.getShoppingItems());
        assertTrue(receipt.contains(String.format(OUTPUT_FORMAT, "apple", 1, 1.0f)));
        assertTrue(receipt.contains(String.format(TOTAL_FORMAT, 1.0f)));
    }

    @Test
    public void whenPrintingReceiptForCartWithOneItemMultipleQuantitiesReceiptIsCorrectlyPrinted() {
        shoppingCart.addItem("apple", 2);
        when(pricer.getPrice("apple")).thenReturn(100);

        List<String> receipt = receiptFormatter.generateReceipt(shoppingCart.getShoppingItems());
        assertTrue(receipt.contains(String.format(OUTPUT_FORMAT, "apple", 2, 2.0f)));
        assertTrue(receipt.contains(String.format(TOTAL_FORMAT, 2.0f)));
    }

    @Test
    public void whenPrintingReceiptForCartWithMultipleItemsThenReceiptIsCorrectlyPrinted() {
        shoppingCart.addItem("apple", 2);
        shoppingCart.addItem("banana", 1);
        when(pricer.getPrice("apple")).thenReturn(100);
        when(pricer.getPrice("banana")).thenReturn(50);

        List<String> receipt = receiptFormatter.generateReceipt(shoppingCart.getShoppingItems());
        assertTrue(receipt.contains(String.format(OUTPUT_FORMAT, "apple", 2, 2.0f)));
        assertTrue(receipt.contains(String.format(OUTPUT_FORMAT, "banana", 1, 0.5f)));
        assertTrue(receipt.contains(String.format(TOTAL_FORMAT, 2.5f)));

    }

    @Test
    public void whenPrintingReceiptThenReceiptPrintedInOrder() {
        shoppingCart.addItem("apple", 2);
        shoppingCart.addItem("banana", 1);
        shoppingCart.addItem("banana", 1);
        shoppingCart.addItem("apple", 3);

        when(pricer.getPrice("apple")).thenReturn(100);
        when(pricer.getPrice("banana")).thenReturn(50);

        assertTrue(receiptFormatter.generateReceipt(shoppingCart.getShoppingItems())
                .get(0).contains(String.format(OUTPUT_FORMAT, "apple", 5, 5.0f)));
    }
}
