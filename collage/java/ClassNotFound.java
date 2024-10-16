public class ClassNotFound {

    public static void findClass(String className) 
    throws ClassNotFoundException {
       
       
        
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
       
    }
}
