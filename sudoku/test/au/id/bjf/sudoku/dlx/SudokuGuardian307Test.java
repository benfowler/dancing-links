package au.id.bjf.sudoku.dlx;

/**
 * Guardian puzzle 307.  Medium difficulty.
 */
public final class SudokuGuardian307Test extends AbstractSudokuTest {

	@Override
	protected byte[] getProblem() {
		final byte guardian_307[] = {
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

		return guardian_307;
	}

	@Override
	protected byte[] getSolution() {
		// Just print answer for time being
		return null;
	}

}
