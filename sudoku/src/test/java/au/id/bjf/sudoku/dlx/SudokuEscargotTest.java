package au.id.bjf.sudoku.dlx;

import org.junit.jupiter.api.Test;

/**
 * Escargot.  Supposed to be hard -- but apparently not.
 */
final class SudokuEscargotTest implements SudokuProblem {

	@Test
	void test() {
		SudokuTester.builder()
				.problem(getProblem())
				.solution(getSolution())
				.build()
			.run();
	}

	@Override
	public byte[] getProblem() {
		return new byte[]{
				1, 0, 0, 0, 0, 7, 0, 9, 0,
				0, 3, 0, 0, 2, 0, 0, 0, 8,
				0, 0, 9, 6, 0, 0, 5, 0, 0,
				0, 0, 5, 3, 0, 0, 9, 0, 0,
				0, 1, 0, 0, 8, 0, 0, 0, 2,
				6, 0, 0, 0, 0, 4, 0, 0, 0,
				3, 0, 0, 0, 0, 0, 0, 1, 0,
				0, 4, 0, 0, 0, 0, 0, 0, 7,
				0, 0, 7, 0, 0, 0, 3, 0, 0,
		};
	}

	private byte[] getSolution() {
		// Just print answer for time being
		return null;
	}

}
