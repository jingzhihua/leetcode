package algorithm;

import com.sun.xml.internal.ws.client.ClientSchemaValidationTube;

import java.util.*;

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


    //860. 柠檬水找零
    public boolean lemonadeChange(int[] bills) {
        int count_5 = 0;
        int count_10 = 0;
        for (int bill : bills) {
            if (bill == 5) {
                count_5++;
            } else if (bill == 10) {
                count_5--;
                count_10++;
            } else {
                if (count_10 > 0) {
                    count_10--;
                    count_5--;
                } else {
                    count_5 -= 3;
                }
            }
            if (count_5 < 0) return false;
        }
        return true;
    }


    //406. 根据身高重建队列
    public int[][] reconstructQueue(int[][] people) {
        return people;
    }


    //452. 用最少数量的箭引爆气球
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;
        Arrays.sort(points, Comparator.comparingInt(point -> point[1]));
        int res = 1;
        int max_right = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > max_right) {
                res++;
                max_right = points[i][1];
            }
        }
        return res;
    }


    //435. 无重叠区间
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, Comparator.comparingInt(point -> point[1]));
        int res = 1;
        int max_right = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= max_right) {
                max_right = intervals[i][1];
                res++;
            }
        }
        return intervals.length - res;
    }


    //763.划分字母区间
    public List<Integer> partitionLabels(String s) {
        int[] chars = new int[26];
        Arrays.fill(chars, -1);
        for (int i = 0; i < s.length(); i++) {
            chars[s.charAt(i) - '0'] = i;
        }
        ArrayList<Integer> res = new ArrayList<>();
        int max_right = 0;
        int left = -1;
        for (int i = 0; i < s.length(); i++) {
            max_right = Math.max(max_right, chars[s.charAt(i) - '0']);
            if (max_right == s.length() - 1) {
                res.add(max_right - left);
                break;
            }
            if (i == max_right) {
                res.add(max_right - left);
                left = i;
            }
        }
        return res;
    }


    //56. 合并区间
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(point -> point[0]));
        int left = intervals[0][0];
        int right = intervals[0][1];
        ArrayList<int[]> res = new ArrayList<>();
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= right) {
                right = Math.max(right, intervals[i][1]);
            } else {
                res.add(new int[]{left, right});
                left = intervals[i][0];
                right = intervals[i][1];
            }
        }
        res.add(new int[]{left, right});
        int[][] a = new int[res.size()][2];
        for (int i = 0; i < res.size(); i++) {
            a[i] = res.get(i);
        }
        return a;
    }

    //738. 单调递增的数字
    public int monotoneIncreasingDigits(int n) {
        if (n < 10) return n;
        StringBuilder stringBuilder = new StringBuilder();
        int a = n % 10;
        n = n / 10;
        stringBuilder.append(a);
        while (n != 0) {
            int b = n % 10;
            if (b > a) {
                b--;
                int size = stringBuilder.length();
                stringBuilder = new StringBuilder();
                while (size-- > 0) stringBuilder.append(9);

            }
            stringBuilder.append(b);
            a = b;
            n = n / 10;
        }
        return Integer.parseInt(stringBuilder.reverse().toString());
    }

    //968. 监控二叉树
    public int minCameraCover(TreeNode root) {
        return 0;
    }

    public static void main(String[] args) {
        Day8 day8 = new Day8();
        day8.wiggleMaxLength(new int[]{3, 3, 3, 2, 5});
        day8.monotoneIncreasingDigits(332);

    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
