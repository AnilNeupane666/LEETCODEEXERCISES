package twopointers.src;

public class MostWater {
    public static void main(String[] args) {
        maxArea(new int[] { 1, 8, 6, 2, 5, 4, 8, 3, 7 });
    }

    public static int maxArea(int[] height) {
        int area = 0;
        int leftPointer = 0;
        int rightPointer = height.length - 1;
        while (leftPointer < rightPointer) {
            if (height[leftPointer] < height[rightPointer]) {
                area = Math.max(area, height[leftPointer] * (rightPointer - leftPointer));
                leftPointer += 1;
            } else {
                area = Math.max(area, height[rightPointer] * (rightPointer - leftPointer));
                rightPointer -= 1;
            }
        }
        System.out.println(area);
        return area;
    }
}
