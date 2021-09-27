package com.mongodb.exer.jsonparser;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        if(args.length < 1) {
            throw new IllegalArgumentException("Expected one argument file name");
        }
        JsonFileDataSource dataSource = new JsonFileDataSource(args[0]);
        CustomJsonParser cp = new CustomJsonParser();
        String data = dataSource.read();
        System.out.println(data);
        System.out.println(cp.flatten(data));
    }
}
