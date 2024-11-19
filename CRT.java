import java.util.Scanner;

public class CRT {

    // Function to compute the greatest common divisor (GCD)
    public static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    // Extended Euclidean Algorithm to find the multiplicative inverse
    public static long extendedGcd(long a, long b, long[] xy) {
        if (b == 0) {
            xy[0] = 1;
            xy[1] = 0;
            return a;
        }
        long[] xy1 = {0, 0}; // to hold values for recursion
        long gcd = extendedGcd(b, a % b, xy1);
        xy[0] = xy1[1];
        xy[1] = xy1[0] - (a / b) * xy1[1];
        return gcd;
    }

    // Function to solve using Chinese Remainder Theorem
    public static long chineseRemainder(int[] num, int[] rem, int k) {
        // Step 1: Compute product of all num[i]
        long prod = 1;
        for (int i = 0; i < k; i++) {
            prod *= num[i];
        }

        // Step 2: Initialize result
        long result = 0;

        // Step 3: Apply the formula for CRT
        for (int i = 0; i < k; i++) {
            long partialProd = prod / num[i];
            long[] xy = {0, 0}; // To store solutions x and y from the extended GCD
            extendedGcd(partialProd, num[i], xy); // Get the modular inverse
            long inverse = xy[0]; // The modular inverse of partialProd mod num[i]
            result += rem[i] * partialProd * inverse;
        }

        // Return the result mod product of all num[i]
        return (result % prod + prod) % prod; // Ensure the result is non-negative
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of equations: ");
        int k = scanner.nextInt();

        int[] num = new int[k];
        int[] rem = new int[k];

        System.out.println("Enter the moduli (n values) and remainders (a values): ");
        for (int i = 0; i < k; i++) {
            System.out.print("n[" + (i + 1) + "]: ");
            num[i] = scanner.nextInt();
            System.out.print("a[" + (i + 1) + "]: ");
            rem[i] = scanner.nextInt();
        }

        // Solve the system using Chinese Remainder Theorem
        long solution = chineseRemainder(num, rem, k);

        // Output the solution
        System.out.println("The solution x is: " + solution);

        scanner.close();
    }
}
