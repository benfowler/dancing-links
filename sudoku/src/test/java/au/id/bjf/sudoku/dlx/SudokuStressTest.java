package au.id.bjf.sudoku.dlx;

import junit.framework.TestCase;
import au.id.bjf.dlx.NullResultProcessor;

/**
 * Solve a bunch of Sudoku over and over.
 */
public class SudokuStressTest extends TestCase {

	private static final int RUN_SIZE = 10000;

	public final void testSudokuStressTest() {
		System.out.println("Stress test. Solving " + RUN_SIZE + " puzzles.");
		DLXSudokuSolver solver = new  DLXSudokuSolver();
		final AbstractSudokuTest[] testPool = getTestsToRun();
		long totalTime = 0;
		for (int i = 0; i < RUN_SIZE; ++i) {
			final AbstractSudokuTest thisTest = testPool[i % testPool.length];
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

	public AbstractSudokuTest[] getTestsToRun() {
		return new AbstractSudokuTest[] {
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
