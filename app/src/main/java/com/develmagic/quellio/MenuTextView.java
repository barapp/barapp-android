package com.develmagic.quellio;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by mejmo on 23.01. 2017.
 */

public class MenuTextView extends TextView {
    public MenuTextView(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public MenuTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public MenuTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context);
    }

    public MenuTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
    }
}
