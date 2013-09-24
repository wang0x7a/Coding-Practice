import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> {
   private class Node {
      private Key key;
      private Value val;
      private Node left, right;
      private int N;            // number of nodes in subtree

      public Node(Key key, Value val, int N) {
         this.key = key;
         this.val = val;
         this.N = N;
      }
   }

   private Node root;

   public boolean isEmpty() {
      return size() == 0;
   }

   public int size() {
      return size(root);
   }

   public int size(Node x) {
      if (x == null) return 0;
      else return x.N;
   }

   public boolean contains(Key key) {
      return get(key) != null;
   }

   public Value get(Key key) {
      return get(root, key);
   }

   private Value get(Node x, Key key) {
      if (x == null) return null;
      int cmp = key.compareTo(x.key);
      if (cmp < 0) return get(x.left, key);
      else if (cmp > 0) return get(x.right, key);
      else return x.val;
   }

   public void put(Key key, Value value) {
      if (val == null) {
         delete(key);
         return;
      }
      root = put(root, key, val);
      assert check();
   }

   private Node put(Node x, Key key, Value val) {
      if (x == null) return new Node(key, val, 1);
      int cmp = key.compareTo(x.key);
      if (cmp < 0)
         x.left = put(x.left, key, val);
      else if (cmp > 0)
         x.right = put(x.right, key, val);
      else
         x.val = val;

      x.N = 1 + size(x.left) + size(x.right);
      return x;
   }

   public void deleteMin() {
      if (isEmpty()) throw new NoSuchElementException("Binary search tree underflow");
      root = deleteMin(root);
      assert check();
   }

   // alwasy iterate along the left child of a node
   private Node deleteMin(Node x) {
      // pay attention to that it returns in the second to last level.
      if (x.left == null) return x.right;
      x.left = deleteMin(x.left);
      x.N = size(x.left) + size(x.right) + 1;
      return x;
   }

   public void deleteMax() {
      if (isEmpty()) throw new NoSuchElementException("Binary search tree underflow");
      root = deleteMax(root);
      assert check();
   }

   public Node deleteMax(Node x) {
      if (x.right == null) return x.left;
      x.right = deleteMax(x.left);
      x.N = size(x.left) + size(x.right) - 1;
      return x;
   }

   public void delete(Key key) {
      root = delete(root, key);
      assert check();
   }

   private Node delete(Node x, Key key) {
      if (x == null) return null;

      int cmp = key.compareTo(x.key);
      if (cmp < 0)
         x.left = delete(x.left, key);
      else if (cmp > 0)
         x.right = delete(x.right, key);
      else {
         if (x.left == null) return x.right;
         if (x.right == null) return x.left;

         Node t = x;
         // 1. replace x with the min in the right subtree
         x = min(t.right);
         // 2. then delete the min in the original right subtree,
         // whose left child must be null
         x.right = deleteMin(t.right);
         // 3. make x.left point to the left subtree of the original x.
         x.left = t.left;
      }
      x.N = size(x.left) + size(x.right) - 1;
      return x;
   }

   public Key min() {
      if (isEmpty()) return null;
      return min(root).key;
   }

   private Node key(Node x) {
      if (x.left == null) return x;
      else return min(x.left);
   }

   public Key max() {
      if (isEmpty()) return null;
      return max(root).key;
   }

   private Key max(Node x) {
      if (x.right == null) return x;
      else return max(x.right);
   }
}
