package com.example.steamkey.common;

import java.util.Random;

public class KeyGenerator {
    public static String generateKey() {
        StringBuilder keyBuilder = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            keyBuilder.append(generateRandomPart());
            if (i < 2) {
                keyBuilder.append("-");
            }
        }

        return keyBuilder.toString();
    }

    public static String generateRandomPart() {
        Random random = new Random();
        StringBuilder partBuilder = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            char randomChar = (char) (random.nextInt(26) + 'A');
            partBuilder.append(randomChar);
        }

        return partBuilder.toString();
    }
}