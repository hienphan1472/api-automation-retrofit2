package com.automation.api.endpoint;

import com.automation.api.endpoint.aaa.AAAEndpoint;
import com.automation.api.endpoint.article.ArticleEndpoint;
import com.automation.api.endpoint.employee.EmployeeEndpoint;
import com.automation.api.service.aaa.AAAService;
import com.automation.support.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

public class Endpoints {

    private static Endpoints instance;
    private Configuration configuration = Configuration.get();



    // Adapters
    private Retrofit articleAdapter;
    private Retrofit employeeAdapter;
    private Retrofit aaaAdapter;

    // Endpoints
    private ArticleEndpoint articleEndpoint;
    private EmployeeEndpoint employeeEndpoint;
    private AAAEndpoint aaaEndpoint;

    // Interceptors - Will be set up by Before class in Hooks
    private Interceptor requestInterceptor;

    public static Endpoints get() {

        if (instance == null) {
            instance = new Endpoints();
        }
        return instance;
    }

    public void setRequestInterceptor(Interceptor requestInterceptor) {
        this.requestInterceptor = requestInterceptor;
    }

    private void setupRequestInterceptors() {
        Interceptor requestInterceptor = buildRequestInterceptor();
        Endpoints.get().setRequestInterceptor(requestInterceptor);
    }

    private Interceptor buildRequestInterceptor() {

        return new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {

                AAAService aaaService = new AAAService();
                String token = aaaService.getAccessToken();

                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder();

                if (token != null) {
                    requestBuilder
                            .addHeader("Authorization", "bearer " + token);
                }

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };

    }

    public Retrofit getArticleRestAdapter() {
        if (articleAdapter == null) {
            OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
            clientBuilder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
            articleAdapter = new Retrofit.Builder()
                    .baseUrl(configuration.getTestProperty("services.uri.article"))
                    .client(clientBuilder.build())
                    .addConverterFactory(JacksonConverterFactory.create(new ObjectMapper()))
                    .build();
        }
        return articleAdapter;
    }

    public Retrofit getEmployeeRestAdapter() {
        if (employeeAdapter == null) {
            OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
            clientBuilder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
            employeeAdapter = new Retrofit.Builder()
                    .baseUrl(configuration.getTestProperty("services.uri.employee"))
                    .client(clientBuilder.build())
                    .addConverterFactory(JacksonConverterFactory.create(new ObjectMapper()))
                    .build();
        }
        return employeeAdapter;
    }

    public Retrofit getAaaAdapter() {
        if (aaaAdapter == null) {
            OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
            clientBuilder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
            aaaAdapter = new Retrofit.Builder()
                    .baseUrl(configuration.getTestProperty("services.uri.aaa"))
                    .client(clientBuilder.build())
                    .addConverterFactory(JacksonConverterFactory.create(new ObjectMapper()))
                    .build();
        }
        return aaaAdapter;
    }

    public ArticleEndpoint articleEndpoint() {
        if (articleEndpoint == null) {
            articleEndpoint = getArticleRestAdapter().create(ArticleEndpoint.class);
        }
        return articleEndpoint;
    }

    public EmployeeEndpoint employeeEndpoint() {
        if (employeeEndpoint == null) {
            employeeEndpoint = getEmployeeRestAdapter().create(EmployeeEndpoint.class);
        }
        return employeeEndpoint;
    }

    public AAAEndpoint aaaEndpoint() {
        if (aaaEndpoint == null) {
            aaaEndpoint = getAaaAdapter().create(AAAEndpoint.class);
        }
        return aaaEndpoint;
    }

    public void beforeScenario() {
        setupRequestInterceptors();
    }
}
