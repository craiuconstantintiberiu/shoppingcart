package com.xgen.interview.receiptprinter;

import com.xgen.interview.ShoppingCart;
import com.xgen.interview.pricingdatabase.PricingDatabaseInMemory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.xgen.interview.receiptprinter.PriceFirstShoppingCartReceiptPrinter.OUTPUT_FORMAT;
import static com.xgen.interview.receiptprinter.PriceFirstShoppingCartReceiptPrinter.TOTAL_FORMAT;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PriceFirstShoppingCartReceiptPrinterTest extends ReceiptPrinterTest {

    @Mock
    private PricingDatabaseInMemory pricer;

    private ShoppingCart shoppingCart;

    private final IShoppingCartReceiptPrinter receiptFormatter = new PriceFirstShoppingCartReceiptPrinter();

    @Before
    public void setup() {
        shoppingCart = new ShoppingCart(pricer, receiptFormatter);
    }

    @Test
    public void whenPrintingReceiptForCartWithOneItemReceiptIsCorrectlyPrinted() {
        shoppingCart.addItem("apple", 1);
        when(pricer.getPrice("apple")).thenReturn(100);

        String receipt = getReceiptPrintResult(receiptFormatter, shoppingCart);
        assertTrue(receipt.contains(String.format(OUTPUT_FORMAT, 1.0f, "apple", 1)));
        assertTrue(receipt.contains(String.format(TOTAL_FORMAT, 1.0f)));
    }

    @Test
    public void whenPrintingReceiptForCartWithOneItemMultipleQuantitiesReceiptIsCorrectlyPrinted() {
        shoppingCart.addItem("apple", 2);
        when(pricer.getPrice("apple")).thenReturn(100);

        String receipt = getReceiptPrintResult(receiptFormatter, shoppingCart);
        assertTrue(receipt.contains(String.format(OUTPUT_FORMAT, 2.0f, "apple", 2)));
        assertTrue(receipt.contains(String.format(TOTAL_FORMAT, 2.0f)));
    }

    @Test
    public void whenPrintingReceiptForCartWithMultipleItemsThenReceiptIsCorrectlyPrinted() {
        shoppingCart.addItem("apple", 2);
        shoppingCart.addItem("banana", 1);
        when(pricer.getPrice("apple")).thenReturn(100);
        when(pricer.getPrice("banana")).thenReturn(50);

        String receipt = getReceiptPrintResult(receiptFormatter, shoppingCart);
        assertTrue(receipt.contains(String.format(OUTPUT_FORMAT, 2.0f, "apple", 2)));
        assertTrue(receipt.contains(String.format(OUTPUT_FORMAT, 0.5f, "banana", 1)));
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

        assertTrue(getReceiptPrintResult(receiptFormatter, shoppingCart)
                .startsWith(String.format(OUTPUT_FORMAT, 5.0f, "apple", 5)));
    }

}
