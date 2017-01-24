package com.develmagic.quellio.list;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.develmagic.quellio.R;

/**
 * Created by mejmo on 23.01. 2017.
 */

public class Product extends LinearLayout {

    private int id;
    private String name;
    private float price;

    public Product(Context context) {
        super(context);
    }

    public Product(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Product(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Product(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public static Product newInstance(LayoutInflater inflater, int id, String title, float price) {
        Product product = (Product) inflater.inflate(R.layout.products_list_product, null);

        TextView name = (TextView) product.findViewById(R.id.productname);
        name.setText(title);

        TextView priceText = (TextView) product.findViewById(R.id.productprice);
        priceText.setText(price+" EUR");

        product.id = id;

        product.setOnClickListener(new AddToBasketListener(product));

        return product;
    }

    private void click() {

    }

}
