package com.automation.support;

import okhttp3.ResponseBody;

import java.io.IOException;

public class RetrofitUtils {

    public static String responseToString(ResponseBody response) {
        try {
            return response.string();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
