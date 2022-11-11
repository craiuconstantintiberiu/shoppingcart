package com.xgen.interview;

import java.util.Objects;

/**
 * Class representing an item placed in an {@code IShoppingCart}, with a name, price and quantity.
 */
public class ShoppingItem {
    private final String name;
    private final int price;
    private final int quantity;

    public ShoppingItem(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingItem that = (ShoppingItem) o;
        return price == that.price && quantity == that.quantity && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, quantity);
    }
}
