package rii.solutions.campus.data.source;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import rii.solutions.campus.data.CampusCompany;
import rii.solutions.campus.data.CampusMember;
import rii.solutions.campus.data.MenuItem;
import rii.solutions.campus.data.OrderResult;
import rii.solutions.campus.data.ResponseWrapperModel;

public class CampusDataSource implements CampusApi {

    public CampusDataSource(final String user, final String password) {
        mApi = ApiServiceFactory.createService(CampusApi.class, user, password);
    }

    private CampusApi mApi;

    @Override
    public Call<ResponseWrapperModel<List<MenuItem>>> menuItems() {
        return mApi.menuItems();
    }

    @Override
    public Call<ResponseWrapperModel<List<CampusMember>>> members() {
        return mApi.members();
    }

    @Override
    public Call<ResponseWrapperModel<List<CampusCompany>>> companies() {
        return mApi.companies();
    }

    @Override
    public Call<OrderResult> order(@Body JsonObject transactionData) {
        return mApi.order(transactionData);
    }

}
