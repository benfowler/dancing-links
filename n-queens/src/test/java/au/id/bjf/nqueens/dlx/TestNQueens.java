package au.id.bjf.nqueens.dlx;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import au.id.bjf.dlx.DLXResult;
import au.id.bjf.dlx.DLXResultProcessor;

/**
 * Sanity-test the solution to the N-Queens problem by generating the answers
 * and sanity checking each one
 */
class TestNQueens {

	@Test
	void argumentHandling() {

		// assert that "new NQueensSolver(-1)" throws an IllegalArgumentException
		assertThrowsExactly(IllegalArgumentException.class, () -> {
			new NQueensSolver(-1);
		});

		// assert that "new NQueensSolver(0)" throws an IllegalArgumentException
		assertThrowsExactly(IllegalArgumentException.class, () -> {
			new NQueensSolver(0);
		});

		// assert that "new NQueensSolver(1)" throws an IllegalArgumentException
		assertThrowsExactly(IllegalArgumentException.class, () -> {
			new NQueensSolver(1);
		});

		// assert that "new NQueensSolver(2)" throws an IllegalArgumentException
		assertThrowsExactly(IllegalArgumentException.class, () -> {
			new NQueensSolver(2);
		});

		// assert that "new NQueensSolver(3)" throws an IllegalArgumentException
		assertThrowsExactly(IllegalArgumentException.class, () -> {
			new NQueensSolver(3);
		});

		// assert that "new NQueensSolver(4)" does not throw an exception
		NQueensSolver solver = new NQueensSolver(4);
		assertNotNull(solver);

		// assert that "new NQueensSolver(8)" does not throw an exception
		solver = new NQueensSolver(8);
		assertNotNull(solver);
	}

	@Test
	void nQueens() {
		for (int i = 4; i <= 8; ++i) {
			testNQueens(i);
		}
	}

	private void testNQueens(int n) {
		NQueensSolver solver = new NQueensSolver(n);
		assertNotNull(solver);
		TestNQueensDLXResultProcessor resultProcessor = new TestNQueensDLXResultProcessor(n);
		solver.solve(resultProcessor);
		assertTrue(resultProcessor.getResultCount() > 0, "Should see at least one set of results");
		System.out.println("For " + n + " queens, there are " + resultProcessor.getResultCount() + " solutions");
	}

	/**
	 * Process results from the algorithm test run; we do nothing but do some
	 * sanity checking over the results.
	 */
	static class TestNQueensDLXResultProcessor implements DLXResultProcessor {

		private int n;
		private int resultCount = 0;

		public TestNQueensDLXResultProcessor(int n) {
			this.n = n;
		}

		public boolean processResult(DLXResult result) {
			++resultCount;
			byte[] chessboard = NQueensSolver.mapResultIntoChessboard(result, n);

			// Verbose form
//			NQueensSolver.printChessboard(chessboard, n);

			// Compact form
			System.out.print(resultCount);
			System.out.print(": ");
			NQueensSolver.printChessboardCompact(chessboard, n);

			sanityCheckSolution(chessboard);
			return true; // see ALL results
		}

		private void sanityCheckSolution(byte[] chessboard) {
			assertNotNull(chessboard);
			assertEquals(n * n, chessboard.length);

			// Ranks
			for (int rank = 0; rank < n; ++rank) {
				int count = 0;
				for (int file = 0; file < n; ++file) {
					count += (chessboard[rank * n + file] != 0) ? 1 : 0;
				}
				assertEquals(1, count, "Must be only one queen in rank " + rank +
						", got " + count);
			}

			// Files
			for (int file = 0; file < n; ++file) {
				int count = 0;
				for (int rank = 0; rank < n; rank++) {
					count += (chessboard[rank * n + file] != 0) ? 1 : 0;
				}
				assertEquals(1, count, "Must be only one queen in file " + file +
						", got " + count);
			}

			// Diagonals
			for (int diag = 0; diag < (n - 2); ++diag) {
				// diagonal cells all lie on (x,y) where diag == x + y
				int count = 0;
				for (int rank = 0; rank < n; rank++) {
					for (int file = 0; file < n; file++) {
						if (rank + file == diag) {
							count += (chessboard[rank * n + file] != 0) ? 1 : 0;
						}
					}
				}
				assertTrue((count == 0 || count == 1), "Must be maximum of one queen in diagonal " +
						diag);
			}

			// Reverse diagonals
			for (int diag = 0; diag < (n - 2); ++diag) {
				// diagonal cells all lie on (x,y) where diag == n - 1 - x + y
				int count = 0;
				for (int rank = 0; rank < n; rank++) {
					for (int file = 0; file < n; file++) {
						if ((n - 1 - rank + file) == diag) {
							count += (chessboard[rank * n + file] != 0) ? 1 : 0;
						}
					}
				}
				assertTrue((count == 0 || count == 1), "Must be maximum of one queen in reverse diagonal " +
						diag);
			}
		}

		public int getResultCount() {
			return resultCount;
		}
	}

}
