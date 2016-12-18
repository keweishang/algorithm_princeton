import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by kshang on 18/12/2016.
 */
public class DequeTest {
    Deque<Integer> deque;

    @Before
    public void setUp() throws Exception {
        deque = new Deque<>();
    }

    @Test
    public void testAddFirstIterateAll() throws Exception {

        int[] input = {5,7,8};

        for (int num : input)
            deque.addFirst(num);
        assertThat(deque.size()).isEqualTo(3);
        assertThat(deque.isEmpty()).isFalse();

        int i = 2;
        for (int item : deque) {
            assertThat(item == input[i--]).isTrue();
        }
    }

    @Test
    public void testAddLastThreeIterateAll() throws Exception {

        int[] input = {5,7,8};

        for (int num : input)
            deque.addLast(num);
        assertThat(deque.size()).isEqualTo(3);
        assertThat(deque.isEmpty()).isFalse();

        while (!deque.isEmpty()) {
            deque.removeFirst();
        }
        assertThat(deque.isEmpty()).isTrue();
        assertThat(deque.size() == 0).isTrue();
    }

    @Test
    public void testAddFirstAndLastIterateAll() throws Exception {

        int[] first = {5,7,8};
        int[] last = {10,11,12};
        int[] expect = {8,7,5,10,11,12};

        for (int num : first)
            deque.addFirst(num);
        for (int num : last)
            deque.addLast(num);
        assertThat(deque.size()).isEqualTo(6);
        assertThat(deque.isEmpty()).isFalse();

        int i = 0;
        for (int item : deque) {
            assertThat(item == expect[i++]).isTrue();
        }
    }
}