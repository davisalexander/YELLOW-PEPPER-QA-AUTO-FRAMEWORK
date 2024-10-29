package com.api.datahelper;

import com.api.utils.PropertiesFile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import lombok.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;
import java.io.*;
import java.nio.charset.Charset;
import java.util.UUID;

@NonNull
public class AbstractDataHelper {

    private static String dataPath = new File(PropertiesFile.getProperty("test.data.path")).getAbsolutePath()+File.separator;
    private static JSONParser parser = new JSONParser();
    private static Object body;

    public static final ObjectMapper MAPPER = new ObjectMapper();

    public static String toJson(Object object) throws JsonProcessingException {
        try {
            return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (Throwable ex) {
            throw ex;
        }
    }

    public static <T> T jsonStrToDto(String jsonStr, Class<T> type) throws JsonProcessingException {
        return MAPPER.readValue(jsonStr, type);
    }

    public static String readFile(Resource resource){
        try {
            Reader reader = new InputStreamReader(resource.getInputStream(), Charset.defaultCharset());

            String container;
            try {
                container = FileCopyUtils.copyToString(reader);
            } catch (Throwable containerException){
                try {
                    reader.close();
                } catch (Throwable closeException){
                    containerException.addSuppressed(closeException);
                }
                throw containerException;
            }
            reader.close();
            return container;
        } catch (IOException exception){
            throw new UncheckedIOException(exception);
        }
    }

    public static String generateUuid() {
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();

        return uuidAsString;
    }
    public static String getRequestBody(String jsonFileName, String jsonKey) {
        try {
            body = ((JSONObject)parser.parse(new FileReader(dataPath+jsonFileName))).get(jsonKey);
            if (body == null) {
                throw new RuntimeException("NO DATA FOUND in JSON file '" + jsonFileName +"' for key '"+jsonKey+"'");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("JSON file not found at path: " + dataPath+jsonFileName);
        } catch (IOException e) {
            throw new RuntimeException("IOException while reading file: " + jsonFileName);
        } catch (ParseException e) {
            throw new RuntimeException("Parse Exception occured while Parsing: " + jsonFileName);
        }
        return body.toString();
    }

    public static String getRequestBody(String jsonFileName) {
        try {
            body = ((JSONObject)parser.parse(new FileReader(dataPath+jsonFileName)));
            if (body == null) {
                throw new RuntimeException("NO DATA FOUND in JSON file '" + jsonFileName +"' for key'");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("JSON file not found at path: " + dataPath+jsonFileName);
        } catch (IOException e) {
            throw new RuntimeException("IOException while reading file: " + jsonFileName);
        } catch (ParseException e) {
            throw new RuntimeException("Parse Exception occured while Parsing: " + jsonFileName);
        }
        return body.toString();
    }

    public static <T> T  deserializedResponse(Response response, Class T ){
        ObjectMapper mapper = new ObjectMapper();
        T responseDeserialized = null;
        try {
            responseDeserialized = (T) mapper.readValue(response.asString(), T);
            String jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseDeserialized); // Pretty print JSON
            System.out.println("Handling Response: \n"+responseDeserialized.toString());
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return responseDeserialized;
    }
}
