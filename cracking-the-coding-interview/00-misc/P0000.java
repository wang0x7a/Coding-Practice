/* An interger array contains elements in increasing order till some point
 * and then decreasing order, return the index of maximum number. Solution
 * should be less than O(N)
 * */

public class P0000 {
  public static void main(String[] args) {
    //int[] a = {1, 2, 3, 4, 5, 3, 1};
    //int[] a = {1, 2, 3, 4, 5, 6, 1};
    int[] a = {1, 2, 3, 4, 5, 6, 7};

    System.out.println(MaxNum(a));
    System.out.println(MaxNum2(a));
  }

  public static int MaxNum(int[] a) {
    int len = a.length;

    for (int i = 0; i < len - 1; i++) {
      if (a[i + 1] < a[i])
        return i;
    }

    return -1;
  }

  public static int MaxNum2(int[] a) {
    int len = a.length;

    return MaxNum2Helper(a, 0, len - 1);
  }

  public static int MaxNum2Helper(int[] a, int low, int high) {
    if (low >= high)
      return -1;

    int mid = (low + high) / 2;

    if (a[mid] > a[mid + 1] && a[mid] > a[mid - 1]) return mid;
    else if (a[mid] < a[mid + 1]) return MaxNum2Helper(a, mid + 1, high);
    else if (a[mid] < a[mid - 1]) return MaxNum2Helper(a, low, mid - 1);
    else return -1;
  }
}
