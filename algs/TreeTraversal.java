/* All tree traversal algos can be regarded as a bottom-up approach,
 * and the difference lies in when to deal with the current node,
 * i.e., before (pre-order), in the middle (in-order), or after 
 * (post-order) its children. Moreover, a leaf is generally processed as
 * a node whose children are null.
 *
 * In many situations, if we want to solve a tree problem in O(N) time,
 * we would probabaly make use of one of the traversal algos in terms of
 * the problem.
 * */


public class TreeTraversal {
  private class Node {
    int value;
    Node left, right;

    public Node(int value) {
      this.value = value;
    }
  }

  public void PostOrder(Node root) {
    if (root == null)
      return;

    PostOrder(root.left);
    PostOrder(root.right);

    System.out.print(root.value + " ");
    return;
  }

  public void PreOrder(Node root) {
    if (root == null)
      return;

    System.out.print(root.value + " ");
    PreOrder(root.left);
    PreOrder(root.right);

    return;
  }

  public void InOrder(Node root) {
    if (root == null)
      return;

    InOrder(root.left);
    System.out.print(root.value + " ");
    InOrder(root.right);

    return;
  }

  public Node buildTree() {
    Node root = new Node(1);
    root.left = new Node(2);
    root.right = new Node(3);
    root.left.left = new Node(4);
    root.left.right = new Node(5);
    root.left.right.right = new Node(9);
    root.right.left = new Node(6);
    root.right.right= new Node(10);
    root.left.left.left = new Node(7);
    root.left.left.left.left = new Node(8);
    //
    return root;
  }

  public static void main(String[] args) {
    TreeTraversal tt = new TreeTraversal();
    Node root = tt.buildTree();

    tt.PostOrder(root);
    System.out.println();
    tt.PreOrder(root);
    System.out.println();
    tt.InOrder(root);
    System.out.println();
  }
}
