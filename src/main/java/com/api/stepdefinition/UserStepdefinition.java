package com.api.stepdefinition;

import com.api.model.PetRequestDTO;
import com.api.model.PetResponseDTO;
import com.api.model.UserRequestDTO;
import com.api.model.UserResponseDTO;
import com.api.utils.ResponseHandler;
import com.api.utils.TestDataUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.api.datahelper.UserDataHelper;
import com.api.utils.TestContext;
import io.cucumber.java.en.*;
import org.junit.Assert;
import static org.junit.Assert.assertNotNull;

public class UserStepdefinition {

    private final UserDataHelper userDataHelper;
    private final TestContext context;

    public UserStepdefinition(TestContext context, UserDataHelper userDataHelper) {
        this.context = context;
        this.userDataHelper = userDataHelper;
    }
    @When("user performs a request to create a new user {string}")
    public void userCreateUser(String userName) throws JsonProcessingException {
        context.session.put("userName", userName);

        String userPayload = userDataHelper.getCreateUserPayload();

        context.response = context.requestSetup().body(userPayload)
                .when().post(context.session.get("endpoint").toString());

        UserResponseDTO userResponseDTO = TestDataUtil.jsonStrToDto(context.response.body().prettyPrint(), UserResponseDTO.class);
        context.session.put("userId", userResponseDTO.getId());
        context.session.put("userName", userResponseDTO.getUsername());
        context.session.put("userPassword", userResponseDTO.getPassword());
        Assert.assertEquals(200,context.response.getStatusCode());
        Assert.assertEquals(context.session.get("userId"),userResponseDTO.getId());
    }

    @When("user performs a request to get user login session")
    public void userGetLogin() {
        context.response = context.requestSetup()
                .queryParams("userName", context.session.get("userName"))
                .queryParams("password", context.session.get("userPassword"))
                .when().get(context.session.get("endpoint").toString());

        assertNotNull("Log in session not found", context.response.body());
        Assert.assertEquals(200,context.response.getStatusCode());
    }


    @When("user performs a request to get user logout")
    public void userGetLogout() {
        context.response = context.requestSetup()
                .when().get(context.session.get("endpoint").toString());

        assertNotNull("Log out did not work", context.response.body());
        Assert.assertEquals(200,context.response.getStatusCode());
    }

    @Then("user should get their login session")
    public void userGetLogIn() {
        assertNotNull("User session was not found", context.response);
    }

    @And("user updates the user {string}")
    public void userUpdateUser(String userName) throws JsonProcessingException {
        context.session.put("userName", userName);
        String payload = userDataHelper.getUpdateUserPayload();
        UserRequestDTO userRequestDTO = TestDataUtil.jsonStrToDto(payload, UserRequestDTO.class );
        context.response = context.requestSetup()
                .body(payload)
                .when().put(context.session.get("endpoint").toString()+userName);
        System.out.println("CONSOLEeeeeeeeee"+context.session.get("endpoint").toString());
        UserResponseDTO userResponseDTO = ResponseHandler.deserializedResponse(context.response, UserResponseDTO.class);
        assertNotNull("User details were not updated", userResponseDTO);
        Assert.assertEquals(userResponseDTO.getFirstName(), userRequestDTO.getFirstName());
    }

    @And("user deletes the user {string}")
    public void userDeleteUser(String userName){

        context.response = context.requestSetup()
                .when().delete(context.session.get("endpoint").toString()+userName);

        assertNotNull("Pet was not deleted", context.response);
    }
}


