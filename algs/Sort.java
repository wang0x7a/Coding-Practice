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
    int len = a.length;
    int[] tmp = new int[len];

    merge(a, tmp, 0, len - 1);
  }

  private static void merge(int[] a, int[] tmp, int left, int right) {
    int center;

    if (left < right) {
      center = (left + right) / 2;
      merge(a, tmp, left, center);
      merge(a, tmp, center + 1, right);
      merge2(a, tmp, left, center + 1, right);
    }
  }

  private static void merge2(int[] a, int[] tmp, int leftPos, int rightPos, 
      int rightEnd) {
    int leftEnd, numElem, tmpPos;

    leftEnd = rightPos - 1;
    numElem = rightEnd - leftPos + 1;
    tmpPos = leftPos;

    while (leftPos <= leftEnd && rightPos <= rightEnd)
      if (a[leftPos] <= a[rightPos])
        tmp[tmpPos++] = a[leftPos++];
      else
        tmp[tmpPos++] = a[rightPos++];

    while (leftPos <= leftEnd)
      tmp[tmpPos++] = a[leftPos++];
    while (rightPos <= rightEnd)
      tmp[tmpPos++] = a[rightPos++];

    for (int i = 0; i < numElem; i++, rightEnd--)
      a[rightEnd] = tmp[rightEnd];
  }
  
  public static int cutoff = 3;

  public static void quick(int[] a) {
    quick(a, 0, a.length - 1);
  }

  private static void quick(int[] a, int left, int right) {
    int i, j;
    int pivot;

    if (left + cutoff <= right) {
      pivot = median3(a, left, right);
      i = left; j = right - 1;
      //i = left; j = right;
      for (; ;) {
        while (a[++i] < pivot) {}
        while (a[--j] > pivot) {}
        if (i < j)
          swap(a, i, j);
        else
          break;
      }
      swap(a, i, right - 1);
      quick(a, left, i - 1);
      quick(a, i + 1, right);
    }
    else
      insertion(a, left, right);
  }

  private static void swap(int[] a, int i, int j) {
    int len = a.length;

    if (i >= len || j >= len) {
      System.out.println("Out of index");
      return;
    }

    int tmp = a[i];
    a[i] = a[j];
    a[j] = tmp;

    return;
  }

  private static int median3(int[] a, int left, int right) {
    int centre = (left + right) / 2;

    if (a[left] > a[centre])
      swap(a, left, centre);
    if(a[left] > a[right])
      swap(a, left, right);
    if(a[centre] > a[right])
      swap(a, centre, right);

    /* Invariant: a[left] <= a[centre] <= a[right]*/

    // make use of the invariant above
    swap(a, centre, right - 1);
    //swap(a, centre, right);
    return a[right - 1];
  }

  /* Insertion sort:
   * picks up the current element, and inserts it into its precedents 
   * from the end.
   * */
  public static void insertion(int[] a) {
    insertion(a, 0, a.length - 1);
  }

  public static void insertion(int[] a, int start, int end) {
    int tmp;

    for (int p = start + 1; p <= end ; p++) {
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
    int[] a = {2, 10, 6, 1, 15, 3, 11, 8, 7, 9};
    //int[] a = {2, 10, 6, 1, 4, 3, 11, 8, 7, 9};

    Sort.print(a);
    //Sort.insertion(a);
    //Sort.shell(a);
    //Sort.merge(a);
    Sort.quick(a);
    Sort.print(a);
  }
}
