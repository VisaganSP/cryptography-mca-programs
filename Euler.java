import java.util.Scanner;

public class Euler {

    // Function to compute Euler's Totient Function phi(n)
    public static int eulerTotient(int n) {
        int result = n; // Start with result = n

        // Check for all numbers less than n
        for (int i = 2; i * i <= n; i++) {
            // If i is a divisor of n
            if (n % i == 0) {
                // Subtract multiples of i from result
                while (n % i == 0) {
                    n /= i;
                }
                result -= result / i;
            }
        }
        
        // If n has a prime factor greater than sqrt(n)
        if (n > 1) {
            result -= result / n;
        }

        return result;
    }

    // Function to compute (a^b) % mod using modular exponentiation
    public static long powerMod(long a, long b, long mod) {
        long result = 1;
        a = a % mod; // Update a if it's greater than mod

        while (b > 0) {
            // If b is odd, multiply a with result
            if (b % 2 == 1) {
                result = (result * a) % mod;
            }

            // b must be even now, so we divide it by 2
            b = b / 2;
            a = (a * a) % mod; // Square a and take mod
        }

        return result;
    }

    // Function to check if Euler's theorem holds for a and n
    public static boolean isEulerValid(int a, int n) {
        // Find phi(n)
        int phiN = eulerTotient(n);

        // Check if a^phi(n) % n == 1
        return powerMod(a, phiN, n) == 1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the value of a: ");
        int a = scanner.nextInt();

        System.out.print("Enter the value of n: ");
        int n = scanner.nextInt();

        // Check if Euler's theorem holds
        if (isEulerValid(a, n)) {
            System.out.println("Euler's theorem holds for a = " + a + " and n = " + n);
        } else {
            System.out.println("Euler's theorem does NOT hold for a = " + a + " and n = " + n);
        }

        scanner.close();
    }
}
