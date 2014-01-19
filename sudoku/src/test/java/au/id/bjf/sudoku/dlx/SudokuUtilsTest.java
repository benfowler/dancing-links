package au.id.bjf.sudoku.dlx;

import junit.framework.TestCase;
import au.id.bjf.sudoku.SudokuCheckFlags;
import au.id.bjf.sudoku.SudokuException;
import au.id.bjf.sudoku.SudokuUtils;

public class SudokuUtilsTest extends TestCase {

	final byte INCOMPLETE_LEGAL[] = {
			0, 0, 0, 0, 4, 0, 0, 0, 0,
			9, 3, 0, 0, 0, 8, 0, 6, 0,
			5, 0, 1, 0, 7, 0, 0, 0, 0,
			0, 6, 0, 1, 0, 0, 0, 4, 9,
			8, 0, 4, 0, 0, 0, 3, 0, 5,
			7, 2, 0, 0, 0, 3, 0, 8, 0,
			0, 0, 0, 0, 9, 0, 7, 0, 2,
			0, 7, 0, 5, 0, 0, 0, 1, 6,
			0, 0, 0, 0, 2, 0, 0, 0, 0, };

	final byte COMPLETE_LEGAL[] = {
			6, 8, 2, 3, 4, 5, 1, 9, 7,
			9, 3, 7, 2, 1, 8, 5, 6, 4,
			5, 4, 1, 6, 7, 9, 8, 2, 3,
			3, 6, 5, 1, 8, 7, 2, 4, 9,
			8, 1, 4, 9, 6, 2, 3, 7, 5,
			7, 2, 9, 4, 5, 3, 6, 8, 1,
			4, 5, 6, 8, 9, 1, 7, 3, 2,
			2, 7, 8, 5, 3, 4, 9, 1, 6,
			1, 9, 3, 7, 2, 6, 4, 5, 8 };

	// row 7, column 7, region 8 broken
	final byte INCOMPLETE_NOT_LEGAL[] = {
			0, 0, 0, 0, 4, 0, 0, 0, 0,
			9, 3, 0, 0, 0, 8, 0, 6, 0,
			5, 0, 1, 0, 7, 0, 0, 0, 0,
			0, 6, 0, 1, 0, 0, 0, 4, 9,
			8, 0, 4, 0, 0, 0, 3, 0, 5,
			7, 2, 0, 0, 0, 3, 0, 8, 0,
			0, 0, 0, 0, 9, 0, 7, 0, 2,
			0, 7, 0, 5, 0, 0, 0, 6, 6,
			0, 0, 0, 0, 2, 0, 0, 0, 0, };

	// row 7, column 7, region 8 broken
	final byte COMPLETE_NOT_LEGAL[] = {
			6, 8, 2, 3, 4, 5, 1, 9, 7,
			9, 3, 7, 2, 1, 8, 5, 6, 4,
			5, 4, 1, 6, 7, 9, 8, 2, 3,
			3, 6, 5, 1, 8, 7, 2, 4, 9,
			8, 1, 4, 9, 6, 2, 3, 7, 5,
			7, 2, 9, 4, 5, 3, 6, 8, 1,
			4, 5, 6, 8, 9, 1, 7, 3, 2,
			2, 7, 8, 5, 3, 4, 9, 8, 6,
			1, 9, 3, 7, 2, 6, 4, 5, 8 };

	public void testIsPuzzleLegalGood() {
		assertTrue(SudokuUtils.isPuzzleLegal(INCOMPLETE_LEGAL));
	}

	public void testIsPuzzleLegalGood2() {
		assertTrue(SudokuUtils.isPuzzleLegal(COMPLETE_LEGAL));
	}

	public void testIsPuzzleLegalBad() {
		assertFalse(SudokuUtils.isPuzzleLegal(INCOMPLETE_NOT_LEGAL));
	}

	public void testIsPuzzleLegalBad2() {
		assertFalse(SudokuUtils.isPuzzleLegal(COMPLETE_NOT_LEGAL));
	}

	public void testIsPuzzleCompleteGoodButIncomplete() {
		assertFalse(SudokuUtils.isPuzzleComplete(INCOMPLETE_LEGAL));
	}

	public void testIsPuzzleCompleteGoodAndComplete() {
		assertTrue(SudokuUtils.isPuzzleComplete(COMPLETE_LEGAL));
	}

	public void testIsPuzzleCompleteBadAndIncomplete() {
		try {
			SudokuUtils.isPuzzleComplete(INCOMPLETE_NOT_LEGAL);
			fail("Expected exception");
		} catch (final SudokuException ex) {
			// Do nothing, this is expected
		}
	}

	public void testIsPuzzleCompleteBadAndComplete() {
		try {
			SudokuUtils.isPuzzleComplete(COMPLETE_NOT_LEGAL);
			fail("Expected exception");
		} catch (final SudokuException ex) {
			// Do nothing, this is expected
		}
	}

	public void testCheckRowGood() {
		assertEquals(SudokuCheckFlags.SOLVED,
				SudokuUtils.checkRow(COMPLETE_LEGAL, 7));
	}

	public void testCheckRowGoodIncomplete() {
		assertEquals(SudokuCheckFlags.UNSOLVED,
				SudokuUtils.checkRow(INCOMPLETE_LEGAL, 7));
	}

	public void testCheckRowBad() {
		assertEquals(SudokuCheckFlags.ERROR,
				SudokuUtils.checkRow(COMPLETE_NOT_LEGAL, 7));
	}

	public void testCheckRowBadIncomplete() {
		assertEquals(SudokuCheckFlags.ERROR,
				SudokuUtils.checkRow(INCOMPLETE_NOT_LEGAL, 7));
	}

	public void testCheckColumnGood() {
		assertEquals(SudokuCheckFlags.SOLVED,
				SudokuUtils.checkColumn(COMPLETE_LEGAL, 7));
	}

	public void testCheckColumnGoodIncomplete() {
		assertEquals(SudokuCheckFlags.UNSOLVED,
				SudokuUtils.checkColumn(INCOMPLETE_LEGAL, 7));
	}

	public void testCheckColumnBad() {
		assertEquals(SudokuCheckFlags.ERROR,
				SudokuUtils.checkColumn(COMPLETE_NOT_LEGAL, 7));
	}

	public void testCheckColumnBadIncomplete() {
		assertEquals(SudokuCheckFlags.ERROR,
				SudokuUtils.checkColumn(INCOMPLETE_NOT_LEGAL, 7));
	}

	public void testCheckRegionGood() {
		assertEquals(SudokuCheckFlags.SOLVED,
				SudokuUtils.checkRegion(COMPLETE_LEGAL, 8));
	}

	public void testCheckRegionGoodIncomplete() {
		assertEquals(SudokuCheckFlags.UNSOLVED,
				SudokuUtils.checkRegion(INCOMPLETE_LEGAL, 8));
	}

	public void testCheckRegionBad() {
		assertEquals(SudokuCheckFlags.ERROR,
				SudokuUtils.checkRegion(COMPLETE_NOT_LEGAL, 8));
	}

	public void testCheckRegionBadIncomplete() {
		assertEquals(SudokuCheckFlags.ERROR,
				SudokuUtils.checkRegion(INCOMPLETE_NOT_LEGAL, 8));
	}

	public void testCountGivensComplete() {
		// i.e. all of them
		assertEquals(81, SudokuUtils.countGivens(COMPLETE_LEGAL));
	}

	public void testCountGivensIncomplete() {
		assertEquals(28, SudokuUtils.countGivens(INCOMPLETE_LEGAL));
	}

}