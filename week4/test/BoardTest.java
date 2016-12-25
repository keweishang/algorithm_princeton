import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by kshang on 25/12/2016.
 */
public class BoardTest {

    @Test
    public void testManhattan() throws Exception {
        int[][] init = {{0, 1, 3},
                {4, 2, 5},
                {7, 8, 6}};

        Board b = new Board(init);
        int manhattan = b.manhattan();
        assertThat(manhattan).isEqualTo(4);
    }

    @Test
    public void testEquals() throws Exception {

        int[][] init = {{0, 1, 3},
                {4, 2, 5},
                {7, 8, 6}};

        int[][] init2 = {{0, 1, 3},
                {4, 2, 5},
                {7, 8, 6}};

        Board b1 = new Board(init);
        Board b2 = new Board(init2);

        assertThat(b1.equals(b2)).isTrue();
    }
}