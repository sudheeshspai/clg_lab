public class ClassNotFoundExample {

    public static void findClass(String className) throws ClassNotFoundException {
       
        Class.forName(className);  
        
        System.out.println("Class " + className + " loaded successfully.");
    }

    public static void main(String[] args) {

        try {
            findClass("java.util.Scanner");  
            
            findClass("com.example.NonExistentClass");
        } 
        catch (ClassNotFoundException e) {
           
            System.out.println("Error: Class not found - " + e.getMessage());
        } 
        finally {
            System.out.println("Execution of try-catch-finally is complete.");
        }
    }
}
