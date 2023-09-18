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
    private final String VERIFY_LATTER_COLLECTION = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public String encodePassword(String rawPassword) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(rawPassword);
    }

    public boolean comparePassword(String rawPassword, String encodedPassword) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public LocalDate parseDate(String s) {

        return LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    }

    public String makeVerificationKey() {
        StringBuilder verificationKeyBuilder = new StringBuilder();
        SecureRandom random = new SecureRandom();

        for(int i = 0; i < KEY_LENGTH; i++) {
            char randomChar = VERIFY_LATTER_COLLECTION.charAt(random.nextInt(VERIFY_LATTER_COLLECTION.length()));
            verificationKeyBuilder.append(randomChar);
        }

        return verificationKeyBuilder.toString();
    }
}
