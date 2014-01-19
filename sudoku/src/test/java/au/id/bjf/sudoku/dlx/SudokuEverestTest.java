package au.id.bjf.sudoku.dlx;

/**
 * 'Hard' Sudoku.
 * <p>
 * See http://www.telegraph.co.uk/science/science-news/9359579/Worlds-hardest-sudoku-can-you-crack-it.html
 */
public final class SudokuEverestTest extends AbstractSudokuTest {

	@Override
	protected byte[] getProblem() {
		final byte everest[] = {
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

		return everest;
	}

	@Override
	protected byte[] getSolution() {
		// Just print answer for time being
		return null;
	}

}
