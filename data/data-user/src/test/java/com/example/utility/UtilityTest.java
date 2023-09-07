package com.example.utility;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UtilityTest {

    @Test
    void makeVerificationKey() {
        String s = Utility.makeVerificationKey();

        assertTrue(s.matches("[a-zA-Z0-9]{6}"));
    }
}