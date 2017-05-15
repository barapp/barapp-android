package com.develmagic.quellio.service.dto;

import com.develmagic.quellio.Category;

/**
 * Created by mejmo on 12.5.2017.
 */

public class ProductDTO {
    private int id;
    private String name;
    private float price;
    private Category category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
