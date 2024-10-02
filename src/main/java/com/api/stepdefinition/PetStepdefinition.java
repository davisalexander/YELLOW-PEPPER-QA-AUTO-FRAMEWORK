package com.api.stepdefinition;

import static org.junit.Assert.*;
import com.api.model.PetRequestDTO;
import com.api.utils.ResponseHandler;
import com.api.utils.TestDataUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import com.api.model.PetResponseDTO;
import com.api.datahelper.PetDataHelper;
import com.api.utils.TestContext;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class PetStepdefinition {

    private final PetDataHelper petDataHelper;
    private final TestContext context;
    public PetStepdefinition(TestContext context, PetDataHelper petDataHelper) {
        this.context = context;
        this.petDataHelper = petDataHelper;
    }

    @When("user performs a request to create a new {string} pet")
    public void userCreatePet(String petName) throws JsonProcessingException {
        context.session.put("petName", petName);
        String payloadPet = petDataHelper.getCreatePetPayload();
        PetRequestDTO petRequestDTO = TestDataUtil.jsonStrToDto(payloadPet, PetRequestDTO.class );
        context.session.put("petId", petRequestDTO.getId());
        context.response = context.requestSetup().body(payloadPet)
                .when().post(context.session.get("endpoint").toString());

        PetResponseDTO petResponseDTO = TestDataUtil.jsonStrToDto(context.response.body().prettyPrint(), PetResponseDTO.class);
        Assert.assertEquals(200,context.response.getStatusCode());
        Assert.assertEquals(context.session.get("petName"),petResponseDTO.getName());
    }

    @When("user performs a request to get pet {string} by id")
    public void userGetPetById(String petId) throws JsonProcessingException {
        context.session.put("petId", petId);

        context.response = context.requestSetup()
                .queryParams("petId", context.session.get("petId"))
                .when().get(context.session.get("endpoint").toString()+petId);

        PetResponseDTO petResponseDTO = TestDataUtil.jsonStrToDto(context.response.body().prettyPrint(), PetResponseDTO.class);

        Assert.assertEquals(200,context.response.getStatusCode());
        Assert.assertEquals(context.session.get("petId"),petResponseDTO.getId());    }

    @When("user performs a request to get pet by status {string}")
    public void userGetPetByStatus(String status) {
        context.session.put("status", status);
        context.response = context.requestSetup()
                .queryParams("status", context.session.get("status"))
                .when().get(context.session.get("endpoint").toString());

        Response response = context.response;
        assertNotNull("No pets were found", response);
        Assert.assertEquals(200,context.response.getStatusCode());
    }

    @And("user updates the pet {string}")
    public void userUpdatePet(String petId) throws JsonProcessingException {

        String payload = petDataHelper.getUpdatePetPayload();
        PetRequestDTO petRequestDTO = TestDataUtil.jsonStrToDto(payload, PetRequestDTO.class );
        context.response = context.requestSetup()
                .body(payload)
                .when().put(context.session.get("endpoint").toString());

        PetResponseDTO petResponseDTO = ResponseHandler.deserializedResponse(context.response, PetResponseDTO.class);
        assertNotNull("Pet details were not updated", petResponseDTO);
        Assert.assertEquals(petResponseDTO.getName(), petRequestDTO.getName());
    }

    @And("user deletes the pet {string}")
    public void userDeletePet(String petId){

        context.response = context.requestSetup()
                .pathParam("petId", context.session.get("petId"))
                .when().delete(context.session.get("endpoint").toString()+"/{petId}");

        assertNotNull("Pet was not deleted", context.response);
    }
}