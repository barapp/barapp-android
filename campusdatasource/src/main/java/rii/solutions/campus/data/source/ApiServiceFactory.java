package rii.solutions.campus.data.source;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rimmer on 04.06.2017.
 */

final class ApiServiceFactory {
    private static final String API_BASE_URL = "http://develmagic.com:8000/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                            .create()))
                    .baseUrl(API_BASE_URL);

    static <S> S createService(
            Class<S> serviceClass, String username, String password) {
        if (!isEmpty(username)
                && !isEmpty(password)) {
            String authToken = Credentials.basic(username, password);
            return createService(serviceClass, authToken);
        }

        return createService(serviceClass, null, null);
    }

    private static <S> S createService(
            Class<S> serviceClass, final String authToken) {
        if (!isEmpty(authToken)) {
            AuthenticationInterceptor interceptor =
                    new AuthenticationInterceptor(authToken);

            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);

                builder.client(httpClient.build());

            }
        }

        return builder.build().create(serviceClass);
    }

    private static boolean isEmpty(String authToken) {
        return authToken == null || authToken.isEmpty();
    }

}
