import java.security.MessageDigest;

public class SHAExample {
    public static void main(String[] args) {
        try {
            // Sample input data
            String input = "Hello, this is a test message.";

            // Create SHA-256 hash
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes());

            // Convert byte array to hex format
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }

            // Output the hash
            System.out.println("SHA-256 Hash: " + hexString.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
