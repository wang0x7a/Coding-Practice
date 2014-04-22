/* P0028
 *
 * Reconstruct a binary tree with the results of in-order and pre-order
 * traversals
 */

public class P0028 {
    public static TreeNode reconstructBST(int[] inOrder, int[] preOrder) {
        int inLen  = inOrder.length;
        int preLen = preOrder.length;

        if (inLen != preLen)
            return null;

        if (inLen == 0)
            return null;

        TreeNode root = reconstructBST(inOrder, 0, inLen - 1, preOrder, 0);
        return root;
    }

    private static TreeNode reconstructBST(int [] inOrder, int inLeft, 
            int inRight, int[] preOrder, int parentPos) {
        if (inLeft > inRight)
            return null;

        //if (inLeft == inRight)
        //    return new TreeNode(inOrder[inLeft]);

        int value = preOrder[parentPos];

        int i;
        for (i = inLeft; i <= inRight; i++) {
            if (inOrder[i] == value)
                break;
        }

        TreeNode parent = new TreeNode(value);
        parent.left  = reconstructBST(inOrder, inLeft, i - 1, preOrder, parentPos + 1);
        parent.right = reconstructBST(inOrder, i + 1, inRight, preOrder, 
                parentPos + i - inLeft + 1);

        return parent;

    }

    public static void postOrder(TreeNode root) {
        if (root == null)
            return;

        postOrder(root.left);
        postOrder(root.right);

        System.out.print(root.value + " ");
    }

    public static void main(String args[]) {
        int[] inOrder  = {3, 2, 4, 1, 5, 7, 6};
        int[] preOrder = {1, 2, 3, 4, 5, 6, 7}; 

        TreeNode root = reconstructBST(inOrder, preOrder);

        postOrder(root);
        System.out.println();
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
