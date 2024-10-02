package com.api.utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;
import java.util.UUID;


public final class TestDataUtil {
    public static final ObjectMapper MAPPER = new ObjectMapper();

    public static String toJson(Object object) throws JsonProcessingException {
        try {
            return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (Throwable ex) {
            throw ex;
        }
    }

    public static <T> T parseJson(String jsonStr, Class<T> type) throws JsonProcessingException {
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
}
