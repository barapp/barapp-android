package rii.solutions.campus.data;

import com.google.gson.annotations.SerializedName;

/**
 * Represents menu item from the bar menu
 * (coffee, redbull, cake etc)
 *
 * Created by rimmer on 04.06.2017.
 */

@SuppressWarnings("unused")
@lombok.Data
public final class MenuItem extends BaseModel {
    private long id;
    private String name;
    private Category category;
    private float price;
    @SerializedName("image_url")
    private String imageUrl;
}
