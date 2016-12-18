import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by kshang on 18/12/2016.
 */
public class RandomizedQueueTest {

    private RandomizedQueue<Integer> randomizedQueue;

    @Before
    public void setUp() throws Exception {
        randomizedQueue = new RandomizedQueue<>();
    }

    @Test
    public void testEnqueue() throws Exception {
        fill();
        assertThat(randomizedQueue.size()).isEqualTo(3);
        assertThat(randomizedQueue.isEmpty()).isFalse();
    }

    @Test
    public void testDequeueSize() throws Exception {
        fill();
        List<Integer> res = new ArrayList<>();
        while (!randomizedQueue.isEmpty()) {
            res.add(randomizedQueue.dequeue());
        }
        assertThat(res.size()).isEqualTo(3);
    }

    @Test
    public void testDequeueRandomness() throws Exception {
        Set<List<Integer>> res = new HashSet<>();

        for (int i = 0; i < 10; i++) {
            fill();
            List<Integer> list = new ArrayList<>();
            while (!randomizedQueue.isEmpty()) {
                list.add(randomizedQueue.dequeue());
            }
            assertThat(list.size()).isEqualTo(3);
            res.add(list);
        }

        assertThat(res.size() > 1).isTrue();
    }

    @Test
    public void testIteratorRandomness() throws Exception {
        Set<List<Integer>> res = new HashSet<>();

        fill();
        for (int i = 0; i < 10; i++) {
            List<Integer> list = new ArrayList<>();
            for (int item : randomizedQueue)
                list.add(item);
            assertThat(list.size()).isEqualTo(3);
            res.add(list);
        }

        assertThat(res.size() > 1).isTrue();
    }

    private void fill() {
        randomizedQueue.enqueue(2);
        randomizedQueue.enqueue(5);
        randomizedQueue.enqueue(3);
    }
}