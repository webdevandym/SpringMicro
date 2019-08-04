package com.demo.web.rest.error;

import java.io.Serializable;

public class RequestErrorVM implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String code;
    private final String description;


    public RequestErrorVM(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
