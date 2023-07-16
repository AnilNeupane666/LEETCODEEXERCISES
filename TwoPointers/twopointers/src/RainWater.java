package twopointers.src;

public class RainWater {
    public static void main(String[] args) {
        catchRainWater(new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 });
    }

    private static int catchRainWater(int[] height) {
        if (height.length == 0) {
            return 0;
        }
        int counter = 0;
        int leftPointer = 0;
        int rightPointer = height.length - 1;
        int maxleft = height[leftPointer];
        int maxright = height[rightPointer];
        while (leftPointer < rightPointer) {
            if (maxleft < maxright) {
                counter += maxleft - height[leftPointer++];
                maxleft = Math.max(height[leftPointer], maxleft);
            } else {
                counter += maxright - height[rightPointer--];
                maxright = Math.max(height[rightPointer], maxright);
            }
        }
        System.out.println(counter);
        return counter;
    }
}
