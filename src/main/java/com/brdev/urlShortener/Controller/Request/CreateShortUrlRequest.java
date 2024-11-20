package com.brdev.urlShortener.Controller.Request;

public record CreateShortUrlRequest (String originalUrl, Long expirationTime) {
}
