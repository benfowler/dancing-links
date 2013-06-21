package au.id.bjf.kaleidoscope.dlx;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import au.id.bjf.dlx.DLX;
import au.id.bjf.dlx.DLXResult;
import au.id.bjf.dlx.DLXResultProcessor;
import au.id.bjf.dlx.data.ColumnObject;
import au.id.bjf.kaleidoscope.KColor;
import au.id.bjf.kaleidoscope.KPiece;
import au.id.bjf.kaleidoscope.KPieces;
import au.id.bjf.kaleidoscope.KUnitSquare;
import au.id.bjf.kaleidoscope.KaleidoscopeSolver;

public class DLXKaleidoscopeSolver implements KaleidoscopeSolver {

	@Override
	public void solve(KColor[] puzzle) {
		KPiece[] generateAllPiecesAllFlipsAllOrientations 
			= generateAllPiecesAllFlipsAllOrientations(KPieces.ALL_PIECES_FLIPPED);
		System.out.println("All orientations of all pieces: " + 
				generateAllPiecesAllFlipsAllOrientations.length);
		
		final byte[][] generatedConstraints = generateAllConstraintRows(
				puzzle, generateAllPiecesAllFlipsAllOrientations);
		System.out.println("All pieces, all orientations, all legal positions on board: " 
				+ generatedConstraints.length);
		
		final ColumnObject generatedConstraintsSparseMatrix 
			= DLX.buildSparseMatrix(generatedConstraints, generateLabels());
		KDLXSolutionPrinterResultProcessor resultProcessor = new KDLXSolutionPrinterResultProcessor(puzzle);
		//CountingOnlyDLXResultProcessor resultProcessor = new CountingOnlyDLXResultProcessor();
		DLX.solve(generatedConstraintsSparseMatrix, true, resultProcessor);
		System.out.println("Total number of solutions: " + resultProcessor.getNumSolutions());
	}
	
	/**
	 * Generate DLX labels
	 * @return a set of labels marking constraints fulfilled in solutions
	 */
	private Object[] generateLabels() {
		List<KLabel> labels = new ArrayList<KLabel>(82);
		for (byte i=0; i < 18; ++i)
			labels.add(new PieceLabel(i));
		for (byte i=18; i < 82; ++i)
			labels.add(new UnitSquareLabel((byte)((i-18) % 8), (byte)((i-18) / 8)));
		return labels.toArray(new KLabel[] { });
	}

	/**
	 * Generates the constraint matrix for all orientations of all pieces.
	 * 
	 * @param the Kaleidoscope puzzle to solve
	 * @param board the playing board to fit the pieces onto
	 * @param allPieces list of all orientations of all pieces to be put on
	 *     the board 
	 * @return a generated constraint matrix
	 */
	byte[][] generateAllConstraintRows(KColor[] board, KPiece[] allPiecesAllWays) {
		final List<byte[]> matrixRows = new ArrayList<byte[]>();
		for (KPiece piece : allPiecesAllWays) {
			for (int y=0; y <= (8 - piece.getHeight()); ++y) {
				for (int x=0; x <= (8 - piece.getWidth()); ++x) {
					if (pieceColorsMatchOnBoard(board, piece, x, y))
						matrixRows.add(encodeMatrixRow(piece, x, y));
				}
			}
		}
		return matrixRows.toArray(new byte[][] { });
	}	

	/**
	 * Check to see if the given piece, positioned on the board at the given
	 * offset, matches the colors on the board.  The piece can only be played
	 * at the position if the piece unit-cell colours match the corresponding
	 * squares on the board.
	 * 
	 * @param board the puzzle to solve, as a sequence of cell colours.  
	 *     Squares are numbered from 9 to 63, top-left to bottom-right row-wise
	 * 
	 * @param piece the piece to play
	 * @param offsetX the X offset of the piece on the board
	 * @param offsetY the Y offset of the piece on the board
	 * @return whether or not the pieces can be played on the board at this
	 *     offset, i.e. the piece colors match the board colors
	 */
	private boolean pieceColorsMatchOnBoard(KColor[] board, KPiece piece,
			int offsetX, int offsetY) {
		for (KUnitSquare unitSquare : piece) {
			int cellOffset = 8 * (unitSquare.getY() + offsetY)  
				+ (unitSquare.getX() + offsetX);
			if (!(unitSquare.getColor()==board[cellOffset]))
				return false;
		}
		
		return true;
	}

