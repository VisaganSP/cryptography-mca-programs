import javax.crypto.KeyAgreement;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import java.security.*;
import java.util.Base64;

public class DiffieHellmanExample {

    public static void main(String[] args) throws Exception {
        // Step 1: Generate a DH key pair for Alice
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("DH");
        keyPairGen.initialize(2048); // Strong key size for DH
        KeyPair aliceKeyPair = keyPairGen.generateKeyPair();

        // Step 2: Generate Bob's DH key pair using Alice's public key parameters
        DHParameterSpec dhParams = ((DHPublicKey) aliceKeyPair.getPublic()).getParams();
        keyPairGen.initialize(dhParams);
        KeyPair bobKeyPair = keyPairGen.generateKeyPair();

        // Step 3: Perform the key agreement
        // Alice's key agreement
        KeyAgreement aliceKeyAgree = KeyAgreement.getInstance("DH");
        aliceKeyAgree.init(aliceKeyPair.getPrivate());
        aliceKeyAgree.doPhase(bobKeyPair.getPublic(), true);
        byte[] aliceSharedSecret = aliceKeyAgree.generateSecret();

        // Bob's key agreement
        KeyAgreement bobKeyAgree = KeyAgreement.getInstance("DH");
        bobKeyAgree.init(bobKeyPair.getPrivate());
        bobKeyAgree.doPhase(aliceKeyPair.getPublic(), true);
        byte[] bobSharedSecret = bobKeyAgree.generateSecret();

        // Encode the shared secret for readability
        String encodedAliceSecret = Base64.getEncoder().encodeToString(aliceSharedSecret);
        String encodedBobSecret = Base64.getEncoder().encodeToString(bobSharedSecret);

        System.out.println("Alice's Shared Secret: " + encodedAliceSecret);
        System.out.println("Bob's Shared Secret: " + encodedBobSecret);

        // Verify if both shared secrets are the same
        System.out.println("Shared secrets match: " + encodedAliceSecret.equals(encodedBobSecret));
    }
}
