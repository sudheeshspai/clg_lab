public class AndOrXor {


    public static void main(String[] args) {
        String originalString = "Hello world";
        int key = 127;


        StringBuilder andResult = new StringBuilder();
        for (char c : originalString.toCharArray()) {
            andResult.append((char) (c & key));
        }


        StringBuilder orResult = new StringBuilder();
        for (char c : originalString.toCharArray()) {
            orResult.append((char) (c | key));
        }


        StringBuilder xorResult = new StringBuilder();
        for (char c : originalString.toCharArray()) {
            xorResult.append((char) (c ^ key));
        }


        System.out.println("Original String: " + originalString);
        System.out.println("Key: " + key);
        System.out.println("------------------------------------");
        System.out.println("AND Result: " + andResult.toString());
        System.out.println("OR Result:  " + orResult.toString());
        System.out.println("XOR Result: " + xorResult.toString());
    }
} 
