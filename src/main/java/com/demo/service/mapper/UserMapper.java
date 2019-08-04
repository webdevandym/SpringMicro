package com.demo.service.mapper;

import com.demo.domain.User;
import com.demo.web.rest.vm.register.UserRegisterRequestVM;
import com.demo.web.rest.vm.register.UserRegisterResponseVM;

public class UserMapper {

    public static User toEntity(UserRegisterRequestVM userRegisterRequestVM) {
        User user = new User();
        user.setFirstName(userRegisterRequestVM.getFirstName());
        user.setLastName(userRegisterRequestVM.getLastName());
        user.setUserName(userRegisterRequestVM.getUserName().trim().toLowerCase());

        return user;
    }

    public static UserRegisterResponseVM toResponse(User user) {
        UserRegisterResponseVM userRegisterResponseVM = new UserRegisterResponseVM();
        userRegisterResponseVM.setId(user.getId());
        userRegisterResponseVM.setFirstName(user.getFirstName());
        userRegisterResponseVM.setLastName(user.getLastName());
        userRegisterResponseVM.setUserName(user.getUserName());

        return userRegisterResponseVM;
    }
}
