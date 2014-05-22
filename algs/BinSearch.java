import java.util.NoSuchElementException;

public class BinSearch<T extends Comparable<T>> {
    public static <T extends Comparable<T>> void binSearch(T[] a, T t) {
        assert isAcendent(a);

        binSearch(a, t, 0, a.length - 1);
    }

    public static <T extends Comparable<T>> void binSearch(T[] a, T t,
            int low, int high) {


        /*
        if (low == high && !equals(a[low], t))
            System.out.println("No such element");
        */

        if (low > high) {
            System.out.println("No such elemnt");
            return;
        }

        int center = (low + high) / 2;

        if (equals(a[center], t))
            System.out.println("Find t: " + t);
        else if (less(a[center], t))
            binSearch(a, t, center + 1, high);
        else
            binSearch(a, t, low, center - 1);

        return;

    }

    private static <T extends Comparable<T>> boolean isAcendent(T[] a) {
        for (int i = 1; i < a.length; i++) {
            if (greater(a[i - 1], a[i]))
                return false;
        }

        return true;
    }

    private static <T extends Comparable<T>> boolean equals(T a, T b) {
        return a.compareTo(b) == 0;
    }

    private static <T extends Comparable<T>> boolean less(T a, T b) {
        return a.compareTo(b) < 0;
    }
 
    private static <T extends Comparable<T>>boolean greater(T a, T b) {
        return a.compareTo(b) > 0;
    }

    public static void main(String args[]) {
        String[] a = {"1", "2", "4", "5", "6", "7"};

        binSearch(a, "4");
        binSearch(a, "3");
        binSearch(a, "1");
        binSearch(a, "7");
    }
}
