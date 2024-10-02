package com.api.stepdefinition;

import com.api.utils.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import static org.junit.Assert.assertEquals;

public class CommonStepdefinition {
    protected final TestContext context;
    public CommonStepdefinition(TestContext context) {
        this.context = context;
    }
    @Given("user has access to endpoint {string}")
    public void userHasAccessToEndpoint(String endpoint) {
        context.session.put("endpoint", endpoint);
    }
    @Then("user should get the response code {int}")
    public void userGetResponseCode(Integer statusCode) {
        assertEquals(Long.valueOf(statusCode), Long.valueOf(context.response.getStatusCode()));
    }
}
