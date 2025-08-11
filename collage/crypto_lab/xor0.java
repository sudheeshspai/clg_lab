import java.util.*;
public class Xor0 {


    public static void main(String[] args) {
        String ogstring= "No change";
        char xorKey = 'O';


        StringBuilder result = new StringBuilder();


        for (int i = 0; i < ogstring.length(); i++) {
            char mixedChar = (char) (ogstring.charAt(i) ^ xorKey);
            result.append(mixedChar);
        }


        System.out.println("Original String: " + ogstring);
        System.out.println("Result after XOR with '" + xorKey + "': " + result.toString());
    }
}