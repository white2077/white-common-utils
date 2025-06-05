package common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JsonUtils {
    private static final ObjectMapper mapper = new ObjectMapper();

    private JsonUtils() {
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static String objectToJson(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    public static <T> T jsonToObject(String json, Class<T> clazz) throws JsonProcessingException {
        return mapper.readValue(json, clazz);
    }

    public static <T> List<T> jsonToList(String json, Class<T> clazz) throws JsonProcessingException {
        return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }

    public static boolean isValidJson(String json) {
        try {
            mapper.readTree(json);
        } catch (JsonProcessingException e) {
            return false;
        }
        return true;
    }

}
