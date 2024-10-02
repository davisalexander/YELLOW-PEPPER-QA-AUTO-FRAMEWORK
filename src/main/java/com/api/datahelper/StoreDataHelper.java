package com.api.datahelper;

import com.api.utils.JsonReader;
import com.fasterxml.jackson.core.JsonProcessingException;

public class StoreDataHelper extends AbstractDataHelper{
    public String getCreateOrderPayload() throws JsonProcessingException {
        return JsonReader.getRequestBody("store_order_create_body.json");
    }
    public String getUpdateStorePayload() throws JsonProcessingException {
        return JsonReader.getRequestBody("store_order_update_body.json");
    }
}
