package com.xgen.interview;

import com.xgen.interview.model.ShoppingItem;
import com.xgen.interview.pricingdatabase.IPricingDatabase;
import com.xgen.interview.receiptprinter.IShoppingCartReceiptPrinter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ShoppingCartTest {

    @Mock
    private IPricingDatabase pricingDatabase;

    @Mock
    private IShoppingCartReceiptPrinter receiptPrinter;


    private ShoppingCart shoppingCart;

    @Before
    public void setup() {
        shoppingCart = new ShoppingCart(pricingDatabase, receiptPrinter);
    }

    @Test
    public void whenAddingItemThenItemAdded() {
        shoppingCart.addItem("apple", 1);

        assertTrue(shoppingCart.getContents().containsKey("apple"));
        assertEquals(1, (int) shoppingCart.getContents().get("apple"));
    }

    @Test
    public void whenAddingItemMultipleQuantitiesThenItemAdded() {
        shoppingCart.addItem("apple", 2);

        assertTrue(shoppingCart.getContents().containsKey("apple"));
        assertEquals(2, (int) shoppingCart.getContents().get("apple"));
    }

    @Test
    public void whenAddingMultipleItemsThenItemsAdded() {
        shoppingCart.addItem("apple", 2);
        shoppingCart.addItem("banana", 1);

        assertTrue(shoppingCart.getContents().containsKey("apple"));
        assertEquals(2, (int) shoppingCart.getContents().get("apple"));
        assertTrue(shoppingCart.getContents().containsKey("banana"));
        assertEquals(1, (int) shoppingCart.getContents().get("banana"));
    }

    @Test
    public void whenAddingItemRepeatedlyThenQuantityCorrectlyUpdated() {
        shoppingCart.addItem("apple", 2);
        shoppingCart.addItem("apple", 2);

        assertTrue(shoppingCart.getContents().containsKey("apple"));
        assertEquals(4, (int) shoppingCart.getContents().get("apple"));
    }

    @Test
    public void whenObtainingShoppingItemsThenShoppingItemsAreCorrectlyObtained() {
        shoppingCart.addItem("apple", 2);
        shoppingCart.addItem("apple", 2);
        shoppingCart.addItem("banana", 3);

        when(pricingDatabase.getPrice("apple")).thenReturn(100);
        when(pricingDatabase.getPrice("banana")).thenReturn(50);

        List<ShoppingItem> items = shoppingCart.getShoppingItems();
        assertTrue(items.contains(new ShoppingItem("apple", 100, 4)));
        assertTrue(items.contains(new ShoppingItem("banana", 50, 3)));
    }


    @Test
    public void whenPrintingReceiptOfShoppingCartThenReceiptIsPrintedAsReceivedFromReceiptPrinter() {
        List<String> generatedReceipt = List.of("apple - 1 - 2", "Total - 2");
        when(receiptPrinter.generateReceipt(any(List.class)))
                .thenReturn(generatedReceipt);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));
        shoppingCart.printReceipt();

        String expectedPrint = generatedReceipt.stream()
                .map(receiptLine->String.format("%s%n", receiptLine))
                .collect(Collectors.joining());
        assertEquals(expectedPrint, myOut.toString());
           }

    @Test
    public void whenPrintingReceiptreceiptPrinterIsCalled() {
        shoppingCart.printReceipt();
        verify(receiptPrinter).generateReceipt(any(List.class));
    }
}


