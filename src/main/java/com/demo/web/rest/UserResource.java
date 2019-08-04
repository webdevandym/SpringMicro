package com.demo.web.rest;

import com.demo.domain.User;
import com.demo.service.UserService;
import com.demo.service.mapper.UserMapper;
import com.demo.web.rest.vm.login.UserLoginRequestVM;
import com.demo.web.rest.vm.login.UserLoginResponseVM;
import com.demo.web.rest.vm.register.UserRegisterRequestVM;
import com.demo.web.rest.vm.register.UserRegisterResponseVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userservice")
public class UserResource {

    private static final Logger log = LoggerFactory.getLogger(UserResource.class);

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(path = "/register")
    public ResponseEntity<UserRegisterResponseVM> register(@RequestBody @Validated UserRegisterRequestVM userRegisterRequestVM) {
        log.debug("REST request to save user {}", userRegisterRequestVM);
        User user = userService.verifyAndSave(userRegisterRequestVM);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(UserMapper.toResponse(user));
    }

    @PostMapping(path = "/authenticate")
    public ResponseEntity<UserLoginResponseVM> authenticate(@RequestBody @Validated UserLoginRequestVM userLoginRequestVM) {
        log.debug("REST request authentication with user name '{}", userLoginRequestVM.getUserName());
        String token = userService.verifyAndGenerateToken(userLoginRequestVM);

        return ResponseEntity.ok(new UserLoginResponseVM(token));
    }

}
