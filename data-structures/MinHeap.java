import java.util.NoSuchElementException;

public class MinHeap<T extends Comparable<T>> {
    private T[] pq;
    private int size;
    private int capacity;
    
    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.size     = 0;
        this.pq       = (T[]) new Comparable[capacity];
    }
    
    public MinHeap() {
        this(100);
    }

    public boolean isEmpty() {
        return size == 0;
    }
    
    public void insert(T t) {
        if (size == capacity)
            throw new ArrayIndexOutOfBoundsException("Exceeds the capacity of "
                    + "the heap ( at most" + capacity + " elements)");
            
        pq[size++] = t;
        swim(size - 1);
    }
    
    public T delMin() {
        if (size == 0)
            throw new NoSuchElementException("Empty heap!");
            
        T res = pq[0];
        
        // move the last element to the first place
        pq[0] = pq[size - 1];
        // destroy the last element
        pq[size--] = null;
        
        sink(0);

        return res;
    }
    
    private boolean greater(int i, int j) {
        return pq[i].compareTo(pq[j]) > 0;
    }
    
    private void swim(int k) {
        while (k > 0) {
            int parent = (k - 1) / 2;
            if (greater(parent, k)) {
                swap(parent, k);
                k = parent;
            }
            else
                break;
        }
    }
    
    private void sink(int k) {
        int child = 2 * k + 1;
        while (child <= size) {
            if (child != size && greater(child, child + 1))
                child++;
                
            if (!greater(k, child))
                break;
                
            swap(k, child);
            k = child;
            child = 2 * k + 1;
        }
    }
    
    private void swap(int i, int j) {
        T tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;
    }

    public static void main(String args[]) {
        MinHeap<Integer> heap = new MinHeap<Integer>();

        int[] a = {3, 1, 7, 2, 5, 4, 10, 9};
        
        for (int i = 0; i < a.length; i++)
            heap.insert(a[i]);

        while (!heap.isEmpty())
            System.out.print(heap.delMin() + " ");

        System.out.println();
    }
}
