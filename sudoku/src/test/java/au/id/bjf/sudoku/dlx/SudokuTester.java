package au.id.bjf.sudoku.dlx;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import au.id.bjf.dlx.DLXResult;
import au.id.bjf.dlx.DLXResultProcessor;

public final class SudokuTester {

	private byte[] problem;

	private byte[] solution;

	private SudokuTester() {
		// hide default constructor; use builder
	}

	public static Builder builder() {
		return new Builder();
	}

	public void run() {

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
				assertTrue((r != -1) && (c != -1) && (v != -1),
						"Sudoku result row appears mangled " +
						"- values are missing");

				if (expectedSolution != null) {
					assertEquals(v, expectedSolution[r*9+c], "Values in expected and actual output " +
							"differ (row " + r + ", column " + c + ")");
				}
			}

			System.out.println(SudokuTester.this.getClass().getName());
			new DLXSudokuSolver().printPuzzle(System.out, result);

			solutionFound = true;
			return true;
		}
	}

	public static final class Builder {

		private final SudokuTester instance = new SudokuTester();

		public Builder problem(byte[] problem) {
			instance.problem = problem;
			return this;
		}

		public Builder solution(byte[] solution) {
			instance.solution = solution;
			return this;
		}

		public SudokuTester build() {
			return instance;
		}
	}

}
