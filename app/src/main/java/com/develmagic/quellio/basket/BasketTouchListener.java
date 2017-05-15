package com.develmagic.quellio.basket;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.develmagic.quellio.MainActivity;

/**
 * Created by mejmo on 25.01. 2017.
 */
public class BasketTouchListener extends RecyclerTouchListener {
    public BasketTouchListener(Context context, RecyclerView recyclerView, MainActivity.ClickListener clickListener) {
        super(context, recyclerView, clickListener);
    }
}
