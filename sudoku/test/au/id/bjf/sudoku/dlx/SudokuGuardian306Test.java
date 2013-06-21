package au.id.bjf.sudoku.dlx;

/**
 * Guardian puzzle 306.
 */
public final class SudokuGuardian306Test extends AbstractSudokuTest {

	@Override
	protected byte[] getProblem() {
		final byte guardian_306[] = {
				0, 0, 0, 0, 4, 0, 0, 0, 0,
				9, 3, 0, 0, 0, 8, 0, 6, 0,
				5, 0, 1, 0, 7, 0, 0, 0, 0,
				0, 6, 0, 1, 0, 0, 0, 4, 9,
				8, 0, 4, 0, 0, 0, 3, 0, 5,
				7, 2, 0, 0, 0, 3, 0, 8, 0,
				0, 0, 0, 0, 9, 0, 7, 0, 2,
				0, 7, 0, 5, 0, 0, 0, 1, 6,
				0, 0, 0, 0, 2, 0, 0, 0, 0, };
		return guardian_306;
	}

	@Override
	protected byte[] getSolution() {
		final byte guardian_306_complete[] = {
				6, 8, 2, 3, 4, 5, 1, 9, 7,
				9, 3, 7, 2, 1, 8, 5, 6, 4,
				5, 4, 1, 6, 7, 9, 8, 2, 3,
				3, 6, 5, 1, 8, 7, 2, 4, 9,
				8, 1, 4, 9, 6, 2, 3, 7, 5,
				7, 2, 9, 4, 5, 3, 6, 8, 1,
				4, 5, 6, 8, 9, 1, 7, 3, 2,
				2, 7, 8, 5, 3, 4, 9, 1, 6,
				1, 9, 3, 7, 2, 6, 4, 5, 8 };
		return guardian_306_complete;
	}

}
