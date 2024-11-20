package com.brdev.urlShortener.Utils;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.InputStream;

public class S3BucketIntegration {
    public static boolean createFile(String jsonData, String shortUrlCode) {
        try {
            PutObjectRequest putRequest = PutObjectRequest
                    .builder()
                    .bucket("brdev-url-shortener-storage")
                    .key(String.format("%s.json",shortUrlCode))
                    .build();

            S3Client.builder().build().putObject(putRequest, RequestBody.fromString(jsonData));
        }
        catch (Exception e) {
            throw new RuntimeException("Error while saving data to S3", e);
        }
        return true;
    }

    public static InputStream getFile(String shortUrlCode) {
        try {
            GetObjectRequest getRequest = GetObjectRequest
                    .builder()
                    .bucket("brdev-url-shortener-storage")
                    .key(String.format("%s.json", shortUrlCode))
                    .build();

            return S3Client.builder().build().getObject(getRequest);
        }
        catch (Exception e) {
            throw new RuntimeException("Error while reading data from S3", e);
        }
    }
}
