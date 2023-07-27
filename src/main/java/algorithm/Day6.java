package algorithm;

import sun.reflect.generics.tree.Tree;

import java.net.Inet4Address;
import java.util.*;

public class Day6 {

    private static TreeNode root = null;


    static {
        TreeNode treeNode8 = new TreeNode(8);
        TreeNode treeNode9 = new TreeNode(9);
        TreeNode treeNode4 = new TreeNode(4, treeNode8, treeNode9);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode2 = new TreeNode(2, treeNode4, treeNode5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode3 = new TreeNode(3, treeNode6, treeNode7);
        root = new TreeNode(1, treeNode2, treeNode3);
    }

    //144. 二叉树的前序遍历
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
//        preorderTraversal(root, list);
        LinkedList<TreeNode> treeNodes = new LinkedList<>();
        treeNodes.add(root);
        while (!treeNodes.isEmpty()) {
            TreeNode node = treeNodes.poll();
            list.add(node.val);
            if (node.right != null) {
                treeNodes.addFirst(node.right);
            }
            if (node.left != null) {
                treeNodes.addFirst(node.left);
            }
        }
        return list;
    }

    private void preorderTraversal(TreeNode root, List<Integer> list) {
        if (root == null) return;
        list.add(root.val);
        preorderTraversal(root.left, list);
        preorderTraversal(root.right, list);
    }

