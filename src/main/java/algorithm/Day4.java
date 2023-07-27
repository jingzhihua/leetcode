package algorithm;

public class Day4 {

    //344. 反转字符串
    public void reverseString(char[] s, int begin, int end) {
        int i = begin, j = end;
        while (i < j) {
            char c = s[i];
            s[i] = s[j];
            s[j] = c;
            i++;
            j--;
        }
    }

    //541. 反转字符串 II
    public String reverseStr(String s, int k) {
        int length = s.length();
        int remainder = length % (2 * k);
        char[] chars = s.toCharArray();
        int index = length - remainder;
        if (remainder < k) {
            reverseString(chars, index, length - 1);
        } else if (remainder < 2 * k) {
            reverseString(chars, index, index + k - 1);
        }
        int i = 0;
        while (i < index) {
            reverseString(chars, i, i + k - 1);
            i += 2 * k;
        }
        return new String(chars);
    }

    //剑指 Offer 05. 替换空格
    public String replaceSpace(String s) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') builder.append("%20");
            else builder.append(c);
        }
        return builder.toString();
    }

    //151. 反转字符串中的单词
    public String reverseWords(String s) {
        s = ' ' + s.trim();
        StringBuilder builder = new StringBuilder(s.length());
        int end = s.length();
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == ' ') {
                if (i != end - 1) {
                    for (int j = i + 1; j < end; j++) {
                        builder.append(s.charAt(j));
                    }
                    builder.append(' ');
                }
                end = i;
            }
        }
        return builder.substring(0, builder.length() - 1);
    }

    //剑指 Offer 58 - II. 左旋转字符串
    public String reverseLeftWords(String s, int n) {
        return s.substring(n) + s.substring(0, n);
    }

    public int strStr(String haystack, String needle) {
        int i = 0, j = 0;
        int[] next = new int[needle.length()];
        getNext(needle, next);
        for (; i < haystack.length(); i++) {
            while (j > 0 && haystack.charAt(i) != needle.charAt(j))
                j = next[j - 1];
            if (haystack.charAt(i) == needle.charAt(j))
                j++;
            if (j == needle.length())
                return i - j + 1;
        }
        return -1;
    }

    void getNext(String s, int[] next) {
        next[0] = 0;
        int i = 1;
        int j = 0;
        while (i < s.length()) {
            if (s.charAt(i) == s.charAt(j)) {
                j++;
                next[i] = j;
                i++;
            } else if (j == 0) {
                next[i] = j;
                i++;
            } else j = next[j - 1];
        }
//        for (; i < s.length(); i++) {
//            while (j != 0 && s.charAt(i) != s.charAt(j)) j = next[j - 1];
//            if (s.charAt(i) == s.charAt(j)) {
//                j++;
//            }
//            next[i] = j;
//        }
    }

    public static void main(String[] args) {
        Day4 day4 = new Day4();
//        System.out.println(day4.reverseWords("1 2 3 4 5"));
//        System.out.println(day4.reverseWords("the sky is blue"));
        System.out.println(day4.strStr("abacababacabad", "abacabad"));
    }
}
