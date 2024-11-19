import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Substitution {
    public static void main(String[] args) {
        // Define the encryption and decryption maps
        Map<String, String> encryptionTable = new HashMap<>();
        Map<String, String> decryptionTable = new HashMap<>();

        // Create the encryption table (substitution cipher for a-j and A-J)
        encryptionTable.put("a", "h");
        encryptionTable.put("b", "i");
        encryptionTable.put("c", "j");
        encryptionTable.put("d", "k");
        encryptionTable.put("e", "l");
        encryptionTable.put("f", "m");
        encryptionTable.put("g", "z");
        encryptionTable.put("h", "o");
        encryptionTable.put("i", "p");
        encryptionTable.put("j", "q");

        // Create corresponding uppercase mappings
        encryptionTable.put("A", "H");
        encryptionTable.put("B", "I");
        encryptionTable.put("C", "J");
        encryptionTable.put("D", "K");
        encryptionTable.put("E", "L");
        encryptionTable.put("F", "M");
        encryptionTable.put("G", "N");
        encryptionTable.put("H", "O");
        encryptionTable.put("I", "P");
        encryptionTable.put("J", "Q");

        // Populate decryption table by reversing the encryption table
        for (Map.Entry<String, String> entry : encryptionTable.entrySet()) {
            decryptionTable.put(entry.getValue(), entry.getKey());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter plain text:");
        String plainText = scanner.nextLine();
        StringBuilder encrypted = new StringBuilder();

        // Encrypting the plain text
        for (int i = 0; i < plainText.length(); i++) {
            char current = plainText.charAt(i);
            String currentChar = String.valueOf(current);

            // Use the encryption table to find the encrypted character
            String encryptedChar = encryptionTable.get(currentChar);

            if (encryptedChar == null) {
                encryptedChar = currentChar; // Leave the character unchanged if not found in the table
            }

            encrypted.append(encryptedChar);
        }

        String encryptedText = encrypted.toString();
        System.out.println("Encrypted text: " + encryptedText);

        // Now decrypt the encrypted text
        StringBuilder decrypted = new StringBuilder();

        for (int i = 0; i < encryptedText.length(); i++) {
            char current = encryptedText.charAt(i);
            String currentChar = String.valueOf(current);

            // Use the decryption table to find the decrypted character
            String decryptedChar = decryptionTable.get(currentChar);

            if (decryptedChar == null) {
                decryptedChar = currentChar; // Leave the character unchanged if not found in the table
            }

            decrypted.append(decryptedChar);
        }

        System.out.println("Decrypted text: " + decrypted.toString());
    }
}
