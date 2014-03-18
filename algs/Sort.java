/* A summary of common sort algos:
 * 1) shell sort
 * 2) heap sort
 * 3) merge sort
 * 4) quick sort
 * 5) etc.
 * */

public class Sort {
  public static void shell(int[] a) {
    int i, j;
    int inc;
  }

  public static void heap(int[] a) {}

  public static void merge(int[] a) {}
  
  public static void quick(int[] a) {}

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

    Sort.insertion(a);
    Sort.print(a);
  }
}
