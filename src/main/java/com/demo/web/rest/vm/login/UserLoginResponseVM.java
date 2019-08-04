package com.demo.web.rest.vm.login;

public class UserLoginResponseVM {
    private String token;

    public UserLoginResponseVM(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
