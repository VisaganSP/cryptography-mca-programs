import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class DES {

    // Encrypt the plaintext using DES
    public static String encrypt(String plaintext, String key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Decrypt the ciphertext using DES
    public static String decrypt(String ciphertext, String key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(ciphertext));
        return new String(decryptedBytes);
    }

    public static void main(String[] args) {
        try {
            // Example key (must be 8 bytes long for DES)
            String key = "12345678"; // 8-byte key for DES
            String plaintext = "Visagan";

            // Encrypt the plaintext
            String ciphertext = encrypt(plaintext, key);
            System.out.println("Encrypted: " + ciphertext);

            // Decrypt the ciphertext
            String decryptedText = decrypt(ciphertext, key);
            System.out.println("Decrypted: " + decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
