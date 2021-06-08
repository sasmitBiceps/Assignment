package com.example.demo.util.responses;

import org.springframework.http.HttpStatus;

public class Response
{
    private HttpStatus httpStatus;
    private String response;
    private boolean success;

    public String getResponse() {
        return response;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
