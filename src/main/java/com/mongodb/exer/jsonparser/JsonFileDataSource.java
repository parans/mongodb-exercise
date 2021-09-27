package com.mongodb.exer.jsonparser;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Utility class to read data from a source
 */
public class JsonFileDataSource {
    private File fp;

    public JsonFileDataSource(final String fileName) {
        if(fileName == null || fileName.isBlank()) {
            throw new IllegalArgumentException("Invalid argument, filename");
        }
        this.fp = new File(fileName);
    }

    public String read() throws IOException {
        return FileUtils.readFileToString(fp);
    }
}