	/**
	 * Generate the matrix representation of this piece placed at these 
	 * coordinates of the board.  It's assumed that the coordinates result in
	 * the piece fitting fully onto the board. <p>
	 * 
	 * Piece placement on the playing board is encoded as follows:
	 * 
	 * <ul>
	 * <li>Columns 0-63: unit square on playing board is occupied.  Numbering 
	 *     is top-down, left-right
	 * <li>Columns 64-82: piece <i>i</i> placed on board 
	 * </ul>
	 *  
	 * @param piece piece to be placed
	 * @param offsetX X offset on board
	 * @param offsetY Y offset on board
	 * @return a matrix row to be fed into the DLX algorithm; an array of
	 *    bytes (0 or 1).
	 */
	private byte[] encodeMatrixRow(KPiece piece, int offsetX, int offsetY)
	{
		byte[] encodedMatrixRow = new byte[82];   // 64 unit squares; 18 pieces
		
		// Piece ID.  All pieces must be placed once and only once.
		if (piece.getPieceId() < 0 || piece.getPieceId() > 17)
			throw new IllegalArgumentException("pieceId");
		
		encodedMatrixRow[piece.getPieceId()] = 1;

		// Unit squares.  All unit squares must be covered once and only once.
		for (KUnitSquare unitSquare : piece) {
			if (unitSquare.getX() < 0 || unitSquare.getX() > 7)
				throw new IllegalArgumentException("unitSquare.x");
			final int x = unitSquare.getX() + offsetX;
			if (x < 0 || x > 7)
				throw new IllegalArgumentException("x");

			if (unitSquare.getY() < 0 || unitSquare.getY() > 7)
				throw new IllegalArgumentException("unitSquare.y");
			final int y = unitSquare.getY() + offsetY;
			if (y < 0 || y > 7)
				throw new IllegalArgumentException("y");
			
			final int cellNum = 8 * y + x;
			encodedMatrixRow[18 + cellNum] = 1;
		}		

		return encodedMatrixRow;
	}
	
	/**
	 * Given all the pieces and their flipped versions, generate all rotations
	 * of these pieces, yielding a list of all possible orientations of all 
	 * pieces.
	 * 
	 * @param pieces pieces to add
	 * @return a list of all orientations of all pieces.
	 */
	public KPiece[] generateAllPiecesAllFlipsAllOrientations(KPiece[] pieces) {
		Set<KPiece> everything = new HashSet<KPiece>();
		
		// For shapes that appear the same when rotated, e.g. monominoes, 
		// we count on the fact that similar shapes hash the same.
		for (KPiece piece : pieces) {
			everything.add(piece);
			KPiece rotated = piece.rotatedNinetyDegrees();  // 90deg clockwise
			everything.add(rotated);
			rotated = rotated.rotatedNinetyDegrees(); // 180deg clockwise
			everything.add(rotated);
			rotated = rotated.rotatedNinetyDegrees(); // 270deg clockwise
			everything.add(rotated);
		}
		return everything.toArray(new KPiece[] {});
	}

	/**
	 * Processor that merely count solutions
	 */
	class CountingOnlyDLXResultProcessor implements DLXResultProcessor {
		private int numSolutions = 0;
		public boolean processResult(DLXResult result) {
			numSolutions++;
			return true;
		}
		public int getNumSolutions() {
			return numSolutions;
		}
	}
	
}

abstract class KLabel {
}

class UnitSquareLabel extends KLabel {
	byte x;
	byte y;
	public UnitSquareLabel(byte x, byte y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public String toString() {
		return "UnitSquare=(" + x + ", " + y + ")";
	}
}

class PieceLabel extends KLabel {
	byte i;
	public PieceLabel(byte i) {
		this.i = i;
	}
	@Override
	public String toString() {
		return "Piece=(" + i + ")";
	}
}
