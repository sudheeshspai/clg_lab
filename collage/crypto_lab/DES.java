import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Scanner;

/**
 * A menu-driven program for DES (Data Encryption Standard) encryption and decryption.
 * This class uses Java's built-in javax.crypto library to perform the cryptographic operations,
 * which simplifies the implementation significantly compared to writing it from scratch.
 */
public class DESCipherProgram {

    private static SecretKey secretKey;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            // Generate a secure DES key once at the start of the program
            secretKey = generateDesKey();
            System.out.println("DES Key generated successfully.");

            while (true) {
                System.out.println("\n--- DES Cipher Menu ---");
                System.out.println("1. Encrypt Text");
                System.out.println("2. Decrypt Text");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        System.out.print("Enter plaintext to encrypt: ");
                        String plaintext = scanner.nextLine();
                        String encryptedText = encrypt(plaintext, secretKey);
                        System.out.println("Encrypted (Base64): " + encryptedText);
                        break;
                    case "2":
                        System.out.print("Enter ciphertext to decrypt (Base64): ");
                        String ciphertext = scanner.nextLine();
                        String decryptedText = decrypt(ciphertext, secretKey);
                        System.out.println("Decrypted Text: " + decryptedText);
                        break;
                    case "3":
                        System.out.println("Exiting program. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    /**
     * Generates a new DES key using the KeyGenerator.
     * @return a SecretKey object for DES.
     * @throws Exception if key generation fails.
     */
    public static SecretKey generateDesKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
        keyGen.init(56); // DES uses a 56-bit key (64 bits total, but 8 are parity)
        return keyGen.generateKey();
    }

    /**
     * Encrypts a plaintext string using the provided DES key.
     * @param plaintext The text to encrypt.
     * @param key The secret DES key.
     * @return The encrypted text, encoded in Base64 for safe printing.
     * @throws Exception if encryption fails.
     */
    public static String encrypt(String plaintext, SecretKey key) throws Exception {
        // Create a Cipher instance with the DES algorithm
        /*DES: This is the algorithm being used. It stands for Data Encryption Standard, a symmetric-key block cipher that was widely used in the past. It works by encrypting data in fixed-size blocks.

ECB: This is the mode of operation. It stands for Electronic Codebook. In this mode, each block of plaintext is encrypted independently using the same key. It's the simplest mode, but it can be less secure for larger messages because identical blocks of plaintext will result in identical blocks of ciphertext, which can reveal patterns.

PKCS5Padding: This is the padding scheme. Since block ciphers like DES work on fixed-size blocks (8 bytes for DES), padding is needed to make sure the plaintext is a multiple of the block size. This specific padding adds a series of bytes to the end of the plaintext to fill out the last block. The value of each added byte indicates the number of bytes that were added. */
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

        // Initialize the cipher for encryption mode with the key
        cipher.init(Cipher.ENCRYPT_MODE, key);

        // Encrypt the text, converting the string to bytes first
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());

        // Use Base64 encoding to convert the binary encrypted data into a printable string
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * Decrypts a Base64-encoded ciphertext string using the provided DES key.
     * @param ciphertext The ciphertext to decrypt, expected to be in Base64 format.
     * @param key The secret DES key.
     * @return The decrypted plaintext string.
     * @throws Exception if decryption fails.
     */
    public static String decrypt(String ciphertext, SecretKey key) throws Exception {
        // Create a Cipher instance with the DES algorithm
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

        // Initialize the cipher for decryption mode with the key
        cipher.init(Cipher.DECRYPT_MODE, key);

        // Decode the Base64 string back into bytes
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(ciphertext));

        // Convert the decrypted bytes back into a string
        return new String(decryptedBytes);
    }
}
