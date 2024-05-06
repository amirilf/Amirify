package app.util;

import java.util.Random;

public class RandomGenerator {

    private static String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_";
    private static int charactersLength = characters.length();

    public static String randomIDGenerator(int length, int id, int default_digits) {

        StringBuilder sb = new StringBuilder();
        Random r = new Random();

        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(r.nextInt(charactersLength)));
        }

        sb.insert(r.nextInt(length - 1), String.format("%0" + default_digits + "d", id));

        return sb.toString();
    }
}