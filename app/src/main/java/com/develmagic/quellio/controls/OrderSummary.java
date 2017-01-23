package com.develmagic.quellio.controls;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by mejmo on 23.01. 2017.
 */

public class OrderSummary extends LinearLayout {

    public OrderSummary(Context context) {
        super(context);
        TextView title = new TextView(context);
        title.setText("Order summary: ");
        this.addView();
    }


}
