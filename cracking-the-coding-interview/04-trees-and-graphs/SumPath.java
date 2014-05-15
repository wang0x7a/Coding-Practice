public class SumPath {
    public static void findSum(Node root, int sum) {
        int depth = depth(root);
        int[] buffer = new int[depth];

        findSum(root, sum, buffer, 0);
    }

    // a variant of pre-order traversal
    private static void findSum(Node node, int sum, int[] buffer, int level) {
        if (node == null)
            return;

        buffer[level] = node.value;
        int t = 0;
        for (int i = level; i >= 0; i--) {
            t += buffer[i];

            if (t == sum)
                print(buffer, i, level);
        }

        findSum(node.left, sum, buffer, level + 1);
        findSum(node.right, sum, buffer, level + 1);
    }

    private static void print(int[] buffer, int start, int end) {
        for (int i = start; i <= end; i++)
            System.out.print(buffer[i] + " ");

        System.out.println();
    }

    private static int depth(Node node) {
        if (node == null)
            return 0;

        int left  = depth(node.left);
        int right = depth(node.right);

        return Math.max(left, right) + 1;
    }

    public static void main(String args[]) {
        Node root = new Node(2);
        root.left = new Node(-2);
        root.right = new Node(1);
        root.left.left = new Node(3);
        root.left.right = new Node(6);
        root.left.right.right = new Node(2);
        root.right.left = new Node(3);
        root.right.right = new Node(5);
        root.left.left.left = new Node(2);
        root.left.left.left.left = new Node(1);

        findSum(root, 6);
    }
}

class Node {
    int value;
    Node left, right;

    public Node(int value) {
        this.value = value;
    }
}
