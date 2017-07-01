package com.develmagic.quellio.basket;

import com.develmagic.quellio.list.Product;

/**
 * Created by mejmo on 28.5.2017.
 */

public class ProductQuantity {
    private Product product;

    public ProductQuantity(Product product) {
        this.product = product;
        this.increment();
    }

    public int getProductId() {
        return this.getProduct().getId();
    }

    public int getQuantity() {
        return quantity;
    }

    private int quantity;

    public void increment() {
        this.quantity++;
    }

    public void decrement() {
        this.quantity--;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public float getTotalPrice() {
        return product.getPrice() * quantity;
    }
}
