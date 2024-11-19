import java.util.Scanner;

public class Ceaser {
    public static void main(String[] args) {

        String encrypted_string = "";
        String decrypted_string = "";

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a plain text:");
        String plaintext = scanner.nextLine();

        int count = plaintext.length();

        System.out.println("Enter a key value:");
        int key = scanner.nextInt();

        for (int i = 0; i < count; i++) {

            int ascii = (int) plaintext.charAt(i);

            if (ascii + key > 122) {
                int rem = (ascii + key) - 122;
                int new_ascii = 96 + rem;
                encrypted_string += (char) new_ascii;
            } else {
                int new_ascii = ascii + key;
                encrypted_string += (char) new_ascii;
            }
        }
        System.out.println("Encrypted String:" + encrypted_string);

        System.out.println("Brute Force Approach:");

        for (int j = 1; j < 25; j++) {
            System.out.println("At shift " + j);

            for (int k = 0; k < count; k++) {
                int ascii = encrypted_string.charAt(k);
                int new_ascii = ascii - j;
                decrypted_string += (char) new_ascii;
            }

            System.out.println(decrypted_string);

            if (decrypted_string.equals(plaintext)) {
                System.out.println("Match is found");
            }

            decrypted_string = "";
        }
    }
}