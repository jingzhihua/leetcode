package algorithm;

import com.sun.java.swing.plaf.windows.WindowsTextAreaUI;
import com.sun.security.auth.UnixNumericGroupPrincipal;

import java.util.*;

public class Day7 {
    List<String> res_string = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    List<List<String>> res_partition = new ArrayList<>();

    List<Integer> temp = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>();

    //77. 组合
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        combine(n, k, new ArrayList<>(), res, 1);
        return res;
    }

    private void combine(int n, int k, ArrayList<Integer> list, List<List<Integer>> res, int start) {
        if (list.size() == k) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i <= n - (k - list.size()) + 1; i++) {
            list.add(i);
            combine(n, k, list, res, i + 1);
            list.remove(list.size() - 1);
        }
    }


    //216. 组合总和 III
    public List<List<Integer>> combinationSum3(int k, int n) {
        combinationSum3(k, n, 0, 1, new ArrayList<Integer>());
        return res;
    }

    private void combinationSum3(int k, int n, int sum, int start, List<Integer> list) {
        if (sum == n && list.size() == k) {
            res.add(new ArrayList(list));
            return;
        } else if (sum > n) return;
        else if (list.size() > k) return;
        for (int i = start; i <= 9 - (k - list.size()) + 1; i++) {
            list.add(i);
            combinationSum3(k, n, sum + i, i + 1, list);
            list.remove(list.size() - 1);
        }
    }


    //17. 电话号码的字母组合
    public List<String> letterCombinations(String digits) {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "");
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");
        letterCombinations(digits, digits.length(), map, new StringBuilder(digits.length()));
        return res_string;
    }

    private void letterCombinations(String digits, int i, HashMap<Integer, String> map, StringBuilder sb) {
        if (i == 0) {
            res_string.add(sb.toString());
            return;
        }
        int key = digits.charAt(digits.length() - i) - '0';
        String s = map.get(key);
        for (int j = 0; j < s.length(); j++) {
            sb.append(s.charAt(j));
            letterCombinations(digits, i - 1, map, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }


    //39. 组合总和
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        res.clear();
        Arrays.sort(candidates);
        combinationSum(candidates, target, 0, new ArrayList<>(), 0);
        return res;
    }

    private void combinationSum(int[] candidates, int target, int sum, List<Integer> temp, int start) {
        if (sum == target) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (target < sum + candidates[i])
                break;
            temp.add(candidates[i]);
            combinationSum(candidates, target, sum + candidates[i], temp, i);
            temp.remove(temp.size() - 1);
        }
    }


    //40. 组合总和 II
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        res.clear();
        Arrays.sort(candidates);
        combinationSum2(candidates, target, 0, new ArrayList<>(), 0);
        return res;
    }

    private void combinationSum2(int[] candidates, int target, int sum, List<Integer> temp, int start) {
        if (sum == target) {
            res.add(new ArrayList<>(temp));
            return;
        }
        int removed = -1;
        for (int i = start; i < candidates.length; i++) {
            if (target < sum + candidates[i])
                break;
            if (removed == candidates[i]) continue;
            temp.add(candidates[i]);
            combinationSum2(candidates, target, sum + candidates[i], temp, i + 1);
            removed = temp.remove(temp.size() - 1);
        }
    }


    //131. 分割回文串
    public List<List<String>> partition(String s) {
        char[] chars = s.toCharArray();
        partition(chars, 0);
        return res_partition;
    }

    private void partition(char[] s, int start) {
        if (start == s.length) {
            res_partition.add(new ArrayList<>(res_string));
            return;
        }
        for (int len = 1; len < s.length - start + 1; len++) {
            if (huiwen(s, start, len)) {
                res_string.add(new String(s, start, len));
                partition(s, start + len);
                res_string.remove(res_string.size() - 1);
            }
        }
    }

    private boolean huiwen(char[] s, int start, int length) {
        int end = start + length - 1;
        while (start < end) {
            if (s[start] != s[end]) return false;
            start++;
            end--;
        }
        return true;
    }


    //93. 复原 IP 地址
    public List<String> restoreIpAddresses(String s) {
        if (s.length() > 12 || s.length() < 4) return res_string;
        char[] chars = s.toCharArray();
        restoreIpAddresses(chars, sb, 0, 0);
        return res_string;
    }

    private void restoreIpAddresses(char[] s, StringBuilder sb, int start, int count) {
        if (start == s.length && count == 4) {
            res_string.add(sb.substring(0, sb.length() - 1));
            return;
        } else if (start > s.length || count > 4)
            return;
        else if ((s.length - start) / 3.0 > 4 - count)
            return;
        for (int i = start; i < start + 3 && i < s.length; i++) {
            int len = i - start + 1;
            if (validIpNumber(s, start, len)) {
                sb.append(s, start, len).append('.');
                restoreIpAddresses(s, sb, start + len, count + 1);
                sb.delete(sb.length() - len - 1, sb.length());
            }
        }
    }

    private boolean validIpNumber(char[] s, int start, int len) {
        if (s[start] == '0') {
            return len == 1;
        }
        int sum = 0;
        int i = start;
        while (i < start + len) {
            sum = sum * 10 + s[i] - '0';
            i++;
        }
        return sum < 256 && sum > -1;
    }


    //78. 子集 && 90. 子集 II(with variable removed)
    public List<List<Integer>> subsets(int[] nums) {
        Arrays.sort(nums);
        subsets(nums, 0);
        return res;
    }

    private void subsets(int[] nums, int start) {
        res.add(new ArrayList<>(temp));
        if (start >= nums.length) {
            return;
        }
        int removed = -100;
        for (int i = start; i < nums.length; i++) {
            if (removed == nums[i]) continue;
            temp.add(nums[i]);
            subsets(nums, i + 1);
            removed = temp.remove(temp.size() - 1);
        }
    }


    //491. 递增子序列
    public List<List<Integer>> findSubsequences(int[] nums) {
        return res;
    }


    //46. 全排列 && 47. 全排列 II
    public List<List<Integer>> permute(int[] nums) {
        int[] flag = new int[nums.length];
        permute(nums, flag, 0);
        return res;
    }

    private void permute(int[] nums, int[] flag, int size) {
        if (size == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (flag[i] == 1) continue;
            //去重
            if (i > 0 && nums[i - 1] == nums[i] && flag[i - 1] == 0) continue;
            temp.add(nums[i]);
            flag[i] = 1;
            permute(nums, flag, size + 1);
            flag[i] = 0;
            temp.remove(temp.size() - 1);
        }
    }


    //332. 重新安排行程
    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, Integer> used = new HashMap<>();
        Map<String, List<String>> map = new HashMap<>();
        tickets.forEach(item -> {
            String key = item.get(0);
            List<String> value = map.get(key);
            if (value == null)
                value = new ArrayList<>();
            value.add(item.get(1));
            map.put(key, value);
            used.merge(key + item.get(1), 1, Integer::sum);
        });
        map.values().forEach(Collections::sort);
        res_string.add("JFK");
        findItinerary(map, used, tickets.size(), "JFK");
        return res_string;
    }

    private boolean findItinerary(Map<String, List<String>> map, Map<String, Integer> used, int size, String from) {
        if (size == 0)
            return true;
        List<String> values = map.get(from);
        if (values == null) return false;
        for (int i = 0; i < values.size(); i++) {
            String to = values.get(i);
            Integer num = used.get(from + to);
            if (num > 0) {
                used.replace(from + to, num - 1);
                res_string.add(to);
                if (findItinerary(map, used, size - 1, to))
                    return true;
                res_string.remove(res_string.size() - 1);
                used.replace(from + to, num);
            }
        }
        return false;
    }


    //51. N 皇后
    public List<List<String>> solveNQueens(int n) {
        List<Integer> list = new ArrayList<>();
        solveNQueens(list, 0, n);
        return res_partition;
    }

    private void solveNQueens(List<Integer> list, int size, int n) {
        if (size == n) {
            List<String> temp = new ArrayList<>();
            list.forEach(item -> {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < n; i++) {
                    stringBuilder.append(item == i ? 'Q' : '.');
                }
                temp.add(stringBuilder.toString());
            });
            res_partition.add(temp);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (checkForNQueens(list, size, i)) {
                list.add(i);
                solveNQueens(list, size + 1, n);
                list.remove(list.size() - 1);
            }
        }
    }

    private boolean checkForNQueens(List<Integer> list, int i, int j) {
        for (int p = 0; p < list.size(); p++) {
            Integer q = list.get(p);
            if (p == i || q == j || Math.abs(p - i) == Math.abs(q - j))
                return false;
        }
        return true;
    }


    //37. 解数独
    public void solveSudoku(char[][] board) {
        //skip
    }


    public static void main(String[] args) {
        Day7 day7 = new Day7();
//        day7.combine(4, 2).forEach(System.out::println);
//        day7.combinationSum3(3, 7);
//        day7.combinationSum(new int[]{8, 7, 4, 3}, 11);
//        day7.restoreIpAddresses("25525511135");
//        day7.subsets(new int[]{1, 2, 3});
//        day7.findSubsequences(new int[]{4, 6, 7, 7});

    }

}
