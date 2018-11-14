package com.jorgegatica.snomedbrowser.util;

public class ApiError {
    private int statusCode;
    private String endpoint;
    private String message = "Error desconocido :(";

    public int getStatusCode() {
        return statusCode;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getMessage() {
        return message;
    }


}
