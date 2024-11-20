package com.brdev.urlShortener;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.brdev.urlShortener.Controller.Request.CreateShortUrlRequest;
import com.brdev.urlShortener.Controller.Response.CreateShortUrlResponse;
import com.brdev.urlShortener.Utils.S3BucketIntegration;
import com.brdev.urlShortener.Utils.ShortenerUrlHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import software.amazon.awssdk.services.s3.S3Client;

import java.util.Map;

public class CreateShortUrl implements RequestHandler<Map<String, Object>, CreateShortUrlResponse> {
    private final ObjectMapper mapper = new ObjectMapper();
    private final S3Client s3Client = S3Client.builder().build();

    @Override
    public CreateShortUrlResponse handleRequest(Map<String, Object> input, Context context) {
        try {
            CreateShortUrlRequest request = ShortenerUrlHelper.bodyToShortenerUrlRequest(input);
            String shortUrlCode = ShortenerUrlHelper.generateShortUrl();
            boolean success = S3BucketIntegration.createFile(mapper.writeValueAsString(request), shortUrlCode);

            if (success) {
                return CreateShortUrlResponse
                        .builder()
                        .code(shortUrlCode)
                        .build();
            }
        }
        catch (JsonProcessingException e) {
            throw new RuntimeException("Error while parsing json", e);
        }
        catch (Exception e) {
            throw new RuntimeException("Error while handling request", e);
        }

        return null;
    }
}