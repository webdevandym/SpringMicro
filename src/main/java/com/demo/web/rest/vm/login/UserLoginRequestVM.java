package com.demo.web.rest.vm.login;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserLoginRequestVM {

    @NotNull
    private String userName;
    @NotNull
    @Size(min = 5, max = 100)
    private String password;

    public UserLoginRequestVM() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserLoginRequestVM{" +
                "userName='" + userName + '\'' +
                '}';
    }
}
