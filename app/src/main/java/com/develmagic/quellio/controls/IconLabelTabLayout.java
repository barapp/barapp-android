package com.develmagic.quellio.controls;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.DrawableRes;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.develmagic.quellio.R;

/**
 * Created by mejmo on 23.01. 2017.
 */

public class IconLabelTabLayout extends TabLayout {

    private static final String DIVIDER_COLOR = "#42362e";
    private String dividerColor;

    public Tab decorateTab(Context context, @DrawableRes int icon, int tabIndex) {

        Tab tab = getTabAt(tabIndex);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),"Roboto-Light.ttf");

        View view = LayoutInflater.from(context).inflate(R.layout.custom_tab_item, null);
        view.findViewById(R.id.icon).setBackgroundResource(icon);

        TextView textView = (TextView) view.findViewById(R.id.labeltext);
        textView.setTypeface(typeface);
        textView.setText(tab.getText());

        tab.setCustomView(view);

        if (this.getTabCount() == 1) {
            this.getTabAt(0).getCustomView().findViewById(R.id.icon).setAlpha(1f);
            this.getTabAt(0).getCustomView().findViewById(R.id.labeltext).setAlpha(1f);
            this.getTabAt(0).select();
        }

        return tab;

    }

    public IconLabelTabLayout(Context context) {
        super(context);
        setup();
    }

    public IconLabelTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public IconLabelTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup();
    }

    public void setup() {
        setDividers();
        this.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getCustomView() != null) {
                    tab.getCustomView().findViewById(R.id.icon).setAlpha(1f);
                    tab.getCustomView().findViewById(R.id.labeltext).setAlpha(1f);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab.getCustomView() != null) {
                    tab.getCustomView().findViewById(R.id.icon).setAlpha(0.5f);
                    tab.getCustomView().findViewById(R.id.labeltext).setAlpha(0.5f);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setDividers() {
        LinearLayout linearLayout = (LinearLayout)this.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.parseColor(getDividerColor()));
        drawable.setSize(1, 1);
        linearLayout.setDividerPadding(20);
        linearLayout.setDividerDrawable(drawable);
    }

    public String getDividerColor() {
        if (dividerColor == null)
            return DIVIDER_COLOR;
        return dividerColor;
    }

    public void setDividerColor(String dividerColor) {
        this.dividerColor = dividerColor;
    }
}
