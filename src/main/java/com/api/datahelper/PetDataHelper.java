package com.api.datahelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.api.utils.JsonReader;

public class PetDataHelper extends AbstractDataHelper{

    public String getCreatePetPayload() throws JsonProcessingException {
        return JsonReader.getRequestBody("pet_create_body.json");
    }
    public String getUpdatePetPayload() throws JsonProcessingException {
        return JsonReader.getRequestBody("pet_update_body.json");
    }
}
