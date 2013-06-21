package org.id.bjf.tetramino.dlx;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.id.bjf.tetramino.Coordinate;
import org.id.bjf.tetramino.PuzzlePiece;
import org.id.bjf.tetramino.PuzzlePieces;
import org.id.bjf.tetramino.PuzzleSolution;
import org.id.bjf.tetramino.PuzzleUtils;

import au.id.bjf.dlx.DLX;
import au.id.bjf.dlx.data.ColumnObject;

/**
 * Solves Mum's 3D tetramino problem, using Knuth's DLX algorithm.
 * <p>
 * Columns in matrix are mapped as follows:
 * <ul>
 * <li>Piece n placed</li>
 * <li>Position (x,y,z) occupied</li>
 * </ul>
 * <p>
 * Rows correspond to a piece placed in a certain position in a certain
 * orientation.
 */
public class DLXTetraminoSolver {

	public static void main(String[] args) {
		byte[][] input = generateInput();
		Object[] labels = generateLabels();
		ColumnObject sparseMatrix = DLX.buildSparseMatrix(input, labels);
		TetraminoDLXResultProcessor processor = new TetraminoDLXResultProcessor();
		DLX.solve(sparseMatrix, true, processor);
		dumpSolutions(processor.getSolutions());
	}

	private static Object[] generateLabels() {
		int numPieces = PuzzlePieces.ALL_PIECES.length;
		ColumnLabel[] labels = new ColumnLabel[numPieces + 27];
		for (int i=0; i < numPieces; ++i) {
			labels[i] = new PieceLabel(i);
		}
		for (int z=0; z<3; ++z) {
			for (int y=0; y<3; ++y) {
				for (int x=0; x<3; ++x) {
					int offset = columnOffset(numPieces, x, y, z);
					labels[offset] = new PositionLabel(x, y, z);
				}
			}
		}
		return labels;
	}

	private static int columnOffset(int numPieces, int x, int y, int z) {
		int offset = numPieces + (9*z) + (3*y) + x;
		return offset;
	}

	private static byte[][] generateInput() {
		List<byte[]> rows = new LinkedList<byte[]>();
		int numPieces = PuzzlePieces.ALL_PIECES.length;
		int pieceNum = 0;
		for (PuzzlePiece piece : PuzzlePieces.ALL_PIECES) {
			Set<PuzzlePiece> allPlacementsForPiece = 
				PuzzleUtils.generateAllPlacementsOfAllOrientations(piece);
			for (PuzzlePiece placedPiece : allPlacementsForPiece) {
				byte[] row = new byte[numPieces + 27];
				row[pieceNum] = 1;
				for (Coordinate coord : placedPiece.getCoordinates()) {
					row[columnOffset(numPieces, coord.getX(), coord.getY(), coord.getZ())] = 1;
				}
				rows.add(row);
			}
			pieceNum++;
		}
		byte[][] input = new byte[rows.size()][];
		for (int i=0; i < rows.size(); ++i) {
			input[i] = rows.get(i);
		}
		return input;
	}
	
	private static void dumpSolutions(Collection<PuzzleSolution> solutions) {
		int solutionNum = 1;
		System.out.println("Dumping out solutions.  Some may be mirror images of others.");
		for (PuzzleSolution solution : solutions) {
			System.out.println("Solution#" + solutionNum);
			System.out.println(solution.toString());
			System.out.println();
			solutionNum++;
		}
	}

}

abstract class ColumnLabel {
	
}

class PieceLabel extends ColumnLabel {
	int pieceNumber;
	public PieceLabel(int pieceNumber) { this.pieceNumber = pieceNumber; }
	public String toString() { return "Piece=" + pieceNumber; }
}

class PositionLabel extends ColumnLabel {
	int x, y, z;
	public PositionLabel(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	@Override
	public String toString() {
		return "[" + x + ", " + y + ", " + z + "]";
	}
}
