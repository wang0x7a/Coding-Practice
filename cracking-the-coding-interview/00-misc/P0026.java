/* P0026
 *
 * Flatten binary tree to linked list
 * Given a binary tree, flatten it to a linked list in-place.
 *
 * For example,
 *       1
 *      / \
 *     2   5   => 1 -> 2 -> 3 -> 4 -> 5 -> 6
 *    / \   \
 *   3  4    6
 */

public class P0026 {
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
