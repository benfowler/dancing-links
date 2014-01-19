package au.id.bjf.kaleidoscope.dlx;

import org.junit.Assert;
import org.junit.Test;

import au.id.bjf.kaleidoscope.KColor;
import au.id.bjf.kaleidoscope.KPiece;
import au.id.bjf.kaleidoscope.KPieces;
import au.id.bjf.kaleidoscope.TestData;

/**
 * Test higher-level methods used to generate the DLX inputs for solving
 * Kaleidoscope puzzles.
 */
public class TestDLXKaleidoscopeSolver {

	@Test
	public void testPiecePlacementMonomino1() {
		testPiecePlacement(TestData.CHALLENGE_001_CHECKERBOARD, 
				KPieces.MONOMINO_1, 32);
	}

	@Test
	public void testPiecePlacementMonomino1Flipped() {
		testPiecePlacement(TestData.CHALLENGE_001_CHECKERBOARD, 
				KPieces.MONOMINO_1_FLIPPED, 32);
	}
	
	@Test
	public void testPiecePlacementMonomino1AllFlipsOrientations() {
		testPiecePlacementAllOrientations(
				KPieces.MONOMINO_1.getName(), 
				TestData.CHALLENGE_001_CHECKERBOARD, 
				64, 
				new KPiece[] { KPieces.MONOMINO_1, KPieces.MONOMINO_1_FLIPPED } );
	}

	@Test
	public void testPiecePlacementMonomino2() {
		testPiecePlacement(TestData.CHALLENGE_001_CHECKERBOARD, 
				KPieces.MONOMINO_2, 32);
	}

	@Test
	public void testPiecePlacementMonomino2Flipped() {
		testPiecePlacement(TestData.CHALLENGE_001_CHECKERBOARD, 
				KPieces.MONOMINO_2_FLIPPED, 0);
	}

	@Test
	public void testPiecePlacementMonomino2AllFlipsOrientations() {
		testPiecePlacementAllOrientations(
				KPieces.MONOMINO_2.getName(), 
				TestData.CHALLENGE_001_CHECKERBOARD, 
				32, 
				new KPiece[] { KPieces.MONOMINO_2, KPieces.MONOMINO_2_FLIPPED } );
	}

	@Test
	public void testPiecePlacementWand() {
		testPiecePlacement(TestData.CHALLENGE_001_CHECKERBOARD, 
				KPieces.WAND, 4);
	}

	@Test
	public void testPiecePlacementWandAllFlipsOrientations() {
		testPiecePlacementAllOrientations(
				KPieces.WAND.getName(), 
				TestData.CHALLENGE_001_CHECKERBOARD, 
				16, 
				new KPiece[] { KPieces.WAND, KPieces.WAND_FLIPPED } );
	}

	@Test
	public void testPiecePlacementSquareTetramino() {
		testPiecePlacement(TestData.CHALLENGE_001_CHECKERBOARD, 
				KPieces.TETRAMINO_SQUARE, 24);
	}	

	@Test
	public void testPiecePlacementSquareTetraminoFlipped() {
		testPiecePlacement(TestData.CHALLENGE_001_CHECKERBOARD, 
				KPieces.TETRAMINO_SQUARE_FLIPPED, 0);
	}	

	@Test
	public void testPiecePlacementSquareTetraminoAllFlipsOrientations() {
		testPiecePlacementAllOrientations(
				KPieces.TETRAMINO_SQUARE.getName(), 
				TestData.CHALLENGE_001_CHECKERBOARD, 
				49, 
				new KPiece[] { KPieces.TETRAMINO_SQUARE, 
					KPieces.TETRAMINO_SQUARE_FLIPPED } );
	}
	
	@Test
	public void testPiecePlacementSTetramino1() {
		testPiecePlacement(TestData.CHALLENGE_001_CHECKERBOARD, 
				KPieces.TETRAMINO_S_1, 21);
	}	

	@Test
	public void testPiecePlacementSTetramino1Flipped() {
		testPiecePlacement(TestData.CHALLENGE_001_CHECKERBOARD, 
				KPieces.TETRAMINO_S_1_FLIPPED, 0);
	}	

	@Test
	public void testPiecePlacementSTetramino1AllFlipsOrientations() {
		testPiecePlacementAllOrientations(
				KPieces.TETRAMINO_S_1.getName(), 
				TestData.CHALLENGE_004_FANCY_CHESSBOARD, 
				46, 
				new KPiece[] { KPieces.TETRAMINO_S_1, 
					KPieces.TETRAMINO_S_1_FLIPPED } );
	}
	
//	@Test
//	public void testCountPuzzleSolutions_Challenge001()
//	{
//		System.out.println("CHALLENGE_001_CHECKERBOARD");
//		System.out.println("--------------------------");
//		DLXKaleidoscopeSolver solver = new DLXKaleidoscopeSolver();
//		KPiece[] allPiecesAllWays 
//			= solver.generateAllPiecesAllFlipsAllOrientations(
//					KPieces.ALL_PIECES_FLIPPED);
//		byte[][] allConstraints = solver.generateAllConstraintRows(
//				TestData.CHALLENGE_001_CHECKERBOARD, allPiecesAllWays);
//		
//		Assert.assertTrue("Known to have solution; MUST have at least one 1 in each column", 
//				isSolutionPossible(allConstraints));
//		
//		//debugPrintPiecePlacement("Challenge 1 pieces", allConstraints);
//		
//		new DLXKaleidoscopeSolver().solve(TestData.CHALLENGE_001_CHECKERBOARD);
//	}
	
