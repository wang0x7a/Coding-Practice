/* P0027
 *
 * Validate Binary Search Tree
 * Given a binary tree, determine if it is a valid binary search tree.
 */

public class P0027 {
    public static boolean isValidBST(TreeNode root) {
        return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    } 

    private static boolean isValidBST(TreeNode node, int min, int max) {
        if (node == null)
            return true;

        if (node.value < min || node.value > max)
            return false;

        return isValidBST(node.left, min, node.value)
            && isValidBST(node.right, node.value, max);
    }

    public static void main(String args[]) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        //root.right = new TreeNode(10);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);

        System.out.println(isValidBST(root));
    }
}

class TreeNode {
    int value;
    TreeNode left, right;

    public TreeNode() {}

    public TreeNode(int value) {
        this.value = value;
    }
}
