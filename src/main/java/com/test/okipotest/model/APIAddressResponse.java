package com.test.okipotest.model;

import java.util.List;

public class APIAddressResponse {

    private int status;

    private String message;

    private String result;

    public APIAddressResponse() {
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
