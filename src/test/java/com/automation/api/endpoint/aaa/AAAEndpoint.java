package com.automation.api.endpoint.aaa;

import com.automation.model.aaa.AccessTokenResponse;
import com.automation.model.aaa.JwtResponse;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AAAEndpoint {
    @POST("v1/oauth/token?grant_type=client_credentials")
    public Call<AccessTokenResponse> requestAccessToken(
            @Header("X-Vibe-JWT") String jwt_token);

    @POST("v1/oauth/register")
    public Call<JwtResponse> getJwt(
            @Header("x-vibe-client-id") String clientId,
            @Header("content-type") String contentType
    );
}
