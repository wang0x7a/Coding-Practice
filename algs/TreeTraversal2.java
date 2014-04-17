/* Iterative (non-recursive) tree traversal
 *
 * 1) In-order
 * 2) Pre-order
 * 3) Post-order
 */

import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class TreeTraversal2 {
    public static void InOrder(TreeNode root) {}

    public static void PreOrder(TreeNode root) {
        if (root == null)
            return;

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);

        TreeNode tmp;
        while (!stack.isEmpty()) {
            tmp = stack.pop();
            System.out.print(tmp.value + " ");

            if (tmp.right != null)
                stack.push(tmp.right);

            if (tmp.left != null)
                stack.push(tmp.left);
        }

        System.out.println();

        /*
        System.out.println(root);
        PreOrder(root.left);
        PreOrder(root.right);
        */
    }

    public static void PostOrder(TreeNode root) {}

    public static void main(String args[]) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        PreOrder(root);
    }
}

class TreeNode {
    int value;
    TreeNode left, right;

    public TreeNode() {}

    public TreeNode(int value) {
        this.value = value;
        this.left  = null;
        this.right = null;
    }

    public TreeNode(int value, TreeNode left, TreeNode right) {
        this.value = value;
        this.left  = left;
        this.right = right;
    }
}
