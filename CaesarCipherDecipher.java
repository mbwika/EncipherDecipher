package cryptography;
/**
 *
 * @author Collins
 */
public class CaesarCipherDecipher {
    public static void main(String[] args) {
        String cipherText = "KHOORZRUOG";
        decryptCaesarCipher(cipherText);
    }
    public static void decryptCaesarCipher(String cipherText) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//        double[] englishFrequencies = {
//                0.082, 0.015, 0.028, 0.043, 0.127, 0.022, 0.020, 0.061, 0.070, 0.002, 0.008, 0.040,
//                0.024, 0.067, 0.075, 0.019, 0.001, 0.060, 0.063, 0.091, 0.028, 0.010, 0.023, 0.001, 0.020, 0.001
//        };
double[] englishFrequencies = {
                0.080, 0.015, 0.030, 0.040, 0.130, 0.020, 0.015, 0.060, 0.065, 0.005, 0.005, 0.035,
                0.030, 0.070, 0.080, 0.020, 0.002, 0.065, 0.060, 0.090, 0.030, 0.010, 0.015, 0.005, 0.020, 0.002
        };
        double maxCorrelation = 0;
        //int bestShift = 0;
        for (int shift = 1; shift <= 25; shift++) {
            double correlation = 0;
            for (int i = 1; i < cipherText.length(); i++) {
                char encryptedChar = cipherText.charAt(i);
                int encryptedIndex = alphabet.indexOf(encryptedChar);
                int decryptedIndex = (encryptedIndex - shift + 26) % 26;
                char decryptedChar = alphabet.charAt(decryptedIndex);
                correlation += englishFrequencies[decryptedChar - 'A'];
            }
            if (correlation > maxCorrelation) {
                maxCorrelation = correlation;
//                bestShift = shift;
            
            
            // Decrypt the ciphertext using the best shift
        StringBuilder decryptedText = new StringBuilder();
        for (int i = (int) maxCorrelation; i > 0; i--) {
            char encryptedChar = cipherText.charAt(i);
            int encryptedIndex = alphabet.indexOf(encryptedChar);
            //int decryptedIndex = (encryptedIndex + bestShift + 26) % 26; //shift up+
            int decryptedIndex = (int) ((encryptedIndex + correlation - 26) % 26); //shift down-
            char decryptedChar = alphabet.charAt(decryptedIndex);
            decryptedText.append(decryptedChar);
        }
        System.out.println("Decrypted Text: " + decryptedText.toString() + " Correlation: " +maxCorrelation);
//        System.out.println("Shift used for decryption: " + bestShift);
        }
        }
    }
}
