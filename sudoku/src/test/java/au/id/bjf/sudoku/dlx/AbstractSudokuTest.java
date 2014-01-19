package au.id.bjf.sudoku.dlx;

import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;
import au.id.bjf.dlx.DLXResult;
import au.id.bjf.dlx.DLXResultProcessor;

public abstract class AbstractSudokuTest extends TestCase {

	/**
	 * Return a problem to solve
	 * @return a byte array containing a problem to solve
	 */
	protected abstract byte[] getProblem();

	/**
	 * Return the problem's solution
	 * @return a byte array containing the solution to the problem given in
	 *     {@link #getProblem()}.  Return <code>null</code> if solution
	 *     shouldn't be checked.
	 */
	protected abstract byte[] getSolution();

	public void testSudokuSolver() {
		final byte[] problem = getProblem();
		final byte[] solution = getSolution();

		DLXResultProcessor processor = null;
		if (solution != null) {
			processor = new TestResultProcessor(solution);
		} else {
			processor = new TestResultProcessor();
		}

		final long startTime = System.nanoTime();
		new DLXSudokuSolver().solve(problem, processor);
		final long finishTime = System.nanoTime();
		final long elapsed = finishTime - startTime;
		System.out.printf("Run took %.2f msec\n", ((float)elapsed) / 1E6);
	}

	class TestResultProcessor implements DLXResultProcessor {

		private boolean solutionFound = false;

		private byte[] expectedSolution = null;

		public TestResultProcessor() {
			// Default constructor.  Don't set an expected solution; no
			// checking will be necessary.
		}

		public TestResultProcessor(byte[] expectedSolution) {
			this.expectedSolution = expectedSolution;
		}

		public boolean processResult(DLXResult result) {

			if (solutionFound) {
				throw new RuntimeException("Only one solution should exist!");
			}

			final Iterator<List<Object>> rows = result.rows();
			while (rows.hasNext()) {
				final List<Object> row = rows.next();
				byte r = -1; // canary values
				byte c = -1;
				byte v = -1;
				for (final Object obj : row) {
					if (obj instanceof CellValueLabel) {
						final CellValueLabel cvl = (CellValueLabel)obj;
						v = cvl.value;
					} else if (obj instanceof CellCoordinatesLabel) {
						final CellCoordinatesLabel ccl =
							(CellCoordinatesLabel)obj;
						r = ccl.row;
						c = ccl.col;
					}
				}
				assertTrue("Sudoku result row appears mangled " +
						"- values are missing",
						(r != -1) && (c != -1) && (v != -1));

				if (expectedSolution != null) {
					assertEquals("Values in expected and actual output " +
							"differ (row " + r + ", column " + c + ")",
							v, expectedSolution[r*9+c]);
				}
			}

			System.out.println(AbstractSudokuTest.this.getClass().getName());
			new DLXSudokuSolver().printPuzzle(System.out, result);

			solutionFound = true;
			return true;
		}
	}

}
