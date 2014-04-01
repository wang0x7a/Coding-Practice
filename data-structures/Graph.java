import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;

public class Graph {
  private class Node {
    int value;

    Node next;
    Node[] neighbours;

    boolean isVisited;

    public Node(int value) {
      this.value = value;
    }

    public Node(int value, Node[] neighbours) {
      this.value = value;
      this.neighbours = neighbours;
    }
  }

  private class Queue {
    Node first, last;

    public void enqueue(Node node) {
      if (this.first == null) {
        this.first = node;
        this.last = first;
      }
      else {
        this.last.next = node;
        this.last = node;
      }
    }

    public Node dequeue() {
      if (this.first == null)
        return null;

      Node tmp = new Node(this.first.value, this.first.neighbours);
      first = first.next;
      return tmp;
    }
  }

  public void bfs(Node root, int x) {
    if (root.value == x)
      System.out.println("find in root");

    Queue queue = new Queue();
    root.isVisited = true;
    queue.enqueue(root);

    while (queue.first != null) {
      Node c = queue.dequeue();
      for (Node n : c.neighbours) {
        if (!n.isVisited) {
          System.out.println(n.value + " ");
          n.isVisited = true;
          if (n.value == x)
            System.out.println("Find " + n + " " + n.value);
          queue.enqueue(n);
        }
      }
    }
  }

  public void test() {
    Node n1 = new Node(1);
    Node n2 = new Node(2);
    Node n3 = new Node(3);
    Node n4 = new Node(4);
    Node n5 = new Node(5);

    n1.neighbours = new Node[]{n2, n3, n5};
    n2.neighbours = new Node[]{n1, n4};
    n3.neighbours = new Node[]{n1, n4, n5};
    n4.neighbours = new Node[]{n2, n3, n5};
    n5.neighbours = new Node[]{n1, n3, n4};

    bfs(n1, 5);
  }

  public static void main(String[] args) {
    Graph g = new Graph();

    g.test();
  }
}
