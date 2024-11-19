class Hill {

    // Function to create the key matrix from the given key
    static void getKeyMatrix(String key, int keyMatrix[][]) {
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Convert the character to corresponding number (A=0, B=1, ..., Z=25)
                keyMatrix[i][j] = (key.charAt(k) - 65);  // key.charAt(k) should be 'A'..'Z'
                k++;
            }
        }
    }

    // Matrix multiplication for encryption
    static void encrypt(int cipherMatrix[][], int keyMatrix[][], int messageVector[][]) {
        int x, i, j;
        // Matrix multiplication
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 1; j++) {
                cipherMatrix[i][j] = 0;
                for (x = 0; x < 3; x++) {
                    cipherMatrix[i][j] += keyMatrix[i][x] * messageVector[x][j];
                }
                cipherMatrix[i][j] = cipherMatrix[i][j] % 26;  // Apply modulo 26 to keep it within A-Z
            }
        }
    }

    // Find the determinant of a matrix modulo 26
    static int determinant(int keyMatrix[][]) {
        return keyMatrix[0][0] * (keyMatrix[1][1] * keyMatrix[2][2] - keyMatrix[1][2] * keyMatrix[2][1])
                - keyMatrix[0][1] * (keyMatrix[1][0] * keyMatrix[2][2] - keyMatrix[1][2] * keyMatrix[2][0])
                + keyMatrix[0][2] * (keyMatrix[1][0] * keyMatrix[2][1] - keyMatrix[1][1] * keyMatrix[2][0]);
    }

    // Find modular inverse of a number modulo 26
    static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return 1;  // Return 1 if no modular inverse is found (shouldn't happen for Hill Cipher)
    }

    // Compute the inverse of the matrix modulo 26
    static void getInverse(int keyMatrix[][], int inverseMatrix[][]) {
        int det = determinant(keyMatrix);
        int modDet = modInverse(det, 26); // Modular inverse of the determinant modulo 26
        int adj[][] = new int[3][3]; // Adjugate matrix

        // Calculate adjugate matrix
        adj[0][0] = keyMatrix[1][1] * keyMatrix[2][2] - keyMatrix[1][2] * keyMatrix[2][1];
        adj[0][1] = -(keyMatrix[0][1] * keyMatrix[2][2] - keyMatrix[0][2] * keyMatrix[2][1]);
        adj[0][2] = keyMatrix[0][1] * keyMatrix[1][2] - keyMatrix[0][2] * keyMatrix[1][1];
        
        adj[1][0] = -(keyMatrix[1][0] * keyMatrix[2][2] - keyMatrix[1][2] * keyMatrix[2][0]);
        adj[1][1] = keyMatrix[0][0] * keyMatrix[2][2] - keyMatrix[0][2] * keyMatrix[2][0];
        adj[1][2] = -(keyMatrix[0][0] * keyMatrix[1][2] - keyMatrix[0][2] * keyMatrix[1][0]);
        
        adj[2][0] = keyMatrix[1][0] * keyMatrix[2][1] - keyMatrix[1][1] * keyMatrix[2][0];
        adj[2][1] = -(keyMatrix[0][0] * keyMatrix[2][1] - keyMatrix[0][1] * keyMatrix[2][0]);
        adj[2][2] = keyMatrix[0][0] * keyMatrix[1][1] - keyMatrix[0][1] * keyMatrix[1][0];
        
        // Apply modular inverse to adjugate and set to inverseMatrix
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                inverseMatrix[i][j] = (adj[i][j] * modDet) % 26;
                if (inverseMatrix[i][j] < 0) {
                    inverseMatrix[i][j] += 26; // Ensure the result is non-negative
                }
            }
        }
    }

    // Matrix multiplication for decryption
    static void decrypt(int plainMatrix[][], int inverseKeyMatrix[][], int cipherMatrix[][]) {
        int x, i, j;
        // Matrix multiplication for decryption
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 1; j++) {
                plainMatrix[i][j] = 0;
                for (x = 0; x < 3; x++) {
                    plainMatrix[i][j] += inverseKeyMatrix[i][x] * cipherMatrix[x][j];
                }
                plainMatrix[i][j] = plainMatrix[i][j] % 26;  // Apply modulo 26
            }
        }
    }

    // Hill cipher encryption
    static void HillCipher(String message, String key) {
        int [][]keyMatrix = new int[3][3];
        getKeyMatrix(key, keyMatrix);

        // Pad the message if needed to make it a multiple of 3 characters
        if (message.length() % 3 != 0) {
            int padLength = 3 - (message.length() % 3);
            for (int i = 0; i < padLength; i++) {
                message += 'X';  // Padding character
            }
        }

        // Encrypt the message in blocks of 3 characters
        StringBuilder CipherText = new StringBuilder();
        
        for (int i = 0; i < message.length(); i += 3) {
            String block = message.substring(i, i + 3);
            int [][]messageVector = new int[3][1];
            
            // Convert the message block into a vector of numbers
            for (int j = 0; j < 3; j++) {
                messageVector[j][0] = (block.charAt(j) - 65);  // Convert 'A' to 0, 'B' to 1, ...
            }

            int [][]cipherMatrix = new int[3][1];
            encrypt(cipherMatrix, keyMatrix, messageVector);

            // Convert the encrypted vector back to characters and append to CipherText
            for (int j = 0; j < 3; j++) {
                CipherText.append((char)(cipherMatrix[j][0] + 65));  // Convert number back to character
            }
        }

        System.out.println("Ciphertext: " + CipherText.toString());
    }

    // Hill cipher decryption
    static void HillDecipher(String cipherText, String key) {
        int [][]keyMatrix = new int[3][3];
        getKeyMatrix(key, keyMatrix);
        
        int [][]inverseKeyMatrix = new int[3][3];
        getInverse(keyMatrix, inverseKeyMatrix);

        StringBuilder PlainText = new StringBuilder();

        for (int i = 0; i < cipherText.length(); i += 3) {
            String block = cipherText.substring(i, i + 3);
            int [][]cipherMatrix = new int[3][1];
            
            // Convert the cipher block into a vector of numbers
            for (int j = 0; j < 3; j++) {
                cipherMatrix[j][0] = (block.charAt(j) - 65);  // Convert 'A' to 0, 'B' to 1, ...
            }

            int [][]plainMatrix = new int[3][1];
            decrypt(plainMatrix, inverseKeyMatrix, cipherMatrix);

            // Convert the decrypted vector back to characters and append to PlainText
            for (int j = 0; j < 3; j++) {
                PlainText.append((char)(plainMatrix[j][0] + 65));  // Convert number back to character
            }
        }

        System.out.println("Decrypted Text: " + PlainText.toString());
    }

    public static void main(String[] args) {
        String message = "ACT";  // Example message
        String key = "GYBNQKURP";  // Example key (9 characters, for 3x3 matrix)

        HillCipher(message, key);  // Encrypt the message using Hill Cipher
        
        String cipherText = "CFB"; // Example ciphertext
        HillDecipher(cipherText, key);  // Decrypt the ciphertext using Hill Cipher
    }
}
