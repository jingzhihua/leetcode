package algorithm;

class Day1 {
    //704. 二分查找
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int middle = (low + high) / 2;
            if (nums[middle] > target) {
                high = middle - 1;
            } else if (nums[middle] < target) {
                low = middle + 1;
            } else return middle;
        }
        return -1;
    }

    //27. 移除元素
    public int removeElement(int[] nums, int val) {
        int val_count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                val_count++;
            } else {
                nums[i - val_count] = nums[i];
            }
        }
        return nums.length - val_count;
    }

    //977. 有序数组的平方
    public int[] sortedSquares(int[] nums) {
        int indexOfNatural = 0;
        for (int i = 0; i < nums.length; i++) {
            indexOfNatural = i;
            if (nums[i] >= 0) break;
        }

        if (indexOfNatural > 0) {
            int i = 0;
            int[] abs_nums = new int[nums.length];
            int left = indexOfNatural - 1;
            int right = indexOfNatural;
            while (left >= 0 && right < nums.length) {
                if (-nums[left] > nums[right]) {
                    abs_nums[i++] = nums[right++];
                } else {
                    abs_nums[i++] = -nums[left--];
                }
            }
            while (left >= 0) abs_nums[i++] = -nums[left--];
            while (right < nums.length) abs_nums[i++] = nums[right++];
            nums = abs_nums;
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] *= nums[i];
        }
        return nums;
    }

    //209. 长度最小的子数组
    public int minSubArrayLen(int target, int[] nums) {
        int res = nums.length + 1;
        int sum = 0;
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            sum += nums[j];
            while ((sum - nums[i]) >= target) {
                sum -= nums[i++];
            }
            if (sum >= target)
                res = Math.min(res, j - i + 1);
        }
        return res == nums.length + 1 ? 0 : res;
    }

    //59. 螺旋矩阵 II
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int left = 0, top = 0, right = n - 1, bottom = n - 1;
        int nums = 1;
        int i = 0, j = 0;
        while (nums <= n * n) {
            res[i][j] = nums;
            if (j < right && i == top) {
                j++;
                if (j == right) {
                    top++;
                }
            } else if (i < bottom && j == right) {
                i++;
                if (i == bottom) {
                    right--;
                }
            } else if (j > left && i == bottom) {
                j--;
                if (j == left) {
                    bottom--;
                }
            } else if (i > top && j == left) {
                i--;
                if (i == top) {
                    left++;
                }
            }
            nums++;
        }
        return res;
    }

    public static void main(String[] args) {
        Day1 solution = new Day1();
//        solution.search(new int[]{-1, 0, 3, 5, 9}, 0);

        solution.generateMatrix(4);
    }
}