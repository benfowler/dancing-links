package au.id.bjf.sudoku.dlx;

/**
 * Guardian puzzle 309.  Rated 'hard'.
 */
public final class SudokuGuardian309Test extends AbstractSudokuTest {

	@Override
	protected byte[] getProblem() {
		final byte guardian_309[] = {
				3, 0, 0, 7, 0, 1, 0, 0, 0,
				0, 0, 6, 0, 9, 0, 0, 4, 0,
				0, 0, 0, 5, 0, 0, 7, 0, 8,
				0, 0, 0, 0, 0, 0, 0, 2, 0,
				7, 0, 0, 0, 3, 0, 0, 0, 6,
				0, 1, 0, 0, 0, 0, 0, 0, 0,
				5, 0, 3, 0, 0, 8, 0, 0, 0,
				0, 9, 0, 0, 1, 0, 4, 0, 0,
				0, 0, 0, 6, 0, 5, 0, 0, 9
		};

		return guardian_309;
	}

	@Override
	protected byte[] getSolution() {
		// Just print answer for time being
		return null;
	}

}
