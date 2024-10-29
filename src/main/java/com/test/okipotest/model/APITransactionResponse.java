package com.test.okipotest.model;

import java.util.List;

public class APITransactionResponse {
    private int status;

    private String message;

    List<Result> result;

    public APITransactionResponse() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> results) {
        this.result = results;
    }
}
