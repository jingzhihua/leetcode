package algorithm;

import java.util.Arrays;
import java.util.Map;

public class Day8 {

    //455. 分发饼干
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int start = 0;
        int res = 0;
        for (int i = 0; i < g.length && start < s.length; i++) {
            while (start < s.length && s[start] < g[i]) start++;
            if (start < s.length) res++;
            start++;
        }
        return res;
    }


    //376. 摆动序列
    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2) return nums.length;
        int res = 0;
        int direction = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) continue;
            else if (nums[i] > nums[i - 1]) {
                if (direction > 0) continue;
                res++;
                direction = 1;
            } else {
                if (direction < 0) continue;
                direction = -1;
                res++;
            }
        }
        return res;
    }


    //53. 最大子数组和
    public int maxSubArray(int[] nums) {
        int res = Integer.MIN_VALUE;
        int temp = 0;
        for (int num : nums) {
            temp += num;
            res = Math.max(res, temp);
            if (temp < 0) temp = 0;
        }
        return res;
    }


    //122. 买卖股票的最佳时机 II
    public int maxProfit(int[] prices) {
        int selling = 0;
        int price = 0;
        int sum = 0;
        for (int i = 0; i < prices.length; i++) {
            if (selling == 0) {
                while (i + 1 < prices.length && prices[i] > prices[i + 1]) {
                    i++;
                }
                price = prices[i];
                selling = 1;
            } else {
                while (i + 1 < prices.length && prices[i] < prices[i + 1]) {
                    i++;
                }
                sum += prices[i] - price;
                selling = 0;
            }
        }
        return sum;
    }


    //55. 跳跃游戏
    public boolean canJump(int[] nums) {
        if (nums.length < 2) return true;
        else if (nums.length == 2 && nums[0] > 0) return true;
        int max = nums[0];
        for (int i = 1; i < nums.length - 1; i++) {
            if (max <= 0) return false;
            else if (max == 1 && nums[i] == 0) return false;
            max = Math.max(max - 1, nums[i]);
            if (max + i >= nums.length - 1) return true;
        }
        return false;
    }


    //45. 跳跃游戏 II
    public int jump(int[] nums) {
        int length = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }


    //1005. K 次取反后最大化的数组和
    public int largestSumAfterKNegations(int[] nums, int k) {
        int sum = 0;
        int abs_min = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0 && k > 0) {
                sum -= nums[i];
                k--;
            } else {
                sum += nums[i];
            }
            abs_min = Math.min(abs_min, Math.abs(nums[i]));
        }
        if (k % 2 == 1) {
            sum -= 2 * abs_min;
        }
        return sum;
    }


    //134. 加油站
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int index = 0;
        int total = 0;
        int sum = 0;
        for (int i = 0; i < gas.length; i++) {
            int rest = gas[i] - cost[i];
            total += rest;
            if (sum < 0) {
                sum = 0;
                index = i;
            }
            sum += rest;
        }
        return total >= 0 ? index : -1;
    }


    //135. 分发糖果
    public int candy(int[] ratings) {

        return 0;
    }

    public static void main(String[] args) {
        Day8 day8 = new Day8();
        day8.wiggleMaxLength(new int[]{3, 3, 3, 2, 5});
        System.out.println(-1 % 2);
        System.out.println(-2 % 2);
    }
}
