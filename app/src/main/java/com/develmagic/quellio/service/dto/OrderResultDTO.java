package com.develmagic.quellio.service.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mejmo on 15.5.2017.
 */

public class OrderResultDTO {

    @SerializedName("result_code")
    private int resultCode;

    @SerializedName("error")
    private String error;

    public static final int OK = 1;
    public static final int ERROR = 2;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
