package au.id.bjf.sudoku.dlx;

import org.junit.jupiter.api.Test;

/**
 * Guardian puzzle 310.  Rated 'hard'.
 */
final class SudokuGuardian310Test implements SudokuProblem {

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
				0, 6, 0, 0, 4, 0, 0, 0, 0,
				0, 7, 0, 0, 8, 0, 2, 1, 0,
				0, 0, 5, 9, 0, 0, 7, 6, 0,
				0, 0, 2, 0, 0, 0, 0, 0, 6,
				0, 4, 0, 0, 0, 0, 0, 5, 0,
				7, 0, 0, 0, 0, 0, 8, 0, 0,
				0, 5, 3, 0, 0, 8, 1, 0, 0,
				0, 2, 9, 0, 1, 0, 0, 4, 0,
				0, 0, 0, 0, 3, 0, 0, 2, 0
		};
	}

	byte[] getSolution() {
		// Just print answer for time being
		return null;
	}

}
