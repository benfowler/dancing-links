package au.id.bjf.sudoku.dlx;

import org.junit.jupiter.api.Test;

/**
 * Guardian puzzle 307.  Medium difficulty.
 */
final class SudokuGuardian307Test implements SudokuProblem {

	@Test
	void test() {
		// Just print answer for time being
		SudokuTester.builder()
				.problem(getProblem())
				.solution(null)  // Just print answer for time being
				.build()
			.run();
	}

	@Override
	public byte[] getProblem() {
		return new byte[]{
				0, 0, 2, 7, 0, 9, 6, 0, 0,
				0, 7, 0, 0, 0, 2, 0, 8, 1,
				0, 0, 0, 0, 3, 0, 0, 0, 0,
				2, 4, 0, 3, 0, 0, 0, 5, 0,
				0, 0, 0, 0, 8, 0, 0, 0, 0,
				0, 1, 0, 0, 0, 6, 0, 7, 3,
				0, 0, 0, 0, 2, 0, 0, 0, 0,
				1, 8, 0, 5, 0, 0, 0, 9, 0,
				0, 0, 6, 1, 0, 8, 7, 0, 0
		};
	}
}
