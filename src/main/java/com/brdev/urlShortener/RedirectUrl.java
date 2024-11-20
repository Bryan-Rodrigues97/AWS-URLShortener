package com.brdev.urlShortener;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.brdev.urlShortener.Controller.Request.CreateShortUrlRequest;
import com.brdev.urlShortener.Utils.S3BucketIntegration;
import com.brdev.urlShortener.Utils.ShortenerUrlHelper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RedirectUrl implements RequestHandler <Map<String, Object>, Map<String, Object>> {

    @Override
    public Map<String, Object> handleRequest(Map<String, Object> input, Context context) {
        String fileUUID = input.get("rawPath").toString().replace("/", "");

        if (fileUUID.isEmpty()){
            throw new IllegalArgumentException("Invalid Input: 'shortUrlCode' is required.");
        }

        CreateShortUrlRequest fileData;
        try {
            fileData = ShortenerUrlHelper.streamToShortUrlRequest(S3BucketIntegration.getFile(fileUUID));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (fileData.expirationTime() < System.currentTimeMillis()/1000) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", 410);
            response.put("body", "This URL has expired");
            return response;
        }

        return ShortenerUrlHelper.makeRedirectResponse(fileData.originalUrl());
    }
}