    //94. 二叉树的中序遍历
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
//        inorderTraversal(root, list);
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.addFirst(root);
                root = root.left;
            }
            root = stack.pollFirst();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }

    public List<Integer> inorderTraversal_2(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();

        return res;
    }

    private void inorderTraversal(TreeNode root, List<Integer> list) {
        if (root == null) return;
        inorderTraversal(root.left, list);
        list.add(root.val);
        inorderTraversal(root.right, list);
    }


    //145. 二叉树的后序遍历
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
//        postorderTraversal(root, list);
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        TreeNode cur = root, prev = null; //prev表示上一个已经处理完的结点
        while (cur != null || !linkedList.isEmpty()) {
            while (cur != null) {
                linkedList.addFirst(cur);
                cur = cur.left;
            }
            cur = linkedList.poll();
            //如果该结点无右孩子或者右孩子已经处理完成
            if (cur.right == null || cur.right == prev) {
                res.add(cur.val);
                prev = cur;
                cur = null;
            } else {
                linkedList.addFirst(cur);
                cur = cur.right;
            }
        }
        return res;
    }

    private void postorderTraversal(TreeNode root, List<Integer> list) {
        if (root == null) return;
        postorderTraversal(root.left, list);
        postorderTraversal(root.right, list);
        list.add(root.val);
    }


    //102. 二叉树的层序遍历
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int i = 0;
            int current_size = queue.size();
            while (i++ < current_size) {
                TreeNode node = queue.removeLast();
                level.add(node.val);
                if (node.left != null) {
                    queue.addFirst(node.left);
                }
                if (node.right != null) {
                    queue.addFirst(node.right);
                }
            }
            res.add(level);
        }
        return res;
    }

    //226. 翻转二叉树
    public TreeNode invertTree(TreeNode root) {
        invert_recursion(root);
        return root;
    }

    private void invert_recursion(TreeNode node) {
        if (node == null || (node.left == null && node.right == null)) return;
        invert_recursion(node.left);
        invert_recursion(node.right);
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
    }

    //101. 对称二叉树
    public boolean isSymmetric(TreeNode root) {
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        linkedList.addFirst(root);
        linkedList.addFirst(root);
        while (!linkedList.isEmpty()) {
            TreeNode p = linkedList.pollFirst();
            TreeNode q = linkedList.pollFirst();
            if (p == null && q == null) continue;
            if (p == null || q == null || p.val != q.val) return false;

            linkedList.addFirst(p.left);
            linkedList.addFirst(q.right);
            linkedList.addFirst(p.right);
            linkedList.addFirst(q.left);
        }
        return true;
    }

    //104. 二叉树的最大深度
    public int maxDepth(TreeNode root) {
//        if (root == null) return 0;
//        int res = 0;
//        LinkedList<TreeNode> queue = new LinkedList<>();
//        queue.add(root);
//        while (!queue.isEmpty()) {
//            res++;
//            int i = 0;
//            int current_size = queue.size();
//            while (i++ < current_size) {
//                TreeNode node = queue.removeLast();
//                if (node.left != null) {
//                    queue.addFirst(node.left);
//                }
//                if (node.right != null) {
//                    queue.addFirst(node.right);
//                }
//            }
//        }
//        return res;
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    //111. 二叉树的最小深度
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int left_depth = minDepth(root.left);
        int right_depth = minDepth(root.right);
        if (root.left != null && root.right == null) return left_depth + 1;
        if (root.right != null && root.left == null) return right_depth + 1;
        return Math.min(left_depth, right_depth) + 1;
    }

    //222. 完全二叉树的节点个数
    public int countNodes(TreeNode root) {
//        return preorderTraversal(root).size();

        return 0;
    }

    //110. 平衡二叉树
    public boolean isBalanced(TreeNode root) {
        return height(root) >= 0;
    }

    public int height(TreeNode root) {
        if (root == null) return 0;
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    //257. 二叉树的所有路径
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        TreeNode cur = root, prev = null;
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        while (cur != null || !linkedList.isEmpty()) {
            while (cur != null) {
                linkedList.addFirst(cur);
                cur = cur.left;
            }
            cur = linkedList.poll();
            if (cur.right == null && cur.left == null) {
                LinkedList<TreeNode> path = new LinkedList<>();
                StringBuilder builder = new StringBuilder();
                while (!linkedList.isEmpty()) {
                    path.addFirst(linkedList.poll());
                }
                while (!path.isEmpty()) {
                    TreeNode temp = path.poll();
                    builder.append(temp.val).append("->");
                    linkedList.addFirst(temp);
                }
                builder.append(cur.val);
                res.add(builder.toString());
            }
            if (cur.right == null || cur.right == prev) {

                prev = cur;
                cur = null;
            } else {
                linkedList.addFirst(cur);
                cur = cur.right;
            }
        }
        return res;
    }

    public List<String> binaryTreePaths_dfs(TreeNode root) {
        List<String> res = new ArrayList<>();
        binaryTreePaths_dfs(root, "", res);
        return res;
    }

    public void binaryTreePaths_dfs(TreeNode node, String str, List<String> res) {
        if (node == null) return;
        if (node.left != null) {
            binaryTreePaths_dfs(node.left, str + node.val + "->", res);
        }
        if (node.right != null) {
            binaryTreePaths_dfs(node.right, str + node.val + "->", res);
        }
        if (node.left == null && node.right == null) {
            res.add(str + node.val);
        }
    }

    //404. 左叶子之和
    public int sumOfLeftLeaves(TreeNode root) {
        int res = 0;
        res += sumOfLeftLeavesDfs(root, false);
        return res;
    }

    public int sumOfLeftLeavesDfs(TreeNode node, boolean isLeft) {
        if (node == null) return 0;
        if (isLeft && node.left == null && node.right == null) return node.val;
        return sumOfLeftLeavesDfs(node.left, true) + sumOfLeftLeavesDfs(node.right, false);
    }

    //513. 找树左下角的值
    public int findBottomLeftValue(TreeNode root) {
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        linkedList.addFirst(root);
//        boolean hasChild = false;
        int res = 0;
        while (!linkedList.isEmpty()) {
            int size = linkedList.size();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = linkedList.pollLast();
                if (i == 0) res = treeNode.val;
                if (treeNode.left != null) {
//                    hasChild = true;
                    linkedList.addFirst(treeNode.left);
                }
                if (treeNode.right != null) {
//                    hasChild = true;
                    linkedList.addFirst(treeNode.right);
                }
            }
//            if(!hasChild) return res;
        }
        return res;
    }

    //112. 路径总和
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        targetSum -= root.val;
        if (targetSum == 0 && root.left == null && root.right == null) return true;
        return hasPathSum(root.left, targetSum) || hasPathSum(root.right, targetSum);
    }

    //106. 从中序与后序遍历序列构造二叉树
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    public TreeNode buildTree(int[] inorder, int start1, int end1, int[] postorder, int start2, int end2) {
        if (start1 > end1) return null;
        if (end1 == start1) return new TreeNode(inorder[end1]);
        int root_val_post = postorder[end2];
        int root_index_in;
        for (root_index_in = start1; root_index_in <= end1; root_index_in++) {
            if (root_val_post == inorder[root_index_in]) {
                break;
            }
        }
        TreeNode treeNode = new TreeNode(inorder[root_index_in]);
        treeNode.left = buildTree(inorder, start1, root_index_in - 1, postorder, start2, start2 + root_index_in - start1 - 1);
        treeNode.right = buildTree(inorder, root_index_in + 1, end1, postorder, start2 + root_index_in - start1, end2 - 1);
        return treeNode;
    }

    //654. 最大二叉树
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTree(nums, 0, nums.length - 1);
    }

    public TreeNode constructMaximumBinaryTree(int[] nums, int start, int end) {
        if (start > end) return null;
        int max_index = start;
        for (int i = start + 1; i <= end; i++) {
            if (nums[max_index] < nums[i]) max_index = i;
        }
        TreeNode treeNode = new TreeNode(nums[max_index]);
        treeNode.left = constructMaximumBinaryTree(nums, start, max_index - 1);
        treeNode.right = constructMaximumBinaryTree(nums, max_index + 1, end);
        return treeNode;
    }

    //617. 合并二叉树
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return null;
        else if (root1 == null) return root2;
        else if (root2 == null) return root1;
        root1.val += root2.val;
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);
        return root1;
    }

    //700. 二叉搜索树中的搜索
    public TreeNode searchBST(TreeNode root, int val) {
        TreeNode p = root;
        while (p != null) {
            if (p.val > val) p = p.left;
            else if (p.val < val) p = p.right;
            else return p;
        }
        return null;
    }

    //98. 验证二叉搜索树
    public boolean isValidBST(TreeNode root) {
        TreeNode cur = root;
        Integer last = null;
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        while (cur != null || !linkedList.isEmpty()) {
            while (cur != null) {
                linkedList.addFirst(cur);
                cur = cur.left;
            }
            cur = linkedList.poll();
            if (last != null && last >= cur.val) {
                return false;
            }
            last = cur.val;
            cur = cur.right;
        }
        return true;
    }

    //530. 二叉搜索树的最小绝对差
    public int getMinimumDifference(TreeNode root) {
        int i = Integer.MAX_VALUE;
        int last = -1;
//        inorderTraversal(root, list);
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.addFirst(root);
                root = root.left;
            }
            root = stack.pollFirst();
            if (last != -1) {
                i = Math.min(i, root.val - last);
                last = root.val;
            } else last = root.val;
            root = root.right;
        }
        return i;
    }

    //501. 二叉搜索树中的众数
    List<Integer> res = new ArrayList<>();
    int max_count = 0;
    int lastNumber = 0;

    public int[] findMode(TreeNode root) {
        findMode(root, 0);
        int[] res_array = new int[this.res.size()];
        for (int i = 0; i < res.size(); i++) {
            res_array[i] = res.get(i);
        }
        return res_array;
    }

    private int findMode(TreeNode node, int count) {
        if (node == null) return count;
        count = findMode(node.left, count);
        if (count == 0 || node.val == lastNumber) count++;
        else count = 0;
        lastNumber = node.val;
        if (count == max_count) res.add(node.val);
        else if (count > max_count) {
            res.clear();
            max_count = count;
            res.add(node.val);
        }
        return findMode(node.right, count);
    }

    //236. 二叉树的最近公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        Deque<TreeNode> stack_p = find_path(p, root);
        Deque<TreeNode> stack_q = find_path(q, root);
        int size = Math.min(stack_p.size(), stack_q.size());
        TreeNode res = null;
        for (int i = 0; i < size; i++) {
            TreeNode node = stack_p.pollLast();
            if (node == stack_q.pollLast()) {
                res = node;
            }
        }
        return res;
    }

    private Deque<TreeNode> find_path(TreeNode q, TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode pre = null, cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.addFirst(cur);
                cur = cur.left;
            }
            if (stack.peekFirst() == q) break;
            cur = stack.pollFirst();
            if (cur.right == null || cur.right == pre) {
                pre = cur;
                cur = null;
            } else {
                stack.addFirst(cur);
                cur = cur.right;
            }
        }
        return stack;
    }

    //235. 二叉搜索树的最近公共祖先
    public TreeNode lowestCommonAncestor_bst(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val > p.val && root.val > q.val) return lowestCommonAncestor_bst(root.left, p, q);
        else if (root.val < p.val && root.val < q.val) return lowestCommonAncestor_bst(root.right, p, q);
        return root;
    }

    //701. 二叉搜索树中的插入操作
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode node = new TreeNode(val);
        if (root == null) return node;
        TreeNode cur = root, prev = null;
        while (cur != null) {
            prev = cur;
            if (cur.val > val) {
                cur = cur.left;
            } else if (cur.val < val) {
                cur = cur.right;
            } else {
                break;
            }
        }
        if (prev.val > val) {
            prev.left = node;
        } else if (prev.val < val) prev.right = node;
        return root;

    }

    //450. 删除二叉搜索树中的节点
    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode cur = root, prev = null;
        while (cur != null && cur.val != key) {
            prev = cur;
            if (cur.val > key) cur = cur.left;
            else cur = cur.right;
        }
        if (cur == null) return root;
        TreeNode res;
        if (cur.left == null) res = cur.right;
        else if (cur.right == null) res = cur.left;
        else {
            TreeNode right = cur.right;
            if (right.left != null) {
                TreeNode left = cur.left;
                while (left.right != null) left = left.right;
                left.right = right.left;
            }
            right.left = cur.left;
            res = right;
        }
        if (prev == null) return res;
        if (key > prev.val) prev.right = res;
        else prev.left = res;
        return root;
    }

    //669. 修剪二叉搜索树
    public TreeNode trimBST(TreeNode root, int low, int high) {
        while (root != null && (root.val < low || root.val > high)) {
            root = root.val < low ? root.right : root.left;
        }
        if (root == null) return null;
        TreeNode cur = root;
        while (cur.left != null) {
            while (cur.left != null && cur.left.val >= low) {
                cur = cur.left;
            }
            if (cur.left != null)
                cur.left = cur.left.right;
        }
        cur = root;
        while (cur.right != null) {
            while (cur.right != null && cur.right.val <= high) {
                cur = cur.right;
            }
            if (cur.right != null)
                cur.right = cur.right.left;
        }
        return root;
    }

    //108. 将有序数组转换为二叉搜索树
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    public TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        if (start > end) return null;
        int middle = (start + end) / 2;
        TreeNode node = new TreeNode(nums[middle]);
        node.right = sortedArrayToBST(nums, middle + 1, end);
        node.left = sortedArrayToBST(nums, start, middle - 1);
        return node;
    }

    //538. 把二叉搜索树转换为累加树
    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        if (root != null) {
            convertBST(root.right);
            sum += root.val;
            root.val = sum;
            convertBST(root.left);
        }
        return root;
    }


    public static void main(String[] args) {
        Day6 day6 = new Day6();
        day6.postorderTraversal(root);
        day6.convertBST(root);

        System.out.println();
    }


    static class TreeNode {
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
