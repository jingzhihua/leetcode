package algorithm;

import java.util.*;

public class SortAlgorithm {

    //selection sort
    private void selectionSort(int[] array) {
        if (array == null || array.length < 2) return;
        int n = array.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (array[i] > array[j]) swap(array, i, j);
            }
        }
    }

    //bubble sort
    private void bubbleSort(int[] array) {
        if (array == null || array.length < 2) return;
        int n = array.length;
        for (int i = 0; i < n; i++) {
            boolean flag = false;
            for (int j = 1; j < n - i; j++) {
                if (array[j - 1] > array[j]) {
                    swap(array, j - 1, j);
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }

    //insert sort
    private void insertSort(int[] array) {
        if (array == null || array.length < 2) return;
        int n = array.length;
        for (int i = 1; i < n; i++) {
            int j = i;
            while (j > 0 && array[j] < array[j - 1]) {
                swap(array, j, j - 1);
                j--;
            }
        }
    }

    //quick sort
    private void quickSort(int[] array, int left, int right) {
        int n = right - left + 1;
        if (array == null || n < 2) return;
        else if (n == 2) {
            if (array[left] > array[right]) swap(array, left, right);
            return;
        }
        int temp = array[left];
        int i = left, j = right;
        while (i < j) {
            while (j > i && array[j] >= temp) j--;
            array[i] = array[j];
            while (j > i && array[i] <= temp) i++;
            array[j] = array[i];
        }
        array[i] = temp;
        quickSort(array, left, j - 1);
        quickSort(array, j + 1, right);
    }

    //merge sort
    private int[] mergeSort(int[] array, int left, int right) {
        int n = right - left + 1;
        if (n == 1) {
            return new int[]{array[left]};
        } else if (n == 2) {
            if (array[left] > array[right]) swap(array, left, right);
            return new int[]{array[left], array[right]};
        }
        int middle = (left + right) / 2;
        int[] sort_a = mergeSort(array, left, middle);
        int[] sort_b = mergeSort(array, middle + 1, right);
        int[] temp = new int[n];
        left = 0;
        right = 0;
        for (int i = 0; i < n; i++) {
            if (right >= sort_b.length) {
                temp[i] = sort_a[left++];
            } else if (left >= sort_a.length || sort_a[left] > sort_b[right]) {
                temp[i] = sort_b[right++];
            } else {
                temp[i] = sort_a[left++];
            }
        }
        return temp;
    }

    //tree pre-order traversal
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        linkedList.addFirst(root);
        while (!linkedList.isEmpty()) {
            TreeNode treeNode = linkedList.pollFirst();
            res.add(treeNode.val);
            if (treeNode.right != null) linkedList.addFirst(treeNode.right);
            if (treeNode.left != null) linkedList.addFirst(treeNode.left);
        }

        return res;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        TreeNode p = root;
        while (!linkedList.isEmpty() || p != null) {
            while (p != null) {
                linkedList.addFirst(p);
                p = p.left;
            }
            p = linkedList.pollFirst();
            res.add(p.val);
            p = p.right;
        }
        return res;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        TreeNode p = root;
        TreeNode last = null;
        while (!linkedList.isEmpty() || p != null) {
            while (p != null) {
                linkedList.addFirst(p);
                p = p.left;
            }
            p = linkedList.pollFirst();
            if (p.right == null || p.right == last) {
                res.add(p.val);
                last = p;
                p = null;
            } else {
                linkedList.add(p);
                p = p.right;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        SortAlgorithm sortAlgorithm = new SortAlgorithm();
        int[] array = new int[20];
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            array[i] = random.nextInt(1000);
        }
        System.out.println(Arrays.toString(array));
//        sortAlgorithm.selectionSort(array);
//        sortAlgorithm.bubbleSort(array);
//        sortAlgorithm.insertSort(array);
//        sortAlgorithm.quickSort(array, 0, array.length - 1)
        int[] sort = sortAlgorithm.mergeSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(sort));
        sortAlgorithm.youxu(sort);
    }

    private boolean youxu(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                System.out.println("error !!!");
                return false;
            }
        }
        return true;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
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
