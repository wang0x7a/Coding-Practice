/* A summary of common sort algos:
 * 1) shell sort
 * 2) heap sort
 * 3) merge sort
 * 4) quick sort
 * 5) insertion sort
 * 6) etc.
 * */

public class Sort {
  /* Shell sort:
   * There are two main loops in shell sort:
   * 1) the loop of decreasing the increament h_k to 1
   * 2) apply insertion sort to h_k-sort
   * */
  public static void shell(int[] a) {
    int i, j;
    int inc;

    int len = a.length;
    // We use Shell's increaments here
    for (inc = len / 2; inc > 0; inc /= 2) {
      // recall insertion sort, the sorting begins with the 2nd element
      // in a sequence. Here, we start from a[inc], which is the 2nd element
      // in a h_k sequence. Moreover, we need to touch every element of 
      // the array.
      for (i = inc; i < len; i++) {
        // tmp is the element we want to insert into its precedents
        int tmp = a[i];
        for (j = i; j >= inc; j -= inc) {
          if (tmp < a[j - inc])
            a[j] = a[j - inc];
          else
            break;
        } // loop of scanning a h_k sequence
        a[j] = tmp;
      }
    } // loop of running through increaments sequence
  }

  /* Heap sort:
   * Please reference to the solution to the problem of finding the top M
   * elements in a N-list.
   * */
  public static void heap(int[] a) {}

  public static void merge(int[] a) {
  }
  
  public static void quick(int[] a) {}

  /* Insertion sort:
   * picks up the current element, and inserts it into its precedents 
   * from the end.
   * */
  public static void insertion(int[] a) {
    int tmp;

    int len = a.length;
    for (int p = 1; p < len; p++) {
      // tmp is the element that we want to sort
      tmp = a[p];
      
      // compare tmp with its precedents
      int i;
      for (i = p; i > 0; i--) {
        if (tmp < a[i - 1])
          a[i] = a[i - 1];
        else
          break;
      }
      a[i] = tmp;
    }
  }

  public static void print(int[] a) {
    int len = a.length;

    for (int i = 0; i < len; i++)
      System.out.print(a[i] + " ");

    System.out.println();


  }

  public static void main(String[] args) {
    int[] a = {2, 10, 6, 1, 4, 3, 11, 8, 7, 9};

    Sort.shell(a);
    Sort.print(a);
  }
}
