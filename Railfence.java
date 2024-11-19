

public class Railfence {
    
    public static String encrypt(String text, int key) {
        if (key == 1) return text;
        
        StringBuilder result = new StringBuilder();
        int length = text.length();
        
        char[][] rail = new char[key][length];
        
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < length; j++) {
                rail[i][j] = '\n';
            }
        }
        
       /* int i = 0;
        int j = 0;
        for (i=0; i<=20;i++){
            if(i%2==0){
                rail[0][j] = text.charAt(i);
            }
        }*/
        int row = 0;
        boolean down = false;
        
        for (int i = 0; i < length; i++) {
            rail[row][i] = text.charAt(i);
            
            if (row == 0 || row == key - 1) {
                down = !down;
            }
            
            row = down ? row + 1 : row - 1;
        }

        for (int i = 0; i < key; i++) {
            for (int j = 0; j < length; j++) {
                if (rail[i][j] != '\n') {
                    result.append(rail[i][j]);
                }
            }
        }
        
        return result.toString();
    }
    
    public static String decrypt(String cipherText, int key) {
        if (key == 1) return cipherText;
        
        int length = cipherText.length();
        char[][] rail = new char[key][length];
        
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < length; j++) {
                rail[i][j] = '\n';
            }
        }
        
        int row = 0;
        boolean down = false;
        
        for (int i = 0; i < length; i++) {
            rail[row][i] = '*';
            
            if (row == 0 || row == key - 1) {
                down = !down;
            }
            
            row = down ? row + 1 : row - 1;
        }
        
        int index = 0;
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < length; j++) {
                if (rail[i][j] == '*' && index < length) {
                    rail[i][j] = cipherText.charAt(index++);
                }
            }
        }
        
        StringBuilder result = new StringBuilder();
        row = 0;
        down = false;
        
        for (int i = 0; i < length; i++) {
            result.append(rail[row][i]);
            
            if (row == 0 || row == key - 1) {
                down = !down;
            }
            
            row = down ? row + 1 : row - 1;
        }
        
        return result.toString();
    }

    public static void main(String[] args) {
        String message = "HELLORAILFENCECIPHER";
        int key = 3;

        String encrypted = encrypt(message, key);
        System.out.println("Encrypted: " + encrypted);

        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted: " + decrypted);
    }
}