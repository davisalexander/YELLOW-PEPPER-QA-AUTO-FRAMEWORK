package com.api.datahelper;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import static com.api.utils.TestDataUtil.readFile;
import static com.api.utils.TestDataUtil.jsonStrToDto;

@NonNull
public class AbstractDataHelper {

    @Autowired
    protected ResourceLoader resourceLoader;
    @Autowired
    protected String readResource(String relativeResourcePath) {
        try {
            return readFile(resourceLoader.getResource(relativeResourcePath));
        } catch (Exception e) {
            throw e;
        }
    }
    @Autowired
    protected <T> T readResource(String relativeResourcePath, Class<T> type) throws JsonProcessingException {
        String json = readResource(relativeResourcePath);
        return jsonStrToDto(json, type);
    }
}
