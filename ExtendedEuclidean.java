import java.util.*;
public class ExtendedEuclidean {

    public static class Result {
        int gcd, x, y;

        public Result(int gcd, int x, int y) {
            this.gcd = gcd;
            this.x = x;
            this.y = y;
        }
    }

    
    public static Result extendedGcd(int a, int b) {
       
        if (b == 0) {
            return new Result(a, 1, 0);
        }

        Result result = extendedGcd(b, a % b);

    
        int newX = result.y;
        int newY = result.x - (a / b) * result.y;

       
        return new Result(result.gcd, newX, newY);
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();

        
        Result result = extendedGcd(a, b);

        
        System.out.println("GCD: " + result.gcd);
        System.out.println("x: " + result.x);
        System.out.println("y: " + result.y);
        System.out.println("Equation: " + a + "(" + result.x + ") + " + b + "(" + result.y + ") = " + result.gcd);
    }
}
