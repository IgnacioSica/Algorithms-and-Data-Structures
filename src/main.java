
public class main {

    public static void main(String[] args) {
        int i = 124;
        String str = "Hello";
        Object obj = new Object();
        float f = 19;

        System.out.println("Integer 123 hashCode: " + Integer.hashCode(i));
        System.out.println("String Hello hashCode: " + str.hashCode());
        System.out.println("Object obj hashCode: " + obj.hashCode());
        System.out.println("Float f hashCode: " + Float.hashCode(f));
    }
}
