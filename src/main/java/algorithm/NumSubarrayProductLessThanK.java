package algorithm;

public class NumSubarrayProductLessThanK {
    public static void main(String[] args) {
        System.out.println(numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100));
    }

    static int numSubarrayProductLessThanK(int[] nums, int k) {
        int n = nums.length;
        int i = 0, j = 0;
        int sum = 1;
        int result = 0;
        while (i < n) {
            sum *= nums[i];


        }

        return result;
    }
}
