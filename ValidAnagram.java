import java.util.Arrays;

public class ValidAnagram {
    public static void main(String[] args) throws Exception {
        String a = "rat";
        String b = "car";
        String c = "atr";
        isAnagram(a, b);
        isAnagram(b, c);
        isAnagram(a, c);
    }

    public static void isAnagram(String s, String t) {

        if (s.length() != t.length()) {
            System.out.println("false");
        }
        char[] s1 = s.toCharArray();
        char[] t2 = t.toCharArray();
        Arrays.sort(s1);
        Arrays.sort(t2);
        System.out.println(Arrays.equals(s1, t2));
    }
}
