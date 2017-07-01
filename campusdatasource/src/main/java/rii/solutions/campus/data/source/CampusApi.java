package rii.solutions.campus.data.source;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rii.solutions.campus.data.CampusCompany;
import rii.solutions.campus.data.CampusMember;
import rii.solutions.campus.data.MenuItem;
import rii.solutions.campus.data.OrderResult;
import rii.solutions.campus.data.ResponseWrapperModel;

/**
 * Retrofit interface communicating with Campus API
 *
 * Created by rimmer on 04.06.2017.
 */

interface CampusApi {

    @GET("/items/")
    Call<ResponseWrapperModel<List<MenuItem>>> menuItems();

    @GET("/members/")
    Call<ResponseWrapperModel<List<CampusMember>>> members();

    @GET("/companies/")
    Call<ResponseWrapperModel<List<CampusCompany>>> companies();

    @POST("/make_transaction/")
    Call<OrderResult> order(@Body JsonObject transactionData);
}
