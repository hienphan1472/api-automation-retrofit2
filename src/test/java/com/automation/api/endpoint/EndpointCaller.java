package com.automation.api.endpoint;

import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

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
                // Return null in the case of an expected message. Error details should be
                // fetched from lastResponseCode and lastErrorJson in this case.
                return null;

            } else {
                System.out.println("res " + response.body());
                return response.body();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public void beforeScenario() {
        lastResponseCode = -1;
        expectingErrorResponse = false;
        lastErrorJson = null;
    }
}
