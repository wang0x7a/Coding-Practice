/* P0026
 *
 * Flatten binary tree to linked list
 * Given a binary tree, flatten it to a linked list in-place.
 *
 * For example,
 *
 *       1
 *      / \
 *     2   5   => 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7
 *    / \   \
 *   3   4   6
 *            \
 *             7
 */

import java.util.Stack;

public class P0026 {
    public static TreeNode flatten(TreeNode root) {
        if (root == null)
            return null;

        Stack<TreeNode> stack = new Stack<TreeNode>();

        TreeNode p = root;
        TreeNode tmp;

        do {
            if (p.right != null) {
                stack.push(p.right);
                p.right = null;
            }

            if (p.left == null && !stack.isEmpty()) {
                tmp = stack.pop();
                p.left = tmp;
            }

            p = p.left;

        } while (!stack.isEmpty() || p != null);

        return root;
    }

    public static void print(TreeNode root) {
        for (TreeNode p = root; p != null; p = p.left)
            System.out.print(p.value + " ");

        System.out.println();
    }

    public static void preOrder(TreeNode root) {
        if (root == null)
            return;

        System.out.print(root.value + " ");
        preOrder(root.left);
        preOrder(root.right);

    }

    public static void main(String args[]) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.right.right = new TreeNode(7);

        flatten(root);
        print(root);

        //preOrder(root);
        //System.out.println();
    }
}

class TreeNode {
    int value;
    TreeNode left, right;

    public TreeNode() {}

    public TreeNode(int value) {
        this.value = value;
    }

    public TreeNode(int value, TreeNode left, TreeNode right) {
        this.value = value;
        this.left  = left;
        this.right = right;
    }
}
