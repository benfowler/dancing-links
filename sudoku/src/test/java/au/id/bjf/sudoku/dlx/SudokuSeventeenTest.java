package au.id.bjf.sudoku.dlx;

import org.junit.jupiter.api.Test;

/**
 * The Seventeen problem.  Very hard.
 */
final class SudokuSeventeenTest implements SudokuProblem {

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
				0, 0, 6, 9, 0, 0, 0, 7, 0,
				0, 0, 0, 0, 1, 0, 0, 0, 2,
				8, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 2, 0, 0, 0, 0, 0, 0, 4,
				0, 0, 0, 0, 0, 0, 0, 0, 1,
				0, 0, 5, 0, 0, 6, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 6, 0,
				0, 0, 0, 0, 0, 2, 0, 5, 0,
				0, 1, 0, 0, 4, 3, 0, 0, 0,
		};
	}

	byte[] getSolution() {
		// Just print answer for time being
		return null;
	}

}
