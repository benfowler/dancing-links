package au.id.bjf.sudoku.dlx;

/**
 * The Seventeen problem.  Very hard.
 */
public final class SudokuSeventeenTest extends AbstractSudokuTest {

	@Override
	protected byte[] getProblem() {
		final byte seventeen[] = {
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
		return seventeen;
	}

	@Override
	protected byte[] getSolution() {
		// Just print answer for time being
		return null;
	}

}
