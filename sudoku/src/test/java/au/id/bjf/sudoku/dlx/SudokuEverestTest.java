package au.id.bjf.sudoku.dlx;

import org.junit.jupiter.api.Test;

/**
 * 'Hard' Sudoku.
 * <p>
 * See http://www.telegraph.co.uk/science/science-news/9359579/Worlds-hardest-sudoku-can-you-crack-it.html
 */
final class SudokuEverestTest implements SudokuProblem {

	@Test
	void test() {
		SudokuTester.builder()
				.problem(getProblem())
				.solution(null)  // Just print answer for time being
				.build()
			.run();
	}

	@Override
	public byte[] getProblem() {
		return new byte[]{
				8, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 3, 6, 0, 0, 0, 0, 0,
				0, 7, 0, 0, 9, 0, 2, 0, 0,
				0, 5, 0, 0, 0, 7, 0, 0, 0,
				0, 0, 0, 0, 4, 5, 7, 0, 0,
				0, 0, 0, 1, 0, 0, 0, 3, 0,
				0, 0, 1, 0, 0, 0, 0, 6, 8,
				0, 0, 8, 5, 0, 0, 0, 1, 0,
				0, 9, 0, 0, 0, 0, 4, 0, 0
		};
	}
}
