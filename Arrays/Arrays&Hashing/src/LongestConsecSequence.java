import java.util.HashSet;
import java.util.Set;

public class LongestConsecSequence {

    public static void main(String[] args) throws Exception {
        int[] nums = new int[] { 0, 3, 7, 2, 5, 8, 4, 6, 0, 1 };
        int[] nums2 = new int[] { 100, 4, 200, 1, 3, 2 };
        findLongestSeq(nums);
        findLongestSeq(nums2);

    }

    public static int findLongestSeq(int[] nums) {
        if (nums.length==0){
            return 0;
        }
        Set<Integer> numSet = new HashSet<>();
        int max = 0;
        for (int i : nums) {
            numSet.add(i);
        }
        for (int i = 0; i < nums.length; i++) {
            int count = 1;
            
            //  left
            int num = nums[i];
            while (numSet.contains(--num)) {
              count++;
              numSet.remove(num);
            }
            
            //  right
            num = nums[i];
            while (numSet.contains(++num)) {
              count++;
              numSet.remove(num);
            }
            
            max = Math.max(max, count);
          }
        System.out.println(max);
        return max;

    }
}
