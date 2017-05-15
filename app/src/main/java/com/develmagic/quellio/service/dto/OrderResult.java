package com.develmagic.quellio.service.dto;

/**
 * Created by mejmo on 15.5.2017.
 */

public class OrderResult {

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

    private int resultCode;
    private String error;

}
