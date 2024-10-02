package com.api.datahelper;

import com.api.utils.JsonReader;
import com.fasterxml.jackson.core.JsonProcessingException;

public class UserDataHelper extends AbstractDataHelper{
    public String getCreateUserPayload() throws JsonProcessingException {
        return JsonReader.getRequestBody("user_create_body.json");
    }
    public String getUpdateUserPayload() throws JsonProcessingException {
        return JsonReader.getRequestBody("user_update_body.json");
    }
}
