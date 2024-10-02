package com.api.stepdefinition;

import com.api.model.StoreOrderRequestDTO;
import com.api.utils.TestDataUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.api.model.StoreOrderResponseDTO;
import com.api.datahelper.StoreDataHelper;
import com.api.utils.TestContext;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.junit.Assert;

import static org.junit.Assert.assertNotNull;

public class StoreStepdefinition {

    private final StoreDataHelper storeDataHelper;
    private final TestContext context;

    public StoreStepdefinition(TestContext context, StoreDataHelper storeDataHelper) {
        this.context = context;
        this.storeDataHelper = storeDataHelper;
    }

    @When("user performs a request to create a new order {int}")
    public void userCreateOrder(Integer orderId) throws JsonProcessingException {
        context.session.put("orderId", orderId);
        String payloadOrder = storeDataHelper.getCreateOrderPayload();
        StoreOrderRequestDTO storeRequestDTO = TestDataUtil.jsonStrToDto(payloadOrder, StoreOrderRequestDTO.class );
        context.session.put("orderId", storeRequestDTO.getId());

        context.response = context.requestSetup().body(payloadOrder)
                .when().post(context.session.get("endpoint").toString());

        StoreOrderResponseDTO storeResponseDTO = TestDataUtil.jsonStrToDto(context.response.body().prettyPrint(), StoreOrderResponseDTO.class);
        Assert.assertEquals(200,context.response.getStatusCode());
        Assert.assertEquals(context.session.get("orderId"),storeResponseDTO.getId());
    }
    @When("user performs a request to get order {int} by id")
    public void userGetOrderById(Integer orderId) throws JsonProcessingException {
        context.session.put("orderId", orderId);

        context.response = context.requestSetup()
                .queryParams("orderId", context.session.get("orderId"))
                .when().get(context.session.get("endpoint").toString()+orderId);

        StoreOrderResponseDTO storeOrderResponseDTO = TestDataUtil.jsonStrToDto(context.response.body().prettyPrint(), StoreOrderResponseDTO.class);

        Assert.assertEquals(200,context.response.getStatusCode());
        Assert.assertEquals(context.session.get("orderId"),storeOrderResponseDTO.getId());
    }

    @When("user performs a request to get store inventory")
    public void userGetStoreInventory() {
        context.response = context.requestSetup()
                .when().get(context.session.get("endpoint").toString());

        Response response = context.response;
        assertNotNull("Inventory was not found", response);
    }

    @And("user deletes the order {int}")
    public void userDeleteOrder(Integer orderId){
        context.session.put("orderId", orderId);
        context.response = context.requestSetup()
                .pathParam("orderId", context.session.get("orderId"))
                .when().delete(context.session.get("endpoint").toString()+"/{orderId}");

        assertNotNull("Order was not deleted", context.response);
    }



}