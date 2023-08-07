package algorithm;

import java.util.Arrays;

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
        return 0;
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
        return 0;
    }


    public static void main(String[] args) {
        Day09 day09 = new Day09();
        System.out.println(day09.integerBreak(2));
        System.out.println(day09.integerBreak(3));
    }
}
