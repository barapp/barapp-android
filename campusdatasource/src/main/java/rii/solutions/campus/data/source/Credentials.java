package rii.solutions.campus.data.source;

import rii.solutions.campus.data.BuildConfig;

/**
 * Created by mejmo on 23.5.2017.
 */

public interface Credentials {
    String API_USERNAME = BuildConfig.API_USERNAME;
    String API_PASS = BuildConfig.API_PASS;
    String API_BASE_URL = BuildConfig.API_BASE_URL;
}
