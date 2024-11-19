import java.util.HashSet;
import java.util.Set;

public class Playfair {
    private static final int MATRIX_SIZE = 5;
    private static final char FILLER_CHAR = 'X';

    private static String keyword = "projectx";
    private static char[][] matrix = new char[MATRIX_SIZE][MATRIX_SIZE];

    public static void main(String[] args) {
        String plaintext = "attack pearl harbor";
        createMatrix();
        System.out.println("Playfair Cipher Matrix:");
        printMatrix();
        String encryptedText = encrypt(plaintext);
        System.out.println("Encrypted Text: " + encryptedText);
    }

    private static void createMatrix() {
        String key = keyword.toUpperCase().replaceAll("[^A-Z]", "");
        Set<Character> usedChars = new HashSet<>();
        int index = 0;

        // Add characters from keyword
        for (char c : key.toCharArray()) {
            if (!usedChars.contains(c) && c != 'J') {
                usedChars.add(c);
                matrix[index / MATRIX_SIZE][index % MATRIX_SIZE] = c;
                index++;
            }
        }

        // Add remaining characters
        for (char c = 'A'; c <= 'Z'; c++) {
            if (!usedChars.contains(c) && c != 'J') {
                usedChars.add(c);
                matrix[index / MATRIX_SIZE][index % MATRIX_SIZE] = c;
                index++;
            }
        }
    }

    private static void printMatrix() {
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static String prepareText(String text) {
        text = text.toUpperCase().replaceAll("[^A-Z]", "");
        text = text.replaceAll("J", "I");
        StringBuilder sb = new StringBuilder(text);

        for (int i = 0; i < sb.length() - 1; i += 2) {
            if (sb.charAt(i) == sb.charAt(i + 1)) {
                sb.insert(i + 1, FILLER_CHAR);
            }
        }

        if (sb.length() % 2 != 0) {
            sb.append(FILLER_CHAR);
        }

        return sb.toString();
    }

    private static String encrypt(String plaintext) {
        plaintext = prepareText(plaintext);
        StringBuilder encryptedText = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i += 2) {
            char a = plaintext.charAt(i);
            char b = plaintext.charAt(i + 1);

            int[] posA = findPosition(a);
            int[] posB = findPosition(b);

            if (posA[0] == posB[0]) {
                encryptedText.append(matrix[posA[0]][(posA[1] + 1) % MATRIX_SIZE]);
                encryptedText.append(matrix[posB[0]][(posB[1] + 1) % MATRIX_SIZE]);
            } else if (posA[1] == posB[1]) {
                encryptedText.append(matrix[(posA[0] + 1) % MATRIX_SIZE][posA[1]]);
                encryptedText.append(matrix[(posB[0] + 1) % MATRIX_SIZE][posB[1]]);
            } else {
                encryptedText.append(matrix[posA[0]][posB[1]]);
                encryptedText.append(matrix[posB[0]][posA[1]]);
            }
        }

        return encryptedText.toString();
    }

    private static int[] findPosition(char c) {
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                if (matrix[i][j] == c) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}