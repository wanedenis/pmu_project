package com.pmu.pmu_project.models;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class MyResponse {
    public static ResponseEntity<Object> response(HttpStatus status, String message, Object data) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", status.value());
        map.put("message", message);
        map.put("data", data);
        return new ResponseEntity<>(map, status);
    }
}
