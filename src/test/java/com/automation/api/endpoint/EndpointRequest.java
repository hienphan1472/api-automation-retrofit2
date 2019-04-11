package com.automation.api.endpoint;

import com.fasterxml.jackson.databind.JsonNode;
import org.json.simple.JSONObject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class EndpointRequest {

    private static EndpointRequest _instance;

    private JSONObject json;
    private JSONObject headers;

    public static EndpointRequest get() {
        if (_instance == null) {
            _instance = new EndpointRequest();
        }

        return _instance;
    }

    public void beforeScenario() {
        json = new JSONObject();
    }

    public void putHeader(String headerAttribute, String headerValue) {
        headers.put(headerAttribute, headerValue);
    }

    public void resetHeaders() {

        headers = new JSONObject();
        headers.put("User-Agent", null);
        headers.put("X-Vibe-TimeZone", null);
        headers.put("X-VibeSDK-Version", null);
        headers.put("X-VibeSDK-Build", null);
        headers.put("Accept-Language", null);

    }

    public void setJson(JSONObject json) {
        this.json = json;
    }

    public JSONObject getJson() {
        return json;
    }

    public String getHeader(String key) {
        Object value = headers.get(key);
        if (value != null) {
            return value.toString();
        } else {
            return null;
        }
    }

    public void putJson(Object key, Object value) {
        json.put(key, value);
    }

    public void verifyAllKeysPresent(JsonNode node) {

        for (Object o : json.keySet()) {
            String key = o.toString();
            assertEquals(json.get(key), node.get(key).asText());
        }

    }

    public void verifyNoKeysPresent(JsonNode node) {

        for (Object o : json.keySet()) {
            String key = o.toString();
            if (node.get(key) != null) {
                assertNotEquals(json.get(key), node.get(key).asText());
            }
        }

    }
}
