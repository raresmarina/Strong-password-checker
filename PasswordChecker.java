import java.util.HashSet;
import java.util.Set;

public class PasswordChecker {
    private static final int MIN_LENGTH = 6;
    private static final int MAX_LENGTH = 20;

    private String password;

    public PasswordChecker(String password) {
        this.password = password;
    }

    public int minimumChanges() {
    int missingChars = countMissingChars();
    int repeatingChars = countRepeatingChars();
    int totalChanges = missingChars + repeatingChars;
    int passwordLength = password.length();

    if (passwordLength <= MAX_LENGTH) {
        return Math.max(totalChanges, MIN_LENGTH - passwordLength);
    } else {
        int excessChars = passwordLength - MAX_LENGTH;
        int removalsNeeded = excessChars - totalChanges;
        return Math.max(MIN_LENGTH - passwordLength, removalsNeeded);
    }
}


    private int countMissingChars() {
        int missingChars = 0;

        if (!password.matches(".*[a-z].*")) {
            missingChars++;
        }

        if (!password.matches(".*[A-Z].*")) {
            missingChars++;
        }

        if (!password.matches(".*\\d.*")) {
            missingChars++;
        }

        return missingChars;
    }

    private int countRepeatingChars() {
        int repeatingChars = 0;
        char prev = '\0';
        int count = 0;
        Set<Character> repeating = new HashSet<>();

        for (char c : password.toCharArray()) {
            if (c == prev) {
                count++;
                if (count == 3) {
                    repeatingChars++;
                    count = 0;
                    prev = c;
                }
            } else {
                count = 1;
                prev = c;
            }
        }

       

        return repeatingChars;
    }
}
