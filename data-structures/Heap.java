public class Heap {
  private class PriorityQueue {
    int size;
    int capacity;
    int[] elements;

    public PriorityQueue(int capacity) {

      this.capacity = capacity;
      this.size = 0;
      // elements[0] is a sentinel
      elements = new int[capacity + 1];
      // this is a min heap
      elements[0] = Integer.MIN_VALUE;
    }
  }

  private PriorityQueue pq;

  public Heap(int capacity) {
    this.pq = new PriorityQueue(capacity);
  }

  public Heap() {
    this.pq = new PriorityQueue(128);
  }

  public int getLeftChild(int parent) {
    return pq.elements[parent * 2];
  }

  public int getRightChild(int parent) {
    return pq.elements[parent * 2 + 1];
  }

  public int getParent(int child) {
    return pq.elements[child / 2];
  }

  public void insert(int value) {
    if (pq.size >= pq.capacity) {
      System.out.println("Reaches the capacity of the heap: should less than " + pq.capacity);
      return;
    }

    pq.size++;
    int i;
    for (i = pq.size; pq.elements[i / 2] > value; i = i / 2) {
      pq.elements[i] = pq.elements[i / 2];
    }
    pq.elements[i] = value;
    return;
  }

  public int deleteMin() {
    if (pq.size == 0) {
      System.out.println("Nothing to delete!");
      return -1;
    }

    int res = pq.elements[1];

    // move the last element to the first
    pq.elements[1] = pq.elements[pq.size];
    pq.elements[pq.size] = 0;
    pq.size--;
    // re-heapfy
    heapfy();

    return res;
  }

  private void heapfy() {
    int i = 1;
    int target = pq.elements[1];
    while (i <= pq.size / 2) {
      int left = i * 2;
      int right = i * 2 + 1;
      int tmp = left;

      if (pq.elements[i] < pq.elements[left]) {
        if (right > pq.size || pq.elements[i] < pq.elements[right]) return;
      }
      //    && (right <= pq.size && pq.elements[i] < pq.elements[right]))
        //return;
      //  break;


      // when comparing among three or more elements, we need to fix the index of
      // one of them
      if (pq.elements[i] > pq.elements[left]) {
        pq.elements[i] = pq.elements[left];
        // wrong:
        // i = left;
        tmp  = left;
      }
      if (right <= pq.size && pq.elements[i] > pq.elements[right]) {
        pq.elements[i] = pq.elements[right];
        // wrong:
        // i = right;
        tmp = right;
      }

      i = tmp;
      pq.elements[i] = target;
    }

    return;
  }

  public void print() {
    for (int i = 1; i <= pq.size; i++) {
      if (pq.elements[i] == 0)
        break;
      System.out.print(pq.elements[i] + " ");
    }

    System.out.println();
  }

  public static void main(String[] args) {
    Heap heap = new Heap();
    //int[] a = {2, 1, 3, 4, 10, 9, 8, 7, 5, 6};
    int[] a = {11, 12, 20, 14, 16, 13, 2, 1, 3, 4, 10, 9, 8, 7, 5, 6};
    for (int i = 0; i < a.length; i++)
      heap.insert(a[i]);
    heap.print();

    for (int i = 0; i < a.length; i++) {
      System.out.print(heap.deleteMin() + " ");
    }
    //heap.deleteMin();
    System.out.println();
    //heap.print();
  }
}
