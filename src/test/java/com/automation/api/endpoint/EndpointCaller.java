package com.automation.api.endpoint;

import com.automation.support.RegexMatcher;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class EndpointCaller {

    private static EndpointCaller instance;

    private boolean expectingErrorResponse = false;
    private int lastResponseCode = 0;
    private String lastErrorJson;

    public static EndpointCaller get() {
        if (instance == null) {
            instance = new EndpointCaller();
        }
        return instance;
    }

    public void beforeScenario() {
        lastResponseCode = -1;
        expectingErrorResponse = false;
        lastErrorJson = null;
    }

    public <T> T perform(Call<T> call) {

        // Ensure that the global variable is reset for each call and use the local variable
        // through the rest of this method.
        boolean expectError = expectingErrorResponse;
        expectingErrorResponse = false;

        try {
            Response<T> response = call.execute();
            lastResponseCode = response.code();
            if (!response.isSuccessful()) {
                lastErrorJson = response.errorBody().source().readUtf8();
                if (!expectError) {
                    String explanation = "Response was an error but we were not expecting one.";
                    explanation += " URL: " + call.request().url();
                    explanation += " HTTP code: " + lastResponseCode;
                    fail(explanation);
                }
                // Return null in the case of an expected message. Error details should be
                // fetched from lastResponseCode and lastErrorJson in this case.
                return null;

            } else {
                System.out.println("Response: " + response.body());
                return response.body();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public int getLastResponseCode() {
        return lastResponseCode;
    }

    public String getLastErrorJson() {
        return lastErrorJson;
    }

    public void setExpectingErrorResponse(boolean expectingErrorResponse) {
        this.expectingErrorResponse = expectingErrorResponse;
    }

    public boolean isExpectingErrorResponse() {
        return expectingErrorResponse;
    }

    /*
    Usage: caller.errorDescriptionMatches("(.*)token was either missing or invalid(.*)");
     */
    public void errorDescriptionMatches(String description){
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(lastErrorJson);
            String error = rootNode.get("error").asText();
            assertThat(error, RegexMatcher.matches(description));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
