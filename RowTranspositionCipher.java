import java.util.Arrays;
import java.util.Scanner;

public class RowTranspositionCipher {

    // Encrypt the given plaintext using the Row Transposition Cipher with the
    // specified key
    public static String encrypt(String plaintext, String key) {
        // Determine the number of columns based on the key length
        int numColumns = key.length();

        // Calculate the number of rows needed, rounding up to ensure all characters fit
        int numRows = (int) Math.ceil((double) plaintext.length() / numColumns);

        // Create a 2D array (matrix) to hold the ciphertext in a grid form
        char[][] grid = new char[numRows][numColumns];

        // Fill the grid with characters from the plaintext
        int idx = 0;
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numColumns; col++) {
                // Fill the grid with characters from the plaintext, padding with 'X' if needed
                if (idx < plaintext.length()) {
                    grid[row][col] = plaintext.charAt(idx++);
                } else {
                    grid[row][col] = 'X'; // Padding character
                }
            }
        }

        // Create an array of indices for the key's order
        Integer[] keyOrder = new Integer[numColumns];
        for (int i = 0; i < numColumns; i++) {
            keyOrder[i] = i;
        }

        // Sort the indices based on the key's alphabetical order
        Arrays.sort(keyOrder, (i, j) -> Character.compare(key.charAt(i), key.charAt(j)));

        // Generate the ciphertext by reading the columns in the sorted order of the key
        StringBuilder ciphertext = new StringBuilder();
        for (int col : keyOrder) {
            for (int row = 0; row < numRows; row++) {
                ciphertext.append(grid[row][col]);
            }
        }

        return ciphertext.toString();
    }

    // Main method to interact with the user
    public static void main(String[] args) {
        // Create a scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Ask the user for the plaintext and key
        System.out.print("Enter plaintext: ");
        String plaintext = scanner.nextLine().replaceAll("\\s+", "").toUpperCase(); // Remove spaces and convert to
                                                                                    // uppercase

        System.out.print("Enter key: ");
        String key = scanner.nextLine();

        // Encrypt the plaintext using the Row Transposition Cipher
        String ciphertext = encrypt(plaintext, key);

        // Display the resulting ciphertext
        System.out.println("Ciphertext: " + ciphertext);

        // Close the scanner
        scanner.close();
    }
}
