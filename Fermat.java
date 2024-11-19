import java.util.Scanner;

public class Fermat{

    // Function to compute (a^b) % p using modular exponentiation
    public static long powerMod(long a, long b, long p) {
        long result = 1;
        a = a % p;

        // Loop to compute (a^b) % p
        while (b > 0) {
            // If b is odd, multiply a with result
            if (b % 2 == 1) {
                result = (result * a) % p;
            }

            // Now b must be even, divide it by 2
            b = b / 2;

            // Square a and take mod with p
            a = (a * a) % p;
        }

        return result;
    }

    // Fermat's Little Theorem check for a^p-1 ≡ 1 (mod p)
    public static boolean isFermatValid(long a, long p) {
        // Fermat's theorem is valid if a^(p-1) % p == 1
        if (a % p == 0) return false;
        return powerMod(a, p - 1, p) == 1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the value of a: ");
        long a = scanner.nextLong();

        System.out.print("Enter the value of prime p: ");
        long p = scanner.nextLong();

        // Check if Fermat's Little Theorem holds
        if (isFermatValid(a, p)) {
            System.out.println("Fermat's theorem holds for a = " + a + " and p = " + p);
        } else {
            System.out.println("Fermat's theorem does NOT hold for a = " + a + " and p = " + p);
        }

        scanner.close();
    }
}
