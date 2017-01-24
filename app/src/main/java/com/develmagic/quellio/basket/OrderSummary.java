package com.develmagic.quellio.basket;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.develmagic.quellio.R;

/**
 * Created by mejmo on 23.01. 2017.
 */

public class OrderSummary extends LinearLayout {

    public OrderSummary(Context context) {
        super(context);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),"Roboto-Light.ttf");
        View view = LayoutInflater.from(context).inflate(R.layout.basket_order_summary, null);
        this.addView(view);
    }



}
