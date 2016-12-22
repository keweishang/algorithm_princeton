import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by kshang on 22/12/2016.
 */
public class QuicksortTest {

    @Test
    public void sortRandomInput() throws Exception {
        Integer[] items = new Integer[10];
        // Randomly generate input items
        for (int i = 0; i < items.length; i++) {
            items[i] = StdRandom.uniform(100);
        }
        Quicksort.sort(items);
        assertThat(items).isSorted();
    }

    @Test
    public void sortEqualItems() throws Exception {
        Integer[] items = new Integer[10];
        // Randomly generate input items
        for (int i = 0; i < items.length; i++) {
            items[i] = 100;
        }
        Quicksort.sort(items);
        assertThat(items).isSorted();
    }

    @Test
    public void sortSingleItem() throws Exception {
        Integer[] items = new Integer[1];
        // Randomly generate input items
        for (int i = 0; i < items.length; i++) {
            items[i] = 100;
        }
        Quicksort.sort(items);
        assertThat(items).isSorted();
    }

    @Test
    public void sortEmptyArray() throws Exception {
        Integer[] items = new Integer[0];
        // Randomly generate input items
        for (int i = 0; i < items.length; i++) {
            items[i] = 100;
        }
        Quicksort.sort(items);
        assertThat(items).isSorted();
    }

}