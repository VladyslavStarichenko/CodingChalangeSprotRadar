package sprot.radar.util;

import java.util.regex.Pattern;

import static org.apache.commons.lang3.RandomStringUtils.random;

public class AppUtil {

    /**
     * Library usedRandomStringUtils is using for unique id generation.
     *
     * @return a unique id for a class instance
     */
    public String generateId() {
        final int idLength = 6;
        return random(idLength, true, true);
    }


    /**
     * Method validates the value is of alphanumerical characters and spaces.
     *
     * @param value is value to validate
     * @return boolean value whether the name is of alphanumeric characters
     */
    public boolean isValidInput(String value) {
        String pattern = "^[a-zA-Z0-9]*$";
        return Pattern.matches(pattern, value);
    }

    public boolean isValidId(String id) {
        String pattern = "^[a-zA-Z0-9]*$";
        return id.length() == 6 && Pattern.matches(pattern, id);
    }

    public boolean isValidScore(int score) {
        return score == -1;
    }

}
