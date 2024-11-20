package com.brdev.urlShortener.Controller.Response;

import lombok.Builder;

@Builder
public record CreateShortUrlResponse(String code) {
}
