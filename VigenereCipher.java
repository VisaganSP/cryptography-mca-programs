import java.util.Scanner;

public class VigenereCipher {

    // Function to encrypt using the Vigenère cipher with an alphabetic key
    public static String encryptWithAlphabetKey(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();
        int keyIndex = 0;

        for (int i = 0; i < plaintext.length(); i++) {
            char currentChar = plaintext.charAt(i);

            if (Character.isLetter(currentChar)) {
                char base = Character.isUpperCase(currentChar) ? 'A' : 'a';
                int shift = Character.toUpperCase(key.charAt(keyIndex % key.length())) - 'A';

                char encryptedChar = (char) ((currentChar - base + shift) % 26 + base);
                ciphertext.append(encryptedChar);

                keyIndex++;
            } else {
                ciphertext.append(currentChar);
            }
        }

        return ciphertext.toString();
    }

    // Function to encrypt using the Vigenère cipher with a numeric key (similar to
    // Caesar cipher)
    public static String encryptWithNumericKey(String plaintext, int key) {
        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i++) {
            char currentChar = plaintext.charAt(i);

            if (Character.isLetter(currentChar)) {
                char base = Character.isUpperCase(currentChar) ? 'A' : 'a';

                char encryptedChar = (char) ((currentChar - base + key) % 26 + base);
                ciphertext.append(encryptedChar);
            } else {
                ciphertext.append(currentChar);
            }
        }

        return ciphertext.toString();
    }

    // Function to decrypt using the Vigenère cipher with an alphabetic key
    public static String decryptWithAlphabetKey(String ciphertext, String key) {
        StringBuilder plaintext = new StringBuilder();
        int keyIndex = 0;

        for (int i = 0; i < ciphertext.length(); i++) {
            char currentChar = ciphertext.charAt(i);

            if (Character.isLetter(currentChar)) {
                char base = Character.isUpperCase(currentChar) ? 'A' : 'a';
                int shift = Character.toUpperCase(key.charAt(keyIndex % key.length())) - 'A';

                char decryptedChar = (char) ((currentChar - base - shift + 26) % 26 + base);
                plaintext.append(decryptedChar);

                keyIndex++;
            } else {
                plaintext.append(currentChar);
            }
        }

        return plaintext.toString();
    }

    // Function to decrypt using the Vigenère cipher with a numeric key
    public static String decryptWithNumericKey(String ciphertext, int key) {
        StringBuilder plaintext = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i++) {
            char currentChar = ciphertext.charAt(i);

            if (Character.isLetter(currentChar)) {
                char base = Character.isUpperCase(currentChar) ? 'A' : 'a';

                char decryptedChar = (char) ((currentChar - base - key + 26) % 26 + base);
                plaintext.append(decryptedChar);
            } else {
                plaintext.append(currentChar);
            }
        }

        return plaintext.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask the user for the type of key (alphabetic or numeric)
        System.out.println("Choose encryption type: ");
        System.out.println("1. Vigenère with alphabetic key");
        System.out.println("2. Vigenère with numeric key (Caesar shift)");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character after the integer input

        if (choice == 1) {
            // For alphabetic key Vigenère cipher
            System.out.println("Enter Plaintext: ");
            String plaintext = scanner.nextLine();

            System.out.println("Enter Key (alphabetic): ");
            String key = scanner.nextLine();

            String encryptedText = encryptWithAlphabetKey(plaintext, key);
            System.out.println("Encrypted Text: " + encryptedText);

            String decryptedText = decryptWithAlphabetKey(encryptedText, key);
            System.out.println("Decrypted Text: " + decryptedText);
        } else if (choice == 2) {
            // For numeric key (Caesar-like shift)
            System.out.println("Enter Plaintext: ");
            String plaintext = scanner.nextLine();

            System.out.println("Enter Numeric Key: ");
            int key = scanner.nextInt();

            String encryptedText = encryptWithNumericKey(plaintext, key);
            System.out.println("Encrypted Text: " + encryptedText);

            String decryptedText = decryptWithNumericKey(encryptedText, key);
            System.out.println("Decrypted Text: " + decryptedText);
        } else {
            System.out.println("Invalid choice.");
        }

        scanner.close();
    }
}
