package com.automation.model.aaa;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class AccessTokenResponse {

    private String accessToken;
    private String tokenType;
    private String scope;
    private Integer expiresIn;

    public String getAccessToken(){
        return accessToken;
    }
    public String getTokenType(){
        return tokenType;
    }
    public String getScope(){
        return scope;
    }
    public Integer getExpiresIn(){
        return expiresIn;
    }

}
