package com.automation.steps;

import com.automation.api.endpoint.EndpointCaller;
import com.automation.api.endpoint.EndpointRequest;
import com.automation.api.endpoint.Endpoints;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {

    // ----- Set up and tear down
    protected EndpointCaller caller = EndpointCaller.get();
    protected Endpoints endpoints = Endpoints.get();
    protected EndpointRequest request = EndpointRequest.get();

    @Before
    public void beforeScenario() throws Exception {
        caller.beforeScenario();
        endpoints.beforeScenario();
        request.beforeScenario();
        request.resetHeaders();
    }

    @After
    public void afterScenario() throws Exception {
        // We don't do anything currently.
    }

}
