package au.id.bjf.sudoku.dlx;

/**
 * Escargot.  Supposed to be hard -- but apparently not.
 */
public final class SudokuEscargotTest extends AbstractSudokuTest {

	@Override
	protected byte[] getProblem() {
		final byte escargot [] = {
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

		return escargot;
	}

	@Override
	protected byte[] getSolution() {
		// Just print answer for time being
		return null;
	}

}
