package com.develmagic.quellio.util;

import android.graphics.drawable.Drawable;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;

import rii.solutions.campus.data.MenuItem;

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

    public static JsonObject generateTransaction(long userId, List<MenuItem> productDTOList) {
        JSONObject jsonObject = new JSONObject();
        try {
            JSONArray productsArray = new JSONArray();

            for (MenuItem dto : productDTOList) {
                JSONObject j = new JSONObject();
                j.put("id", dto.getId());
                productsArray.put(j);
            }

            jsonObject.put("products", productsArray);
            jsonObject.put("userId", userId);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonParser jsonParser = new JsonParser();
        JsonObject gsonObject = (JsonObject) jsonParser.parse(jsonObject.toString());
        return gsonObject;
    }


}
