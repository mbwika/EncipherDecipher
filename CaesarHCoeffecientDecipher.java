package cryptography;

import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author Collins
 */
public class CaesarHCoeffecientDecipher {
        public static void main(String[] args) {
        String ciphertext = "TEBKFKQEBZLROPBLCERJXKBSBKQP";
        decryptCaesarCipher(ciphertext);
    }

    public static void decryptCaesarCipher(String ciphertext) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        double[] englishFrequencies = {
                0.080, 0.015, 0.030, 0.040, 0.130, 0.020, 0.015, 0.060, 0.065, 0.005, 0.005, 0.035,
                0.030, 0.070, 0.080, 0.020, 0.002, 0.065, 0.060, 0.090, 0.030, 0.010, 0.015, 0.005, 0.020, 0.002
        };

        // Calculate correlation coefficients for all possible shifts
        ShiftCorrelation[] shiftCorrelations = new ShiftCorrelation[26];
        for (int shift = 0; shift < 26; shift++) {
            double correlation = 0;

            for (int i = 0; i < ciphertext.length(); i++) {
                char encryptedChar = ciphertext.charAt(i);
                int encryptedIndex = alphabet.indexOf(encryptedChar);
                int decryptedIndex = (encryptedIndex - shift + 26) % 26;
                char decryptedChar = alphabet.charAt(decryptedIndex);
                correlation += englishFrequencies[decryptedChar - 'A'];
            }

            shiftCorrelations[shift] = new ShiftCorrelation(shift, correlation);
        }

        // Sort shifts by correlation coefficient in descending order
        Arrays.sort(shiftCorrelations, Collections.reverseOrder());
        
        // Decipher the ciphertext using the top 5 correlations and print the results
//        int topShifts = Math.min(5, shiftCorrelations.length);
        int alphabetLength = 26;
//        for (int i = 0; i < topShifts; i++) {
//            int shift = shiftCorrelations[i].getShift();
//      Decipher the ciphertext using the all correlations and print the results  
        for (ShiftCorrelation shiftCorrelation : shiftCorrelations) {
            int shift = shiftCorrelation.getShift();
            StringBuilder decryptedText = new StringBuilder();
            for (int j = 0; j < ciphertext.length(); j++) {
                char encryptedChar = ciphertext.charAt(j);
                int encryptedIndex = alphabet.indexOf(encryptedChar);
                int decryptedIndex = (encryptedIndex - shift + 26) % 26;
                char decryptedChar = alphabet.charAt(decryptedIndex);
                decryptedText.append(decryptedChar);
            }
            System.out.println("Deciphered Text: " + decryptedText.toString()+ "; ShiftKey: +" + (alphabetLength - shift)+ " or -" + shift);
        }
    }
    
    // Helper class to store shift and correlation coefficient
    static class ShiftCorrelation implements Comparable<ShiftCorrelation> {
        private final int shift;
        private final double correlation;

        public ShiftCorrelation(int shift, double correlation) {
            this.shift = shift;
            this.correlation = correlation;
        }

        public int getShift() {
            return shift;
        }

        @Override
        public int compareTo(ShiftCorrelation other) {
            return Double.compare(correlation, other.correlation);
        }
    }
}
