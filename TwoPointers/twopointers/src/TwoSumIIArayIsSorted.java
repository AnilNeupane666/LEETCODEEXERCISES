package twopointers.src;
import java.util.Arrays;

public class TwoSumIIArayIsSorted {
    public static void main(String[] args) {
       // twoSum(new int[] { 2, 7, 11, 15 }, 9);
        //twoSum(new int[] { 2, 3, 4, 15 }, 6);
        twoSumWithinTImeLimit(new int[] { 2, 3, 4, 15 }, 18);

    }

    public static int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target) {
                    result[0] = i + 1;
                    result[1] = j + 1;
                }
            }
        }
        System.out.println(Arrays.toString(result));
        return result;
    }

    public static int[] twoSumWithinTImeLimit(int[] numbers, int target) {
        int leftPointer = 0;
        int rightPointer = numbers.length-1;
        int currSum =0;
        while (leftPointer<rightPointer){
            currSum = numbers[leftPointer] +  numbers[rightPointer];
            if (currSum>target){
                rightPointer-=1;
            } else if (currSum<target){
                leftPointer+=1;
            } else {
                return new int[]{leftPointer+1, rightPointer+1};
            }
        }
        return null;
    }
}
