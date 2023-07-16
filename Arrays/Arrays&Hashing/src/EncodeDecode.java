import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EncodeDecode {
    public static final char DELIMITER = '#';

    public static void main(String[] args) throws Exception {
        List<String> list = new ArrayList<String>() {
            {
                add("eat");
                add("tea");
                add("tan");
            }
        };
        String encodedMessage = encode(list);
        System.out.println(encodedMessage);
        decode(encodedMessage);
    }

    // Encodes a list of strings to a single string.
    public static String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str.length());
            sb.append(DELIMITER);
            sb.append(str);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public static List<String> decode(String s) {
        List<String> res = new LinkedList<>();
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            // Find the number of characters
            StringBuilder sb = new StringBuilder();
            while (arr[i] != DELIMITER) {
                sb.append(arr[i++]);
            }
            i++;

            // Iteration the enter string to add to res list
            int numOfChars = Integer.valueOf(sb.toString());
            int end = i + numOfChars;
            sb = new StringBuilder();
            while (i < end) {
                sb.append(arr[i++]);
            }
            i--;
            res.add(sb.toString());
        }
        System.out.println(res);

        return res;
    }
}
