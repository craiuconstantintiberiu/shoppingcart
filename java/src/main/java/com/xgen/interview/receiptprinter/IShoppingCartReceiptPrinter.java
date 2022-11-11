package com.xgen.interview.receiptprinter;

import com.xgen.interview.model.ShoppingItem;

import java.util.List;

public interface IShoppingCartReceiptPrinter {

    /**
     * Generates receipt based on a list of {@code ShoppingItem}
     *
     * @param items - Objects of class {@code ShoppingItem}
     * @return A List holding objects of type {@code String}, each representing one line of the receipt.
     */
    List<String> generateReceipt(List<ShoppingItem> items);
}