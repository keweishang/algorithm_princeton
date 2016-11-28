
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class PercolationTest {
	private Percolation percolation = new Percolation(4);

	@Test
	public void test_init_state() throws Exception {
		for (int row = 1; row <= 4; row++) {
			for (int col = 1; col <= 4; col++) {
				assertOpenFull(percolation, row, col, false);
			}
		}
		Assertions.assertThat(percolation.percolates()).isFalse();
	}

	@Test
	public void test_union_ok() throws Exception {
		percolation.open(1, 4);
		percolation.open(2, 4);
		assertOpenFull(percolation, 1, 4, true);
		assertOpenFull(percolation, 2, 4, true);

		assertOpenFull(percolation, 1, 3, false);
		assertOpenFull(percolation, 2, 3, false);
		assertOpenFull(percolation, 3, 4, false);

	}

	@Test
	public void test_no_union_if_closed() throws Exception {
		percolation.open(1, 4);
		percolation.open(2, 4);

		assertOpenFull(percolation, 1, 3, false);
		assertOpenFull(percolation, 2, 3, false);
		assertOpenFull(percolation, 3, 4, false);

	}

	@Test
	public void test_no_remote_union() throws Exception {
		percolation.open(1, 4);
		percolation.open(2, 4);

		assertOpenFull(percolation, 1, 1, false);
		assertOpenFull(percolation, 2, 1, false);
	}

	@Test
	public void test_percolate_straight() {
		percolation.open(1, 4);
		percolation.open(2, 4);
		percolation.open(3, 4);
		percolation.open(4, 4);
		assertOpenFull(percolation, 1, 4, true);
		assertOpenFull(percolation, 2, 4, true);
		assertOpenFull(percolation, 3, 4, true);
		assertOpenFull(percolation, 4, 4, true);
		Assertions.assertThat(percolation.percolates()).isTrue();
	}
	
	@Test
	public void test_percolate_no_straight() {
		percolation.open(1, 4);
		percolation.open(2, 4);
		percolation.open(2, 3);
		percolation.open(3, 3);
		percolation.open(3, 2);
		percolation.open(4, 2);
		assertOpenFull(percolation, 1, 4, true);
		assertOpenFull(percolation, 2, 4, true);
		assertOpenFull(percolation, 2, 3, true);
		assertOpenFull(percolation, 3, 3, true);
		assertOpenFull(percolation, 3, 2, true);
		assertOpenFull(percolation, 4, 2, true);
		Assertions.assertThat(percolation.percolates()).isTrue();
	}
	
	@Test
	public void test_backwash() {
	    percolation.open(1, 4);
        percolation.open(2, 4);
        percolation.open(3, 4);
        percolation.open(4, 4);
        assertOpenFull(percolation, 1, 4, true);
        assertOpenFull(percolation, 2, 4, true);
        assertOpenFull(percolation, 3, 4, true);
        assertOpenFull(percolation, 4, 4, true);
        Assertions.assertThat(percolation.percolates()).isTrue();
        
        percolation.open(4, 2);
        Assertions.assertThat(percolation.isFull(4, 2)).isFalse();
	}

	private void assertOpenFull(Percolation percolation, int row, int col, boolean openFull) {
		if (openFull) {
			Assertions.assertThat(percolation.isOpen(row, col)).isTrue();
			Assertions.assertThat(percolation.isFull(row, col)).isTrue();
		} else {
			Assertions.assertThat(percolation.isOpen(row, col)).isFalse();
			Assertions.assertThat(percolation.isFull(row, col)).isFalse();
		}
	}

}
