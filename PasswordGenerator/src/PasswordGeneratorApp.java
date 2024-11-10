import java.util.Scanner;
import java.util.Random;

public class PasswordGeneratorApp {

    private static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_=+[]{}|;:'\",.<>?/`~";

    public static void main(String[] args) {
        getUserInputAndGeneratePassword();
    }

    public static String generatePassword(int length, boolean includeUppercase, 
            boolean includeLowercase, boolean includeNumbers, boolean includeSpecial) {
        StringBuilder characterPool = new StringBuilder();
        Random random = new Random();

        if (includeUppercase) {
            characterPool.append(UPPERCASE_LETTERS);
        }
        if (includeLowercase) {
            characterPool.append(LOWERCASE_LETTERS);
        }
        if (includeNumbers) {
            characterPool.append(NUMBERS);
        }
        if (includeSpecial) {
            characterPool.append(SPECIAL_CHARACTERS);
        }

        if (characterPool.length() == 0) {
            throw new IllegalArgumentException("At least one character type must be selected.");
        }

        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characterPool.length());
            password.append(characterPool.charAt(index));
        }

        return password.toString();
    }

    public static void getUserInputAndGeneratePassword() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the desired password length: ");
        int length = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Include uppercase letters? (yes/no): ");
        boolean includeUppercase = scanner.nextLine().equalsIgnoreCase("yes");

        System.out.print("Include lowercase letters? (yes/no): ");
        boolean includeLowercase = scanner.nextLine().equalsIgnoreCase("yes");

        System.out.print("Include numbers? (yes/no): ");
        boolean includeNumbers = scanner.nextLine().equalsIgnoreCase("yes");

        System.out.print("Include special characters? (yes/no): ");
        boolean includeSpecial = scanner.nextLine().equalsIgnoreCase("yes");

        try {
            String password = generatePassword(length, includeUppercase, includeLowercase, 
                    includeNumbers, includeSpecial);
            System.out.println("Generated Password: " + password);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }
}
