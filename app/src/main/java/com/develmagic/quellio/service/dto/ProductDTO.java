package com.develmagic.quellio.service.dto;

import com.develmagic.quellio.Category;
import com.google.gson.annotations.SerializedName;
//import com.google.gson.annotations.SerializedName;

/**
 * Created by mejmo on 12.5.2017.
 */

public class ProductDTO {

//    @SerializedName("id")
    private int id;

//    @SerializedName("name")
    private String name;

//    @SerializedName("price")
    private float price;

//    @SerializedName("category")
    private Category category;

    @SerializedName("image_url")
    private String imageUrl;

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
