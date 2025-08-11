import java.util.*;

/**
 * Main class for a menu-driven Playfair and Rail Fence cipher program.
 * This class provides a simple command-line interface for a user to choose
 * which cipher to use. It contains methods for both encryption and decryption
 * for each cipher.
 */
public class playfair {

    // Main method to run the program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("--- Cipher Menu ---");
            System.out.println("1. Playfair Cipher");
            System.out.println("2. Rail Fence Cipher");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    playfairMenu(scanner);
                    break;
                case "2":
                    railFenceMenu(scanner);
                    break;
                case "3":
                    System.out.println("Exiting program. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println(); // Add a new line for better formatting
        }
    }

    /**
     * Menu for the Playfair cipher.
     * @param scanner The Scanner object for user input.
     */
    private static void playfairMenu(Scanner scanner) {
        System.out.println("\n--- Playfair Cipher Menu ---");
        System.out.println("1. Encrypt");
        System.out.println("2. Decrypt");
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine();

        System.out.print("Enter the key: ");
        String key = scanner.nextLine().toUpperCase();
        char[][] playfairMatrix = generatePlayfairMatrix(key);

        switch (choice) {
            case "1":
                System.out.print("Enter the plaintext: ");
                String plaintext = scanner.nextLine().toUpperCase();
                String encryptedText = encryptPlayfair(plaintext, playfairMatrix);
                System.out.println("Ciphertext: " + encryptedText);
                break;
            case "2":
                System.out.print("Enter the ciphertext: ");
                String ciphertext = scanner.nextLine().toUpperCase();
                String decryptedText = decryptPlayfair(ciphertext, playfairMatrix);
                System.out.println("Plaintext: " + decryptedText);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    /**
     * Menu for the Rail Fence cipher.
     * @param scanner The Scanner object for user input.
     */
    private static void railFenceMenu(Scanner scanner) {
        System.out.println("\n--- Rail Fence Cipher Menu ---");
        System.out.println("1. Encrypt");
        System.out.println("2. Decrypt");
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine();

        System.out.print("Enter the number of rails (an integer): ");
        int rails = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case "1":
                System.out.print("Enter the plaintext: ");
                String plaintext = scanner.nextLine();
                String encryptedText = encryptRailFence(plaintext, rails);
                System.out.println("Ciphertext: " + encryptedText);
                break;
            case "2":
                System.out.print("Enter the ciphertext: ");
                String ciphertext = scanner.nextLine();
                String decryptedText = decryptRailFence(ciphertext, rails);
                System.out.println("Plaintext: " + decryptedText);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    // --- Playfair Cipher Logic ---

    /**
     * Generates a 5x5 Playfair matrix from a given key.
     * It handles the alphabet (A-Z) and replaces 'J' with 'I'.
     * @param key The key string.
     * @return A 5x5 character array representing the Playfair matrix.
     */
    private static char[][] generatePlayfairMatrix(String key) {
        char[][] matrix = new char[5][5];
        Set<Character> usedLetters = new HashSet<>();
        String keyCleaned = key.replaceAll("[^A-Z]", "");
        keyCleaned = keyCleaned.replaceAll("J", "I");

        int row = 0, col = 0;
        // Fill the matrix with the key
        for (char c : keyCleaned.toCharArray()) {
            if (usedLetters.add(c)) {
                matrix[row][col] = c;
                col++;
                if (col == 5) {
                    col = 0;
                    row++;
                }
            }
        }

        // Fill the rest of the matrix with remaining alphabet letters
        for (char c = 'A'; c <= 'Z'; c++) {
            if (c == 'J') continue; // Skip 'J'
            if (usedLetters.add(c)) {
                matrix[row][col] = c;
                col++;
                if (col == 5) {
                    col = 0;
                    row++;
                }
            }
        }
        return matrix;
    }

    /**
     * Encrypts a plaintext message using the Playfair cipher.
     * @param plaintext The message to encrypt.
     * @param matrix The 5x5 Playfair matrix.
     * @return The encrypted ciphertext.
     */
    private static String encryptPlayfair(String plaintext, char[][] matrix) {
        // Prepare the plaintext: remove spaces, convert to uppercase, handle 'J's
        String preparedText = plaintext.replaceAll("[^A-Z]", "").replaceAll("J", "I");

        // Handle double letters and odd length
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < preparedText.length(); i += 2) {
            char char1 = preparedText.charAt(i);
            char char2;
            if (i + 1 < preparedText.length()) {
                char2 = preparedText.charAt(i + 1);
                // If a double letter, insert an 'X'
                if (char1 == char2) {
                    sb.append(char1).append('X');
                    i--; // Decrement to re-process the second 'X' with the next character
                } else {
                    sb.append(char1).append(char2);
                }
            } else {
                // If odd length, add a padding character 'X'
                sb.append(char1).append('X');
            }
        }
        preparedText = sb.toString();

        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < preparedText.length(); i += 2) {
            char char1 = preparedText.charAt(i);
            char char2 = preparedText.charAt(i + 1);

            int[] pos1 = findPosition(matrix, char1);
            int[] pos2 = findPosition(matrix, char2);

            int row1 = pos1[0], col1 = pos1[1];
            int row2 = pos2[0], col2 = pos2[1];

            // Same row rule
            if (row1 == row2) {
                ciphertext.append(matrix[row1][(col1 + 1) % 5]);
                ciphertext.append(matrix[row2][(col2 + 1) % 5]);
            }
            // Same column rule
            else if (col1 == col2) {
                ciphertext.append(matrix[(row1 + 1) % 5][col1]);
                ciphertext.append(matrix[(row2 + 1) % 5][col2]);
            }
            // Rectangle rule
            else {
                ciphertext.append(matrix[row1][col2]);
                ciphertext.append(matrix[row2][col1]);
            }
        }
        return ciphertext.toString();
    }

    /**
     * Decrypts a ciphertext message using the Playfair cipher.
     * @param ciphertext The message to decrypt.
     * @param matrix The 5x5 Playfair matrix.
     * @return The decrypted plaintext.
     */
    private static String decryptPlayfair(String ciphertext, char[][] matrix) {
        StringBuilder plaintext = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i += 2) {
            char char1 = ciphertext.charAt(i);
            char char2 = ciphertext.charAt(i + 1);

            int[] pos1 = findPosition(matrix, char1);
            int[] pos2 = findPosition(matrix, char2);

            int row1 = pos1[0], col1 = pos1[1];
            int row2 = pos2[0], col2 = pos2[1];

            // Same row rule (inverse)
            if (row1 == row2) {
                plaintext.append(matrix[row1][(col1 + 4) % 5]); // +4 is equivalent to -1 mod 5
                plaintext.append(matrix[row2][(col2 + 4) % 5]);
            }
            // Same column rule (inverse)
            else if (col1 == col2) {
                plaintext.append(matrix[(row1 + 4) % 5][col1]);
                plaintext.append(matrix[(row2 + 4) % 5][col2]);
            }
            // Rectangle rule (same as encryption)
            else {
                plaintext.append(matrix[row1][col2]);
                plaintext.append(matrix[row2][col1]);
            }
        }
        return plaintext.toString();
    }

    /**
     * Finds the row and column of a character in the Playfair matrix.
     * @param matrix The 5x5 Playfair matrix.
     * @param c The character to find.
     * @return An integer array [row, col].
     */
    private static int[] findPosition(char[][] matrix, char c) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == c) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1}; // Should not happen with a valid key
    }

    // --- Rail Fence Cipher Logic ---

    /**
     * Encrypts a plaintext message using the Rail Fence cipher.
     * @param plaintext The message to encrypt.
     * @param rails The number of rails (the key).
     * @return The encrypted ciphertext.
     */
    private static String encryptRailFence(String plaintext, int rails) {
        char[][] railMatrix = new char[rails][plaintext.length()];
        int row = 0;
        int direction = 1; // 1 for down, -1 for up

        for (int i = 0; i < plaintext.length(); i++) {
            railMatrix[row][i] = plaintext.charAt(i);
            if (row == 0) {
                direction = 1; // Go down
            } else if (row == rails - 1) {
                direction = -1; // Go up
            }
            row += direction;
        }

        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < plaintext.length(); j++) {
                if (railMatrix[i][j] != 0) {
                    ciphertext.append(railMatrix[i][j]);
                }
            }
        }
        return ciphertext.toString();
    }

    /**
     * Decrypts a ciphertext message using the Rail Fence cipher.
     * @param ciphertext The message to decrypt.
     * @param rails The number of rails (the key).
     * @return The decrypted plaintext.
     */
    private static String decryptRailFence(String ciphertext, int rails) {
        char[][] railMatrix = new char[rails][ciphertext.length()];
        int[] railLengths = new int[rails];
        int row = 0;
        int direction = 1;

        // First, calculate the length of each rail to know where to place characters
        for (int i = 0; i < ciphertext.length(); i++) {
            railLengths[row]++;
            if (row == 0) {
                direction = 1;
            } else if (row == rails - 1) {
                direction = -1;
            }
            row += direction;
        }

        // Fill the rail matrix with the ciphertext characters
        int k = 0;
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < railLengths[i]; j++) {
                railMatrix[i][k] = ciphertext.charAt(k);
                k++;
            }
        }

        StringBuilder plaintext = new StringBuilder();
        row = 0;
        direction = 1;
        // Read the matrix in the zig-zag pattern to get the plaintext
        for (int i = 0; i < ciphertext.length(); i++) {
            plaintext.append(railMatrix[row][i]);
            if (row == 0) {
                direction = 1;
            } else if (row == rails - 1) {
                direction = -1;
            }
            row += direction;
        }
        return plaintext.toString();
    }
}


