/* Review of common sorting algorithms.
 *
 * In the routines of this class, we sort a given array, a, in increasing order.
 */

public class Sort2 {
    public static void shell(int[] a) {
        int len = a.length;

        int i, j, k;
        int tmp;
        for (k = len / 2; k > 0; k /= 2) {
            for (i = k; i < len; i++) {
                tmp = a[i];
                for (j = i; j >= k; j -= k) {
                    if (tmp < a[j - k])
                        a[j] = a[j - k];
                    else
                        break;
                }
                a[j] = tmp;
            }
        }

        return;
    }

    public static void heap(int[] a) {}

    public static void merge(int[] a) {
        int len = a.length;
        int[] tmp = new int[len];

        merge(a, tmp, 0, a.length - 1);
    }

    private static void merge(int[] a, int[] tmp, int left, int right) {
        int center = (left + right) / 2;

        if (left == center)
            return;

        merge(a, tmp, left, center);
        merge(a, tmp, center + 1, right);
        merge2(a, tmp, left, center, right);

        return;
    }

    private static void merge2(int[] a, int[] tmp, int leftPos, int leftEnd, 
            int rightEnd) {
        int rightPos = leftEnd + 1;
        int numElem  = rightEnd - leftPos + 1;

        int pos = leftPos;
        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            if (a[leftPos] < a[rightPos])
                tmp[pos++] = a[leftPos++];
            else
                tmp[pos++] = a[rightPos++];
        }

        while (leftPos <= leftEnd)
            tmp[pos++] = a[leftPos++];
        while (rightPos <= rightEnd)
            tmp[pos++] = a[rightPos++];

        for (int i = 0; i < numElem; i++, rightEnd--)
            // stupid mistake:
            // a[rightEnd--] = tmp[rightEnd--];
            a[rightEnd] = tmp[rightEnd];

        return;
    }

    public static void quick(int[] a) {

    }

    private static int median3(int[] a, int left, int right) {
        return 0;
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

    public static void insertion(int[] a) {
        insertion(a, 0, a.length - 1);
    }

    public static void insertion(int[] a, int start, int end) {
        int tmp;

        int i, j;
        for (i = start + 1; i <= end; i++) {
            tmp = a[i];
            for (j = i; j > start; j--) {
                if (tmp < a[j - 1])
                    a[j] = a[j - 1];
                else
                    break;
            }
            a[j] = tmp;
        }

        return;
    }

    public static void print(int[] a) {
        int len = a.length;

        for (int i = 0; i < len; i++)
            System.out.print(a[i] + " ");

        System.out.println();
    }

    public static void main(String args[]) {
        int[] a = {2, 10, 6, 1, 15, 3, 11, 8, 7, 9};

        //Sort2.insertion(a);
        //Sort2.shell(a);
        Sort2.merge(a);
        Sort2.print(a);

    }
    
}
