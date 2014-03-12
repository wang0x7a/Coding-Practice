/* Check if a tree is balanced
 * */

public class P0412 {
  private class Node {
    int value;
    Node left, right;

    public Node(int value) {
      this.value = value;
    }
  }

  private int getHeight(Node node) {
    if (node == null)
      return 0;

    return 1 + Math.max(getHeight(node.left), getHeight(node.right));
  }

  public boolean isBalanced(Node root) {
    if (root == null)
      return true;

    int leftHeight = 1 + getHeight(root.left);
    int rightHeight = 1 + getHeight(root.right); 

    //return Math.abs(leftHeight - rightHeight) <= 1 ? true : false;
    return isBalanced(root.right) && isBalanced(root.left);
  }
  
  public int checkHeight(Node root) {
    if (root == null)
      return 0;

    int leftHeight = checkHeight(root.left);
    if (leftHeight == -1)
      return -1;

    int rightHeight = checkHeight(root.right);
    if (rightHeight == -1)
      return -1;

    int heightDiff = leftHeight - rightHeight;
    if (Math.abs(heightDiff) > 1)
      return -1;
    else
      return Math.max(leftHeight, rightHeight) + 1;
  }

  public boolean isBalancedX(Node root) {
    if (checkHeight(root) == -1)
      return false;
    else
      return true;
  }

  public Node buildTree() {
    Node root = new Node(1);
    root.left = new Node(2);
    root.right = new Node(3);
    root.left.left = new Node(4);
    root.left.right = new Node(5);
    /*
    root.left.right.right = new Node(9);
    root.right.left = new Node(6);
    root.right.right= new Node(10);
    root.left.left.left = new Node(7);
    root.left.left.left.left = new Node(8);
    */
    //
    return root;
  }

  public static void main(String[] args) {
    P0412 p0412 = new P0412();
    Node root = p0412.buildTree();

    //System.out.println(p0412.isBalanced(root));
    System.out.println(p0412.isBalancedX(root));
  }
}
