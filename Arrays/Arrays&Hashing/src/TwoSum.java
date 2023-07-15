import java.util.Arrays;

public class TwoSum {
    public static void main(String[] args) throws Exception {
        int[] a = new int[] { 2, 7, 11, 15 };
        int[] b = new int[] { 3, 2, 4 };
        int[] c = new int[] { 3, 3 };
        twoSum(a, 9);
        twoSum(b, 6);
        twoSum(c, 6);
    }

    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int n = i + 1; n < nums.length; n++) {
                if (nums[i] + nums[n] == target) {
                    result[0] = i;
                    result[1] = n;
                }
            }

        }
        System.out.println(Arrays.toString(result));

        return result;
    }
}
