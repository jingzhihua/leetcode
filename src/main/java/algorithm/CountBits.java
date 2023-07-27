package algorithm;

import java.util.Arrays;

public class CountBits {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(countBits(7)));
    }

    public static int[] countBits(int n) {
        int[] res = new int[n + 1];
        res[0] = 0;

        for (int i = 1; i <= n; i++) {
            int temp = i - 1;
            while (temp % 2 != 0) {
                temp = temp >> 1;
            }
            res[i] = res[temp] + 1;
        }
        return res;
    }
}
