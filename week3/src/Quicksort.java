import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by kshang on 22/12/2016.
 */
public class Quicksort {

    /**
     * Partition array from lo to hi.
     *
     * After partitioning, all items before the return index j are no larger the a[j];
     * all items after the return index jj are no smaller than a[j]
     *
     * @param a
     * @param lo
     * @param hi
     * @return
     */
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        while (true) {
            // find item on left to swap
            while (less(a[++i], a[lo])) {
                if (i == hi) break;
            }

            // find item on right to swap
            while (less(a[lo], a[--j])) {
                if (j == lo) break;
            }

            // check if pointers cross
            if (i >= j) break;

            // swap
            exchange(a, i, j);
        }

        // swap with partitioning item
        exchange(a, j, lo);

        // return index of item now known to be in place
        return j;
    }

    public static void sort(Comparable[] a) {
        // Shuffle the input to avoid worst case when the input was already sorted
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a,j + 1, hi);

    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private static void exchange(Comparable[] a, int idx1, int idx2) {
        Comparable tmp = a[idx1];
        a[idx1] = a[idx2];
        a[idx2] = tmp;
    }
}
