package com.automation.model;

public class SuccessResponse<T> {
    private T data;
    private String status;

    public T getData() {
        return this.data;
    }

    public String getStatus() {
        return this.status;
    }
}
