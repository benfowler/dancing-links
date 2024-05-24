package au.id.bjf.sudoku.dlx;

import org.junit.jupiter.api.Test;

import au.id.bjf.dlx.NullResultProcessor;

/**
 * Solve a bunch of Sudoku over and over.
 */
class SudokuStressTest {

	private static final int RUN_SIZE = 10000;

	@Test
	final void sudokuStressTest() {
		System.out.println("Stress test. Solving " + RUN_SIZE + " puzzles.");
		DLXSudokuSolver solver = new  DLXSudokuSolver();
		final SudokuProblem[] testPool = getTestsToRun();
		long totalTime = 0;
		for (int i = 0; i < RUN_SIZE; ++i) {
			final SudokuProblem thisTest = testPool[i % testPool.length];
			final byte[] puzzle = thisTest.getProblem();
			final long startTime = System.nanoTime();
			solver.solve(puzzle, new NullResultProcessor(false));
			final long endTime = System.nanoTime();
			final long elapsedTime = endTime - startTime;
			totalTime += elapsedTime;
		}
		System.out.println("Total time elapsed for all runs: " 
				+ (totalTime / 1E6) + " msec");
		System.out.printf("Average time per test: %.2f msec\n",
				((float)totalTime) / (((float)RUN_SIZE) * 1E6));
	}

	public SudokuProblem[] getTestsToRun() {
		return new SudokuProblem[] {
				new SudokuEscargotTest(),
				new SudokuEverestTest(),
				new SudokuGuardian306Test(),
				new SudokuGuardian307Test(),
				new SudokuGuardian309Test(),
				new SudokuGuardian310Test(),
				new SudokuSeventeenTest()
		};
	}

}
