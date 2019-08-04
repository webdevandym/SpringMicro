package com.demo.security;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class BCryptService {
    private final int logRounds;

    public BCryptService(Environment env) {
        this.logRounds = Integer.valueOf(Objects.requireNonNull(env.getProperty("application.bCrypt.logRounds")));
    }

    public String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(logRounds));
    }

    public boolean verifyHash(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}
