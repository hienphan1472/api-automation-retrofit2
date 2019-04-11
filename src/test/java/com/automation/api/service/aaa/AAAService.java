package com.automation.api.service.aaa;

import com.automation.api.endpoint.EndpointCaller;
import com.automation.api.endpoint.Endpoints;
import com.automation.model.aaa.AccessTokenResponse;
import com.automation.model.aaa.JwtResponse;

public class AAAService {
    private Endpoints endPoints = Endpoints.get();
    private EndpointCaller caller = EndpointCaller.get();

    public String getJwt() {
        JwtResponse jwtResponse = caller.perform(endPoints.aaaEndpoint().getJwt("mtr_sdk", "application/json"));
        return jwtResponse.getJwt();
    }

    public String getAccessToken() {
        String jwt = getJwt();
        AccessTokenResponse accessToken = caller.perform(endPoints.aaaEndpoint().requestAccessToken(jwt));
        return accessToken.getAccessToken();
    }
}
