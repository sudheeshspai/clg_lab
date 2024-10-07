public class ExceptionHandlingExample {

    public static void main(String[] args) {
        
        int[] numbers = {10, 20, 30};
        int a = 10, b = 0;

        try {
          
            int result = a / b;  
           
            System.out.println(numbers[5]);  
        } 
        catch (ArithmeticException e) {
           
            System.out.println("Error: Division by zero is not allowed.");
        } 
        catch (ArrayIndexOutOfBoundsException e) {
           
            System.out.println("Error: Array index is out of bounds.");
        } 
        finally {
           
            System.out.println("Execution of try-catch-finally block is complete.");
        }
    }
}
