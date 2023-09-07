package com.example.utility;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@UtilityClass
public class Utility {
    private final int KEY_LENGTH = 6;
    private final int LOWER_BOUND = '0';
    private final int DIF_BOUND = 'z' - '0';

    public String encodePassword(String raw) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(raw);
    }

    public LocalDate parseDate(String s) {

        return LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    }

    public String makeVerificationKey() {
        StringBuilder verificationKeyBuilder = new StringBuilder();
        SecureRandom random = new SecureRandom();

        for(int i = 0; i < KEY_LENGTH; i++) {
            char randomChar = (char) (random.nextInt(DIF_BOUND) + LOWER_BOUND);
            verificationKeyBuilder.append(randomChar);
        }

        return verificationKeyBuilder.toString();
    }
}
