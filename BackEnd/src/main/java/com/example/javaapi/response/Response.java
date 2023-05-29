package com.example.javaapi.response;

import lombok.Data;

import java.util.List;
@Data
public class Response {
    private String message;
    private int statusCode;
    private Object obj;

    public Response(String message, int statusCode, Object obj) {
        this.message = message;
        this.statusCode = statusCode;
        this.obj = obj;
    }
}
