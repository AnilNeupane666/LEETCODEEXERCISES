package twopointers.src;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ThreeSum {
    public static void main(String[] args)  {
        threeSum(new int[] { -1, 0, 1, 2, -1, -4 });
    }

    private static List<List<Integer>> threeSum(int[] arr) {
        List<List<Integer>> result = new LinkedList();
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 2; i++) {
            if (i == 0 || (i > 0 && arr[i] != arr[i - 1])) {
                int leftPointer = i + 1;
                int rightPointer = arr.length - 1;
                int sum = 0 - arr[i];

                while (leftPointer < rightPointer) {
                    if (arr[leftPointer] + arr[rightPointer] == sum) {
                        result.add(Arrays.asList(arr[i], arr[leftPointer], arr[rightPointer]));
                        while (leftPointer < rightPointer && arr[leftPointer] == arr[leftPointer + 1]) {
                            leftPointer++;
                        }
                        while (leftPointer < rightPointer && arr[rightPointer] == arr[rightPointer - 1]) {
                            rightPointer--;
                        }
                        leftPointer++;
                        rightPointer--;
                    } else if (arr[leftPointer] + arr[rightPointer] > sum) {
                        rightPointer--;
                    } else {
                        leftPointer++;
                    }
                }

            }
        }
        System.out.println(result);
        return result;
    }
}