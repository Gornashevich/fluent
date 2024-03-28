package utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class RandomDataGenerator {
    public static String getRandomText() {
        // create a string of uppercase and lowercase characters and numbers
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";

        // combine all strings
        String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;

        // create random string builder
        StringBuilder sb = new StringBuilder();

        // create an object of Random class
        Random random = new Random();

        // specify length of random string
        int length = 4;

        for (int i = 0; i < length; i++) {

            // generate random index number
            int index = random.nextInt(alphaNumeric.length());

            // get character specified by index
            // from the string
            char randomChar = alphaNumeric.charAt(index);

            // append the character to string builder
            sb.append(randomChar);
        }

        String randomString = sb.toString();
        return randomString;
    }

    public static int getRandomNumberValue() {
        return Integer.parseInt(RandomStringUtils.randomNumeric(3));
    }
    public static int getRandomNumberValue(int max) {
        return new Random().ints(0, max)
                .findFirst().getAsInt();
    }
    public static int getMinimumRandomNumberValue() {
        return Integer.parseInt(RandomStringUtils.randomNumeric(1));
    }
    public static int getMaximumRandomNumberValue() {
        return Integer.parseInt(RandomStringUtils.randomNumeric(6));
    }
}
