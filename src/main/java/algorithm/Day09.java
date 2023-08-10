package algorithm;

import java.net.Inet4Address;
import java.util.*;

public class Day09 {

    //509. 斐波那契数
    public int fib(int n) {
        if (n == 0) return 0;
        else if (n == 1) return 1;
        int last_last = 0;
        int last = 1;
        int res = 0;
        for (int i = 2; i <= n; i++) {
            res = last + last_last;
            last_last = last;
            last = res;
        }
        return res;
    }

    //70. 爬楼梯
    public int climbStairs(int n) {
        if (n == 1) return 1;
        else if (n == 2) return 2;
        int last_last = 1;
        int last = 2;
        int res = 0;
        for (int i = 3; i <= n; i++) {
            res = last + last_last;
            last_last = last;
            last = res;
        }
        return res;
    }

    //746. 使用最小花费爬楼梯
    public int minCostClimbingStairs(int[] cost) {
        int last_last = 0, last = 0, current = 0;
        for (int i = 0; i < cost.length; i++) {
            last_last = last;
            last = current;
            current = cost[i] + Math.min(last, last_last);
        }
        return Math.min(current, last);
    }

    //62. 不同路径
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    //63. 不同路径 II
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                break;
            }
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 1) {
                break;
            }
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = obstacleGrid[i][j] == 1 ? 0 : (dp[i][j - 1] + dp[i - 1][j]);
            }
        }
        return dp[m - 1][n - 1];
    }

    //343. 整数拆分
    public int integerBreak(int n) {
        if (n < 4) return n - 1;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 2;
        for (int i = 4; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        return dp[n];
    }
//    public int integerBreak(int n) {
//        int a = (int) Math.pow(n, 0.5);
//        int left_res = calcForIntegerBreak(a, n);
//        int right_res = left_res;
//        boolean continue_left = true;
//        boolean continue_right = true;
//        for (int i = 1; i <= a; i++) {
//            int left = a - i;
//            int right = a + i;
//            int left_temp = continue_left ? calcForIntegerBreak(left, n) : 0;
//            if (continue_left && left_res < left_temp) {
//                left_res = left_temp;
//            } else {
//                continue_left = false;
//            }
//
//            int right_temp = continue_right ? calcForIntegerBreak(right, n) : 0;
//            if (continue_right && right_res < right_temp) {
//                right_res = right_temp;
//            } else {
//                continue_right = false;
//            }
//            if (!continue_left && !continue_right) break;
//
//        }
//        return Math.max(left_res, right_res);
//    }
//
//    private int calcForIntegerBreak(int a, int n) {
//        if (a <= 0) return 0;
//        int count = n / a;
//        int yushu = n % a;
//        if (yushu == 0) return (int) Math.pow(a, count);
//        int temp1 = (int) (Math.pow(a, count) * yushu);
//        int temp2 = (int) (Math.pow(a, count - 1) * (yushu + a));
//        return Math.max(temp1, temp2);
//    }


    //96. 不同的二叉搜索树
    public int numTrees(int n) {
        if (n < 3) return n;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            int sum = 0;
            for (int j = 1; j <= i; j++) {
                int left = j - 1;
                int right = i - j;
                sum += dp[left] * dp[right];
            }
            dp[i] = sum;
        }
        return dp[n];
    }


    //416. 分割等和子集
    public boolean canPartition(int[] nums) {
        if (nums.length < 2) return false;
        int sum = 0;
        int maxNumber = 0;
        for (int num : nums) {
            sum += num;
            maxNumber = Math.max(maxNumber, num);
        }
        int target = sum / 2;
        if ((sum & 1) == 1 || maxNumber > target) return false;
        int[][] dp = new int[nums.length][target + 1];
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = 1;
        }
        dp[0][nums[0]] = 1;

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < target + 1; j++) {
                if (j >= nums[i])
                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - nums[i]];
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[dp.length - 1][target] == 1;
    }

    //1049. 最后一块石头的重量 II
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int s : stones) {
            sum += s;
        }
        int target = sum / 2;
        int[][] dp = new int[stones.length][target + 1];
        for (int j = stones[0]; j <= target; j++) {
            dp[0][j] = stones[0];
        }
        for (int i = 1; i < stones.length; i++) {
            for (int j = 1; j <= target; j++) {
                if (j >= stones[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - stones[i]] + stones[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return (sum - dp[stones.length - 1][target]) - dp[stones.length - 1][target];
    }


    //494.目标和
    int res = 0;

    public int findTargetSumWays(int[] nums, int target) {
        findTargetSumWaysDFS(nums, 0, target);
        return res;
    }

    private void findTargetSumWaysDFS(int[] nums, int index, int target) {
        if (index >= nums.length) {
            if (target == 0)
                res++;
            return;
        }
        findTargetSumWaysDFS(nums, index + 1, target + nums[index]);
        findTargetSumWaysDFS(nums, index + 1, target - nums[index]);
    }

    //474. 一和零
    public int findMaxForm(String[] strs, int m, int n) {
        int q = strs.length;
        int[][][] dp = new int[q + 1][m + 1][n + 1];
        for (int i = 1; i <= q; i++) {
            int count_0 = 0, count_1 = 0;
            for (int j = 0; j < strs[i - 1].length(); j++) {
                if (strs[i - 1].charAt(j) == '0') count_0++;
                else if (strs[i - 1].charAt(j) == '1') count_1++;
            }
            for (int j = 0; j <= m; j++) {
                if (count_0 > j) {
                    for (int k = 0; k <= n; k++) {
                        dp[i][j][k] = dp[i - 1][j][k];
                    }
                    continue;
                }
                for (int k = 0; k <= n; k++) {
                    if (count_1 > k) dp[i][j][k] = dp[i - 1][j][k];
                    else dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - count_0][k - count_1] + 1);
                }
            }
        }
        return dp[q][m][n];
    }


    public int wanquan(int[] weight, int[] price, int maxWeight) {
        int[] dp = new int[maxWeight + 1];
        for (int i = 1; i <= weight.length; i++) {
            for (int j = weight[i]; j <= maxWeight; j++) { // 遍历背包容量
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + price[i]);
            }
        }
        return dp[maxWeight];
    }


    //518. 零钱兑换 II
    public int change(int amount, int[] coins) {
        return 0;
    }


    public static void main(String[] args) {
        Day09 day09 = new Day09();
//        System.out.println(day09.integerBreak(2));
//        day09.canPartition(new int[]{2, 2, 3, 5});
//        day09.lastStoneWeightII(new int[]{1, 2, 4, 8, 16, 32, 64, 12, 25, 51});
    }

}
