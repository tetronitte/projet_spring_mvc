package fr.dawan.projet_spring_mvc.tools;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordHasher {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public PasswordHasher() {
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    public String encode(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    public boolean matches(String password, String encodedPassword) {
        return bCryptPasswordEncoder.matches(password, encodedPassword);
    }
}
