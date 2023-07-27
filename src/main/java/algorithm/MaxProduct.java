package algorithm;

import java.util.Arrays;
import java.util.HashSet;

public class MaxProduct {
    public static void main(String[] args) {
        String[] test = new String[]{"abcw","baz","foo","bar","fxyz","abcdef"};
        System.out.println(maxProduct(test));
    }

    static int maxProduct(String[] words) {
        int n = words.length;
        int[][] sameChar = new int[n][n];

        int result = 0;

        for (int i = 1; i < n; i++) {
            sameChar[0][i] = sameC(words[0], words[i]);
            if (sameChar[0][i] == 0) {
                int temp = words[0].length() * words[i].length();
                result = Math.max(result, temp);
            }
        }

        for (int i = 1; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = 0; k < j; k++) {
                    if (sameChar[k][j] == 1 && sameChar[i][k] == 1) {
                        sameChar[i][j] = 1;
                        break;
                    }
                }
                sameChar[i][j] = sameC(words[i], words[j]);
                if (sameChar[i][j] == 0) {
                    int temp = words[i].length() * words[j].length();
                    result = Math.max(result, temp);
                }
            }
        }
        return result;

    }

    static int sameC(String word1, String word2) {
        int[] count = new int[26];
        for (char c : word1.toCharArray()) count[c - 'a'] = 1;
        for (char c : word2.toCharArray()) {
            if (count[c - 'a'] == 1) return 1;
        }
        return 0;
    }

//    static int sameC(String a, String b) {
//        HashSet<Character> characters = new HashSet<>();
//        for (int i = 0; i < a.length(); i++) {
//            characters.add(a.charAt(i));
//        }
//        for (int i = 0; i < b.length(); i++) {
//            if (characters.contains(b.charAt(i))) return 1;
//        }
//        return 0;
//    }
}
