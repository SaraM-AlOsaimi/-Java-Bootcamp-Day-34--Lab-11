package com.example.blogsystem.API;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ApiResponse {

    private String message;

    // Constructor that accepts a String
    public ApiResponse(String message) {
        this.message = message;
    }
}
