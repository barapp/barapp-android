package rii.solutions.campus.data;

import com.google.gson.annotations.SerializedName;

/**
 * Model that holds the result of order operation
 *
 * Created by mejmo on 15.5.2017.
 */
@lombok.Data
public class OrderResult {

    @SerializedName("result_code")
    private int resultCode;

    @SerializedName("error")
    private String error;

    public static final int OK = 1;
    public static final int ERROR = 2;

}
