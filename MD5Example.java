import java.security.MessageDigest;

public class MD5Example {
    public static void main(String[] args) {
        try {
            // Sample input data
            String input = "Hello, this is a test message.";

            // Create MD5 hash
            MessageDigest digest = MessageDigest.getInstance("MD5");
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
            System.out.println("MD5 Hash: " + hexString.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
