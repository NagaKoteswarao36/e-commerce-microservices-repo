package com.ecommerce.common.dto;

public record ApiResponse<T>(boolean success, String message, T data) {
    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(true, "SUCCESS", data);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, message, null);
    }
}
