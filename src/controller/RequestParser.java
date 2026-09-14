package controller;

import java.util.HashMap;
import java.util.Map;

public class RequestParser {

    public static Map<String, String> parse(String paramsBlock) {

        Map<String, String> params = new HashMap<>();

        if (paramsBlock == null || paramsBlock.isBlank()) {
            return params;
        }

        String[] lines = paramsBlock.split("\n");

        for (String line : lines) {
            if (line.isBlank()) {
                continue;
            }

            String[] pair = line.split("=", 2);

            String key = pair[0].trim();
            String value = pair.length > 1 ? pair[1].trim() : "";

            params.put(key, value);
        }

        return params;
    }
}


