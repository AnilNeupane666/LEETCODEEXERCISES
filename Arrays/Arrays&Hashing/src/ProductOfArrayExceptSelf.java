import java.util.Arrays;

public class ProductOfArrayExceptSelf {
    public static void main(String[] args) throws Exception {
        int[] nums1 = new int[] { 1, 2, 3, 4 }; // [24,12,8,6]
        int[] nums2 = new int[] { -1, 1, 0, -3, 3 }; // [0,0,9,0,0]
        productExceptSelf(nums1);
        productExceptSelf(nums2);
        productExceptSelf2(nums1);
        productExceptSelf2(nums2);
    }

    public static int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int[] temporaryArray = new int[nums.length - 1];
            temporaryArray = Arrays.copyOf(nums, nums.length);
            temporaryArray[i] = 1;
            int product = 1;
            for (int element : temporaryArray) {
                product *= element;
            }
            result[i] = product;

        }

        System.out.println(Arrays.toString(result));
        return result;
    }

    public static int[] productExceptSelf2(int[] nums) {
        int ans[] = new int[nums.length];
        int pre = 1, post = 1;
        
        //find pre product
        for(int i=0;i<nums.length;i++){
            ans[i] = pre;
            pre*=nums[i];
        }

        //find post product
        for(int i=nums.length-1;i>=0;i--){
            ans[i]*=post;
            post*=nums[i];
        }
        System.out.println(Arrays.toString(ans));
        return ans;
    }
}
