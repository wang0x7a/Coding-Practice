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

    public static int cutoff = 3;

    public static void quick(int[] a) {
        int len = a.length;

        quick(a, 0, len - 1);
    }

    private static void quick(int[] a, int left, int right) {
        int i, j;
        int pivot;

        int len = right - left + 1;
        if (len > cutoff) {
            pivot = median3(a, left, right);
            i = left;
            j = right - 1;

            while (true) {
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
        else {
            insertion(a, left, right);
        }
    }

    private static int median3(int[] a, int left, int right) {
        int center = (left + right) / 2;

        // we don't have to check whether left == center, since when
        // the length of a is smaller than cutoff, we will use insertion
        // sort instead.
        if (a[left] > a[center])
            swap(a, left, center);
        if (a[left] > a[right])
            swap(a, left, right);
        if (a[center] > a[right])
            swap(a, center, right);

        swap(a, center, right - 1);

        return a[right - 1];
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
        //Sort2.merge(a);
        Sort2.quick(a);
        Sort2.print(a);

    }
    
}
