package com.xgen.interview;

import com.xgen.interview.pricingdatabase.PricingDatabaseInMemory;
import com.xgen.interview.receiptprinter.IShoppingCartReceiptPrinter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class ShoppingCartTest {

    @Mock
    private PricingDatabaseInMemory pricer;

    @Mock
    private IShoppingCartReceiptPrinter receiptFormatter;


    private ShoppingCart shoppingCart;

    @Before
    public void setup() {
        shoppingCart = new ShoppingCart(pricer, receiptFormatter);
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
    public void whenPrintingReceiptReceiptFormatterIsCalled() {
        shoppingCart.printReceipt();
        verify(receiptFormatter).printReceipt(shoppingCart);
    }
}


