package org.id.bjf.tetramino;

import static org.id.bjf.tetramino.PuzzlePieces.GRID_SIZE;

/**
 * Models a solution to the 3D tetramino problem.
 */
public class PuzzleSolution {

	PuzzlePiece[] pieces = new PuzzlePiece[PuzzlePieces.ALL_PIECES.length];
	int[][][] grid = new int[GRID_SIZE][GRID_SIZE][GRID_SIZE];
	
	public void addPieceToSolution(int pieceNum, PuzzlePiece piece) {
		
		if (pieces[pieceNum] != null) {
			throw new IllegalArgumentException("Piece already added");
		}
 		pieces[pieceNum] = piece;
 		
 		for (Coordinate coord : piece.getCoordinates()) {
 			grid[coord.getX()][coord.getY()][coord.getZ()] = pieceNum;
 		}
	}
	
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("Grid, printed by layer (z), z=0 (top) first, blocks oriented / /\n");
		for (int z=0; z<GRID_SIZE; ++z) {
			for (int y=GRID_SIZE-1; y>=0; --y) { // origin is bottom-left
				for (int x=0; x<GRID_SIZE; ++x) {
					buf.append(String.format("%2d ", grid[x][y][z]));
				}
				buf.append("\n");
			}
			buf.append("\n");
		}
		return buf.toString();
	}
	
}
