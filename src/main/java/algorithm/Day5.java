package algorithm;

import java.util.*;

public class Day5 {
    //20.有效的括号
    public boolean isValid(String s) {
        int length = s.length();
        if (length % 2 != 0) return false;
        LinkedList<Character> stack = new LinkedList<>();
        HashMap<Character, Character> map = new HashMap<>();
        map.put('[', ']');
        map.put('{', '}');
        map.put('(', ')');

        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) stack.addLast(c);
            else {
                if (stack.size() == 0 || c != map.get(stack.pollLast())) return false;
            }
        }
        return stack.size() == 0;
    }

    //1047. 删除字符串中的所有相邻重复项
    public String removeDuplicates(String s) {
        char[] chars = s.toCharArray();
        int j = 0;
        for (int i = 1; i < s.length(); ) {
            if (j == -1 || chars[i] != chars[j]) {
                chars[++j] = chars[i++];
            } else {
                while (j >= 0 && i < s.length() && chars[j] == chars[i]) {
                    j--;
                    i++;
                }
            }
        }
        return new String(chars, 0, j + 1);
    }

    //150. 逆波兰表达式求值
    public int evalRPN(String[] tokens) {
        HashSet<String> set = new HashSet<>();
        set.add("+");
        set.add("-");
        set.add("*");
        set.add("/");
        LinkedList<Integer> stack = new LinkedList();
        for (String s : tokens) {
            if (set.contains(s)) {
                Integer a = stack.pollLast();
                Integer b = stack.pollLast();
                switch (s) {
                    case "+":
                        stack.addLast(b + a);
                        break;
                    case "-":
                        stack.addLast(b - a);
                        break;
                    case "*":
                        stack.addLast(b * a);
                        break;
                    case "/":
                        stack.addLast(b / a);
                        break;
                }
            } else stack.addLast(Integer.parseInt(s));
        }
        return stack.pollLast();
    }

    //239. 滑动窗口最大值
    public int[] maxSlidingWindow(int[] nums, int k) {
        return null;
    }

    //347. 前 K 个高频元素
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue = new PriorityQueue<>(k, Comparator.comparingInt(Map.Entry::getValue));
        for (int i : nums) {
            map.merge(i, 1, Integer::sum);
        }
        for (Map.Entry<Integer, Integer> i : map.entrySet()) {
            if (priorityQueue.size() < k) priorityQueue.add(i);
            else {
                if (priorityQueue.peek().getValue() < i.getValue()) {
                    priorityQueue.poll();
                    priorityQueue.add(i);
                }
            }
        }
        int[] res = new int[k];
        int index = 0;
        for (Map.Entry<Integer, Integer> integerIntegerEntry : priorityQueue) {
            res[index++] = integerIntegerEntry.getKey();
        }
        return res;
    }

    public static void main(String[] args) {
        Day5 day5 = new Day5();
        System.out.println(day5.removeDuplicates("aababaab"));
        System.out.println(day5.evalRPN(new String[]{"4", "13", "5", "/", "+"}));
        day5.maxSlidingWindow(new int[]{9, 10, 9, -7, -4, -8, 2, -6}, 5);
    }
}
