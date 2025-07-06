package com.warehouse.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashTest {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = encoder.encode("admin123");
        System.out.println("Hash: " + hash);
    }
}
