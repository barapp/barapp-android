package com.develmagic.quellio.util;

import android.graphics.drawable.Drawable;

import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;

/**
 * Created by mejmo on 12.5.2017.
 */

public class Util {

    public static Drawable loadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }

    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

}
