package com.mongodb.exer.jsonparser;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * JSON parser which traverses and flattens
 * the input json object
 */
public class CustomJsonParser {
    private Gson gson;
    private JsonParser parser;

    public CustomJsonParser() {
        gson = new Gson();
        parser  = new JsonParser();
    }

    private Map<String, Object> traverseAndSerialize(JsonObject jsonTree) {
        Map<String, Object> result = new LinkedHashMap<>();
        Map<String, Object> elementMap = gson.fromJson(jsonTree, new TypeToken<Map<String, Object>>(){}.getType());
        for(String key: elementMap.keySet()) {
            JsonElement value = jsonTree.get(key);
            if(value.isJsonObject()) {
                Map<String, Object> nestedMap = traverseAndSerialize(value.getAsJsonObject());
                for(String k : nestedMap.keySet()) {
                    result.putIfAbsent(key.concat(".").concat(k), nestedMap.get(k));
                }
            } else if (value.isJsonNull()){
                result.putIfAbsent(key, value.getAsJsonNull());
            } else if (value.isJsonArray()) {
                result.putIfAbsent(key, value.getAsJsonArray());
            } else {
                result.putIfAbsent(key, value.getAsJsonPrimitive());
            }
        }
        return result;
    }

    public String flatten(String json) {
        JsonObject jsonTree = parser.parse(json).getAsJsonObject();
        Map<String, Object> flattenedMap = traverseAndSerialize(jsonTree);
        return gson.toJson(flattenedMap);
    }
}
