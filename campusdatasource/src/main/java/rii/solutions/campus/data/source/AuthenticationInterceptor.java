package rii.solutions.campus.data.source;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Adds Authorization header
 *
 * Created by rimmer on 04.06.2017.
 */

class AuthenticationInterceptor implements Interceptor {
    private String authToken;

    AuthenticationInterceptor(String token) {
        this.authToken = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder builder = original.newBuilder()
                .header("Authorization", authToken);

        Request request = builder.build();

        return chain.proceed(request);
    }
}
