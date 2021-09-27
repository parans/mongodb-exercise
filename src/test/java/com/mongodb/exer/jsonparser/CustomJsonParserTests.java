package com.mongodb.exer.jsonparser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CustomJsonParserTests {

    @Test
    public void testBasicUseCase() throws IOException {
        InputStream stream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("simple.json");
        String json = IOUtils.toString(stream);
        CustomJsonParser cp = new CustomJsonParser();
        Gson gson = new Gson();
        String jsonData = cp.flatten(json);
        Map<String, Object> elementMap = gson.fromJson(jsonData, new TypeToken<Map<String, Object>>(){}.getType());
        assertTrue(elementMap.containsKey("c.f.a"));
        assertTrue(elementMap.containsKey("c.f.b"));
        assertTrue(elementMap.containsKey("x.flatten"));
    }

    @Test
    public void testComplexJsonUseCase() throws IOException {
        InputStream stream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("sample.json");
        String json = IOUtils.toString(stream);
        Gson gson = new Gson();
        CustomJsonParser cp = new CustomJsonParser();
        String jsonData = cp.flatten(json);
        Map<String, Object> elementMap = gson.fromJson(jsonData, new TypeToken<Map<String, Object>>(){}.getType());
        assertTrue(elementMap.containsKey("streamSourceConfig.connectionConfig.systemName"));
    }
}
