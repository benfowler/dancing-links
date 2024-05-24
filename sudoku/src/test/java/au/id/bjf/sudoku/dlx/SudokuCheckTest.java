package au.id.bjf.sudoku.dlx;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Do a little testing to ensure that the method that checks the Sudoku
 * property on DLXSudokuSolver ({@link DLXSudokuSolver#isValidSudoku}) works
 * correctly.
 */
final class SudokuCheckTest {

	final byte[] seventeen = {
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

	final byte[] moreThanOneSolution = {
			1, 0, 0, 0, 0, 0, 0, 0, 5,
			0, 0, 0, 0, 3, 0, 0, 0, 0,
			0, 0, 2, 0, 4, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 3, 4, 0, 0, 0, 7, 0, 0,
			0, 0, 0, 2, 0, 6, 0, 0, 1,
			2, 0, 0, 0, 0, 5, 0, 0, 0,
			0, 7, 0, 0, 0, 0, 0, 3, 0,
			0, 0, 0, 0, 0, 1, 0, 0, 0,
	};

	@Test
	void okay() {
		assertTrue(new DLXSudokuSolver().isValidSudoku(seventeen));
	}

	@Test
	void notOkay() {
		assertFalse(new DLXSudokuSolver().isValidSudoku(moreThanOneSolution));
	}

}
