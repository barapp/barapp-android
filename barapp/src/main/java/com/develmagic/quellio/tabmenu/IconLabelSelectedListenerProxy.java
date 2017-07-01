package com.develmagic.quellio.tabmenu;

import android.support.design.widget.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Workaround for adding more listeners to TabLayout for API23. This is not required in API25 as
 * TabLayout has feature of adding listeners.+
 */
public class IconLabelSelectedListenerProxy implements TabLayout.OnTabSelectedListener {

    private List<TabLayout.OnTabSelectedListener> listeners = new ArrayList<>();

    public IconLabelSelectedListenerProxy(TabLayout.OnTabSelectedListener listener) {
        listeners.add(listener);
    }

    public void clearListeners() {
        listeners.clear();
    }

    public void addListener(TabLayout.OnTabSelectedListener listener) {
        listeners.add(listener);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        for (TabLayout.OnTabSelectedListener l : listeners) {
            l.onTabSelected(tab);
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        for (TabLayout.OnTabSelectedListener l : listeners) {
            l.onTabUnselected(tab);
        }
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        for (TabLayout.OnTabSelectedListener l : listeners) {
            l.onTabReselected(tab);
        }
    }
}