	@Test
	public void testCountPuzzleSolutions_Challenge004()
	{
		System.out.println("CHALLENGE_004_FANCY_CHESSBOARD");
		System.out.println("------------------------------");
		DLXKaleidoscopeSolver solver = new DLXKaleidoscopeSolver();
		KPiece[] allPiecesAllWays 
			= solver.generateAllPiecesAllFlipsAllOrientations(
					KPieces.ALL_PIECES_FLIPPED);
		byte[][] allConstraints = solver.generateAllConstraintRows(
				TestData.CHALLENGE_004_FANCY_CHESSBOARD, allPiecesAllWays);
		
		Assert.assertTrue("Known to have solution; MUST have at least one 1 in each column", 
				isSolutionPossible(allConstraints));
		
		//debugPrintPiecePlacement("Challenge 4 pieces", allConstraints);
		
		new DLXKaleidoscopeSolver().solve(TestData.CHALLENGE_004_FANCY_CHESSBOARD);
	}
	

	/**
	 * Scan a set of generated constraints to make sure there's at least one 1
	 * in each column.  If not, then a solution will be impossible.
	 * 
	 * @param allConstraints matrix to check
	 * @return <code>true</code> if there is at least one 1 in each column of
	 *     the supplied matrix
	 */
	private boolean isSolutionPossible(byte[][] allConstraints) {
		boolean result = true;
		boolean[] seenFlagInCol = new boolean[82];
		for (byte[] row : allConstraints) {
			Assert.assertEquals(82, row.length);
			for (int i=0; i< 82; ++i) {
				if (row[i] != 0 && seenFlagInCol[i] == false)
					seenFlagInCol[i] = true;
			}
		}
		for (int i=0; i < 82; ++i) {
			if (!seenFlagInCol[i]) {
				System.err.println("Missing 1 in column " + i);
				result = false;
			}
		}
		
		return result;
	}

	/**
	 * Helper method to count placement of a piece on the board.
	 * @param board
	 * @param piece
	 * @param expectedNumbeOfOccurrences
	 */
	private void testPiecePlacement(KColor[] board,
			KPiece piece, int expectedNumbeOfOccurrences) {
		
		final byte[][] generatedRows = new DLXKaleidoscopeSolver()
					.generateAllConstraintRows(board, new KPiece[] { piece });
		
		Assert.assertEquals(expectedNumbeOfOccurrences,  generatedRows.length);
		
		for (byte[] row : generatedRows) {
			Assert.assertEquals(row.length, 82);
			for (byte b : row) {
				Assert.assertTrue(b == 0 || b == 1);
			}
		}
		
		debugPrintPiecePlacement(piece.getName(), generatedRows);
	}
	
	/**
	 * Helper method to count placement of a piece on the board
	 * 
	 * @param description
	 * @param board
	 * @param expectedNumbeOfOccurrences
	 * @param pieces
	 */
	private void testPiecePlacementAllOrientations(String description, 
			KColor[] board, int expectedNumbeOfOccurrences, KPiece[] pieces) {
		
		DLXKaleidoscopeSolver solver = new DLXKaleidoscopeSolver();
		KPiece[] allFlips = solver.generateAllPiecesAllFlipsAllOrientations(pieces);
		final byte[][] generatedRows = solver.generateAllConstraintRows(board, allFlips);
		
		Assert.assertEquals(expectedNumbeOfOccurrences,  generatedRows.length);
		
		for (byte[] row : generatedRows) {
			Assert.assertEquals(row.length, 82);
			for (byte b : row) {
				Assert.assertTrue(b == 0 || b == 1);
			}
		}
		
		debugPrintPiecePlacement(description, generatedRows);
	}

	private void debugPrintPiecePlacement(String description, byte[][] generatedRows) {
		System.out.println(description + ": " + generatedRows.length 
				+ " possible placements");
		for (byte[] row : generatedRows) {
			for (int i=0;i<18;++i) {
				System.out.print(row[i]);
			}
			System.out.print('|');
			for (int i=0; i<64; ++i) {
				System.out.print(row[18+i]);
				if ((i+1)%8==0)
					System.out.print(' ');
			}
			System.out.println();
		}
	}
	
}
