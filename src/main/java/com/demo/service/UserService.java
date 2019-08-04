package com.demo.service;

import com.demo.domain.User;
import com.demo.repository.UserRepository;
import com.demo.security.BCryptService;
import com.demo.service.exception.BadRequestException;
import com.demo.service.exception.ConflictRequestException;
import com.demo.service.mapper.UserMapper;
import com.demo.web.rest.vm.login.UserLoginRequestVM;
import com.demo.web.rest.vm.register.UserRegisterRequestVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final BCryptService  bCryptService;

    public UserService(UserRepository userRepository,
                       BCryptService bCryptService) {
        this.userRepository = userRepository;
        this.bCryptService = bCryptService;
    }

    public User verifyAndSave(UserRegisterRequestVM userRegisterRequestVM) {
        log.debug("Request for verify and save user {}", userRegisterRequestVM);
        if (checkOnExistsByLogin(userRegisterRequestVM.getUserName())) {
            throw new ConflictRequestException("USER_ALREADY_EXISTS", "A user with the given username already exists");
        }

        User user = UserMapper.toEntity(userRegisterRequestVM);

        return save(user);
    }

    @Transactional(readOnly = true)
    public Boolean checkOnExistsByLogin(String userName) {
        log.debug("Request for verify user exists by login '{}'", userName);
        return userRepository.checkOnExistsByLogin(userName);
    }

    @Transactional
    public User save(User user) {
        log.debug("Request to save user {}", user);
        if (user.getHashedPassword() == null || user.getHashedPassword().trim().isEmpty()) {
            hashPassword(user);
        }
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public String verifyAndGenerateToken(UserLoginRequestVM userLoginRequestVM) {
        log.debug("Request to verify user pass and generate token, for user '{}'", userLoginRequestVM);
        return userRepository.findByUserName(userLoginRequestVM.getUserName())
                             .map(user -> {
                                 if (!bCryptService.verifyHash(userLoginRequestVM.getPassword(), user.getHashedPassword())) {
                                     throw new BadRequestException("INCORRECT_PASSWORD", "The given password is incorrect");
                                 }

                                 //Generate u token
                                 return "TOKEN!";
                             })
                             .orElseThrow(() -> new BadRequestException("USER_NOT_FOUND", "User doesnt exist"));
    }

    private void hashPassword(User user) {
        String hash = bCryptService.hash(user.getPlainTextPassword());
        user.setHashedPassword(hash);
    }
}
