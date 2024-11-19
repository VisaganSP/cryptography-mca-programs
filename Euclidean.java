import java.util.*;
public class Euclidean{

    public static int gcd(int a, int b) {
    
        if (b == 0) {
            return a;
        }
    
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int num1 =sc.nextInt();
        int num2 = sc.nextInt();
        
        
        int result = gcd(num1, num2);
    
        
        System.out.println("The GCD of " + num1 + " and " + num2 + " is: " + result);
    }
}
