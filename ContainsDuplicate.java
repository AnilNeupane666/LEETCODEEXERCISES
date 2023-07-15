import java.util.HashSet;

public class ContainsDuplicate {

    public static void main(String[] args) throws Exception {
        int[] a = new int[] { 1, 2, 3, 1 };
        int[] b = new int[] { 1,2,3,4 };
        int[] c = new int[] { 1, 1, 1, 3, 3, 4, 3, 2, 4, 2 };
        containsDuplicate(a);
        containsDuplicate(b);
        containsDuplicate(c);
    }

    public static void containsDuplicate(int[] nums) {
        HashSet<Integer> intList = new HashSet<>();
        boolean result = false;
        for (int i : nums) {
            if (intList.contains(i)) {
                result = true;
            } else {
                intList.add(i);
            }
        }
        System.out.println(result);
    }
}