package com.develmagic.quellio.basket;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.develmagic.quellio.Constants;
import com.develmagic.quellio.R;
import com.develmagic.quellio.list.Product;

/**
 * Created by mejmo on 23.01. 2017.
 */

public class ProductMini extends LinearLayout {

    private int id;

    public ProductMini(Context context) {
        super(context);
    }

    public ProductMini(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProductMini(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ProductMini(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public static ProductMini newInstance(LayoutInflater inflater, int id, String title, float price) {
        ProductMini product = (ProductMini) inflater.inflate(R.layout.basket_product_mini, null);
        inflater.inflate(R.layout.basket_product_mini, null);
        product.id = id;

//        ImageView imageView = (ImageView) product.findViewById(R.id.productminiimage);
//        imageView.setImageBitmap(bitmap);
//        new Product.DownloadImageTask(imageView).execute(Constants.API_BASE_URL+imageUrl.substring(1));

        return product;
    }

    public void setRemoveListener(int position) {

    }


}
