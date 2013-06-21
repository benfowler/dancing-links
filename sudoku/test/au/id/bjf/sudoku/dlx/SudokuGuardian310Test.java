package au.id.bjf.sudoku.dlx;

/**
 * Guardian puzzle 310.  Rated 'hard'.
 */
public final class SudokuGuardian310Test extends AbstractSudokuTest {

	@Override
	protected byte[] getProblem() {
		final byte guardian_310[] = {
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

		return guardian_310;
	}

	@Override
	protected byte[] getSolution() {
		// Just print answer for time being
		return null;
	}

}
