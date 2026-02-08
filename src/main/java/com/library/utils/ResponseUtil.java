package com.library.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {

    public static ResponseEntity<Map<String, Object>> createSuccessResponse(String message, Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", message);
        response.put("data", data);
        response.put("timestamp", System.currentTimeMillis());
        return ResponseEntity.ok(response);
    }

    public static ResponseEntity<Map<String, Object>> createErrorResponse(String message, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", message);
        response.put("timestamp", System.currentTimeMillis());
        return new ResponseEntity<>(response, status);
    }

    public static Map<String, Object> createApiResponse(boolean success, String message, Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", success);
        response.put("message", message);
        response.put("data", data);
        response.put("timestamp", System.currentTimeMillis());
        return response;
    }

    public static ResponseEntity<Map<String, Object>> created(Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Resource created successfully");
        response.put("data", data);
        response.put("timestamp", System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public static ResponseEntity<Map<String, Object>> updated(Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Resource updated successfully");
        response.put("data", data);
        response.put("timestamp", System.currentTimeMillis());
        return ResponseEntity.ok(response);
    }

    public static ResponseEntity<Map<String, Object>> deleted() {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Resource deleted successfully");
        response.put("timestamp", System.currentTimeMillis());
        return ResponseEntity.ok(response);
    }

    public static ResponseEntity<Map<String, Object>> notFound(String resource) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", resource + " not found");
        response.put("timestamp", System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}







