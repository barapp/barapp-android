package rii.solutions.campus.doorbell.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Class that helps with getting random
 * Maerial color
 *
 * Created by rimmer on 05.06.2017.
 */

@SuppressWarnings("WeakerAccess")
public class MaterialColorUtils {

    public static final String TYPE_COLOR_50   = "50";
    public static final String TYPE_COLOR_100  = "100";
    public static final String TYPE_COLOR_200  = "200";
    public static final String TYPE_COLOR_300  = "300";
    public static final String TYPE_COLOR_400  = "400";
    public static final String TYPE_COLOR_500  = "500";
    public static final String TYPE_COLOR_600  = "600";
    public static final String TYPE_COLOR_700  = "700";
    public static final String TYPE_COLOR_800  = "800";
    public static final String TYPE_COLOR_900  = "900";
    public static final String TYPE_COLOR_A100 = "A100";
    public static final String TYPE_COLOR_A200 = "A200";
    public static final String TYPE_COLOR_A400 = "A400";
    public static final String TYPE_COLOR_A700 = "A700";

    @StringDef({
        TYPE_COLOR_50,
        TYPE_COLOR_100,
        TYPE_COLOR_200,
        TYPE_COLOR_300,
        TYPE_COLOR_400,
        TYPE_COLOR_500,
        TYPE_COLOR_600,
        TYPE_COLOR_700,
        TYPE_COLOR_800,
        TYPE_COLOR_900,
        TYPE_COLOR_A100,
        TYPE_COLOR_A200,
        TYPE_COLOR_A400,
        TYPE_COLOR_A700
    })
    @Retention(RetentionPolicy.RUNTIME)
    public @interface TypeColor {}

    public static int getRandomMaterialColor(final Context context, @TypeColor final String typeColor) {
        int returnColor = Color.WHITE;
        int arrayId = context.getResources().getIdentifier("mdcolor_" + typeColor, "array",
                context.getPackageName());

        if (arrayId != 0) {
            TypedArray colors = context.getResources().obtainTypedArray(arrayId);
            int index = (int) (Math.random() * colors.length());
            returnColor = colors.getColor(index, returnColor);
            colors.recycle();
        }
        return returnColor;
    }
}
