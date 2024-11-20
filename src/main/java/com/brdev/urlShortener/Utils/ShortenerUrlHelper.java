package com.brdev.urlShortener.Utils;

import com.brdev.urlShortener.Controller.Request.CreateShortUrlRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ShortenerUrlHelper {
    public static String generateShortUrl() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public static CreateShortUrlRequest bodyToShortenerUrlRequest(Map<String, Object> input) throws JsonProcessingException {
        return new ObjectMapper().readValue((String) input.get("body"), CreateShortUrlRequest.class);
    }

    public static CreateShortUrlRequest streamToShortUrlRequest(InputStream stream) throws IOException {
        return new ObjectMapper().readValue(stream, CreateShortUrlRequest.class);
    }

    public static Map<String, Object> makeRedirectResponse(String originalUrl) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Location", originalUrl);

        Map<String, Object> response = new HashMap<>();
        response.put("statusCode", 302);
        response.put("headers", headers);

        return response;
    }
}
