package cryptography;
/**
 *
 * @author Collins
 */
public class CaesarCipherBruteForceAttack {
    public static void main(String[] args) {
        //Declare and attach the cipher text
        String cipherText = "TEBKFKQEBZLROPBLCERJXKBSBKQP";
        //Method call
        bruteForceDecrypt(cipherText);
    }
    public static void bruteForceDecrypt(String cipherText) {
        //Loops through all 25 possible Caesar ciphers (excluding the trivial shift of 0 positions)
        for (int loop = 1; loop <= 25; loop++) {
            StringBuilder decryptedText = new StringBuilder();
            for (char character : cipherText.toCharArray()) {
                if (Character.isLetter(character)) {
                    //Caesar Decryption algorithm
                    char shiftedChar = (char) (((character + 'A' + loop + 26) % 26) + 'A'); //shift +up
                    //char shiftedChar = (char) (((character - 'A' - loop + 26) % 26) + 'A'); //shift -down
                    decryptedText.append(shiftedChar);
                } else {
                    decryptedText.append(character);
                }
            }
            //Prints out all returned text results
            System.out.println("Shift " + loop + ": " + decryptedText.toString());
        }
    }
}
