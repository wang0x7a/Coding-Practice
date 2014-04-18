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
    public static void InOrder(TreeNode root) {
        if (root == null)
            return;

        Stack<TreeNode> stack = new Stack<TreeNode>();

        TreeNode p = root;
        // the stack is empty when finishing traversing the left child of root,
        // hence, we need to check if the right child is null, if not, entering
        // the right child.
        while (!stack.isEmpty() || p != null) {
            if (p == null) {
                p = stack.pop();
                System.out.print(p.value + " ");
                p = p.right;
            }
            else {
                stack.push(p);
                p = p.left;
            }
        }
        
        System.out.println();

        /*
        InOrder(root.left);
        System.out.println(root.value);
        InOrder(root.right);
        */

        return;
    }

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

        return;
    }

    // the difficulty of iterative post-order traversal lies in we need to
    // visit a parent (non-leaf node) three times: 
    //
    // 1) the first time is to push it onto the stack
    // 2) access its right child through the parement
    // 3) return to the parent after visiting its right child and print it.
    //
    // while iterative in-order and pre-order traversals only need the first 
    // two visits.
    public static void PostOrder(TreeNode root) {
        if (root == null)
            return;

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);

        Stack<TreeNode> res   = new Stack<TreeNode>();


        TreeNode p;
        while (!stack.isEmpty()) {
            p = stack.pop();

            res.push(p);

            if (p.left != null)
                stack.push(p.left);
            if (p.right != null)
                stack.push(p.right);
        }

        while (!res.isEmpty()) {
            p = res.pop();
            System.out.print(p.value + " ");
        }

        /*
        PostOrder(root.left);
        PostOrder(root.right);
        System.out.print(root.value + " ");
        */

        System.out.println();

        return;
    }

    public static void main(String args[]) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.left.left = new TreeNode(6);
        root.left.right.right = new TreeNode(7);
        root.left.right.left = new TreeNode(9);
        root.right.left = new TreeNode(8);

        PreOrder(root);
        InOrder(root);
        PostOrder(root);
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
