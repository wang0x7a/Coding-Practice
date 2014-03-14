/* Find the common ancestor of two nodes.
 * */

public class P0413 {
  private class Node {
    int value;
    Node left, right;

    public Node(int value) {
      this.value = value;
    }
  }

  public Node findLCA(Node root, Node p, Node q) {
    //if (root == null || p == null || q == null)
    //  return null;
    // invalid inputs
    if (p == null || q == null)
      return null;
    // invalid inputs or not find the nodes
    if (root == null)
      return null;

    if (root == p || root == q) return root;

    // find p and q in the left subtree
    Node left = findLCA(root.left, p, q);
    // find them in the right subtree
    Node right = findLCA(root.right, p, q);

    // find them in the right and left subtrees respecitvely
    if (left != null && right != null) return root;
    // find one of them in the left
    return (left != null) ? left : right;
  }

  private class Result {
    Node node;
    boolean isAncestor;

    public Result(Node node, boolean isAncestor) {
      this.node = node;
      this.isAncestor = isAncestor;
    }
  }


  public Node LCA2(Node root, Node p, Node q) {
    if (p == null || q == null || root == null)
      return null;

    Result result = LCA2Helper(root, p, q);
    if (result.isAncestor)
      return result.node;
    else
      return null;
  }

  public Result LCA2Helper(Node root, Node p, Node q) {
    if (root == null)
      return new Result(null, false);

    Result rleft = LCA2Helper(root.left, p, q);
    if (rleft.isAncestor)
      return rleft;
    Result rright = LCA2Helper(root.right, p, q);
    if (rright.isAncestor)
      return rright;

    // we set isAncestor to true only when we find both the nodes in the tree
    if (rleft.node != null && rright.node != null)
      return new Result(root, true);
    
    if (root == p || root == q) {
      if (rleft.node != null || rleft.node != null)
        return new Result(root, true);
      else
        return new Result(root, false);
    }

    return new Result(rleft.node != null ? rleft.node : rright.node, false);
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
    P0413 p0413 = new P0413();
    Node root = p0413.buildTree();
    Node p = root.left.left.left;
    //Node q = root.left.left.left.left;
    Node q = root.left.right.right;

    Node res = p0413.findLCA(root, p, q);
    System.out.println(res.value);
  }
}
