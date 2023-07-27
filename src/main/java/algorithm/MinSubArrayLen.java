package algorithm;

public class MinSubArrayLen {
    public static void main(String[] args) {
        System.out.println(minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
    }

    static int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int i = 0, j = 0;
        int sum = 0;
        int result = 0;
        while (j < n) {
            sum += nums[j];
            j++;
            while (sum >= target) {
                sum -= nums[i];
                i++;
                int temp_res = j - i + 1;
                if (result == 0) result = temp_res;
                else result = Math.min(temp_res, result);

            }

        }

        return result;
    }
}
