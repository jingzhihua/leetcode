package algorithm;

import java.util.*;

public class Day3 {

    //242. 有效的字母异位词
    public boolean isAnagram(String s, String t) {
        int[] char_count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char_count[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < t.length(); i++) {
            int index = t.charAt(i) - 'a';
            char_count[index]--;
            if (char_count[index] < 0) return false;
        }
        for (int j : char_count) {
            if (j != 0) return false;
        }
        return true;

    }

    //349. 两个数组的交集
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        Set<Integer> set = new HashSet<>();
        for (int i : nums1) {
            set.add(i);
        }
        Set<Integer> res = new HashSet<>();
        for (int i : nums2) {
            if (set.contains(i)) res.add(i);
        }
        int[] a = new int[res.size()];
        int i = 0;
        for (Integer k : res) {
            a[i++] = k;
        }
        return a;
    }

    //202. 快乐数
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (!set.contains(n)) {
            set.add(n);
            int sum = 0;
            while (n != 0) {
                sum += (n % 10) * (n % 10);
                n = n / 10;
            }
            n = sum;
            if (n == 1) return true;
        }
        return false;
    }

    //1. 两数之和
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.merge(num, 1, Integer::sum);
        }
        int i = 0;
        int a = 0;
        for (; i < nums.length; i++) {
            a = target - nums[i];
            Integer integer = map.get(a);
            if (integer != null && (integer == 1 && a != nums[i] || integer == 2 && a == nums[i])) {
                break;
            }
        }
        if (i < nums.length - 1) {
            int j = i + 1;
            for (; j < nums.length; j++) {
                if (nums[j] == a) return new int[]{i, j};
            }

        }
        return null;
    }

    //454. 四数相加 II
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums1) {
            for (int j : nums2) {
                map.merge(i + j, 1, Integer::sum);
            }
        }
        int res = 0;
        for (int i : nums3) {
            for (int j : nums4) {
                res += map.getOrDefault(-i - j, 0);
            }
        }
        return res;
    }

    //383. 赎金信
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] letter = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            letter[magazine.charAt(i) - 'a']++;
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            int index = ransomNote.charAt(i) - 'a';
            letter[index]--;
            if (letter[index] < 0) return false;
        }
        return true;
    }

    //15. 三数之和
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                return res;
            }
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int right = nums.length - 1;
            int left = i + 1;
            int target = -nums[i];
            while (left < right) {
                if (nums[left] + nums[right] > target) right--;
                else if (nums[left] + nums[right] < target) left++;
                else {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (right > left && nums[left] == nums[left + 1]) left++;
                    while (right > left && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                }
            }
        }

        return res;
    }

    //18. 四数之和
    public List<List<Integer>> fourSum(int[] nums, int target) {
        //三数之和 再套一层循环 跳过
        return null;
    }

    public static void main(String[] args) {
        Day3 day3 = new Day3();
        day3.isHappy(19);
        day3.twoSum(new int[]{2, 5, 5, 11}, 10);
        day3.threeSum(new int[]{1, -1, -1, 0});
    }
}
