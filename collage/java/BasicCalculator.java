import java.util.InputMismatchException;
import java.util.Scanner;

public class BasicCalculator {

    // Method to perform the calculator operations
    public static double calculate(double num1, double num2, char operation) {
        switch (operation) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 == 0) {
                    // Step 2: Throw ArithmeticException for division by zero
                    throw new ArithmeticException("Division by zero is not allowed.");
                }
                return num1 / num2;
            default:
                // Step 3: Throw IllegalArgumentException for invalid operation
                throw new IllegalArgumentException("Invalid operation: " + operation);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;

        while (!validInput) {
            try {
                // Step 1: Get numbers from the user and handle InputMismatchException
                System.out.print("Enter first number: ");
                double num1 = scanner.nextDouble();

                System.out.print("Enter second number: ");
                double num2 = scanner.nextDouble();

                System.out.print("Enter operation (+, -, *, /): ");
                char operation = scanner.next().charAt(0);

                // Perform the calculation
                double result = calculate(num1, num2, operation);

                // Output the result
                System.out.println("The result is: " + result);
                validInput = true;  // Valid input, break out of the loop

            } catch (InputMismatchException e) {
                // Step 1a: Handle non-numeric input
                System.out.println("Invalid input. Please enter numeric values.");
                scanner.nextLine();  // Clear the invalid input
            } catch (ArithmeticException e) {
                // Step 2b: Handle division by zero
                System.out.println(e.getMessage());  // "Division by zero is not allowed."
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());  // "Invalid operation"
            } finally {
                System.out.println("Attempt complete.");
            }
        }

        scanner.close();  
}
