package org.id.bjf.tetramino;

import static org.id.bjf.tetramino.PuzzleUtils.block;
import static org.id.bjf.tetramino.PuzzleUtils.puzzlePiece;

public class PuzzlePieces {

	public static final int GRID_SIZE = 3;
	
	public static PuzzlePiece PIECE0  = puzzlePiece(new Coordinate[] {
			block(0, 0, 0),
			block(0, 1, 0),
			block(1, 1, 0),
			block(0, 0, 1),
		});

	public static PuzzlePiece PIECE1  = puzzlePiece(new Coordinate[] {
			block(0, 0, 0),
			block(1, 0, 0),
			block(1, 0, 1),
			block(1, 1, 1),
		});

	public static PuzzlePiece PIECE2  = puzzlePiece(new Coordinate[] {
			block(0, 0, 0),
			block(0, 1, 0),
			block(1, 0, 0),
		});

	public static PuzzlePiece PIECE3  = puzzlePiece(new Coordinate[] {
			block(0, 0, 0),
			block(1, 0, 0),
			block(2, 0, 0),
			block(1, 0, 1),
		});

	public static PuzzlePiece PIECE4  = puzzlePiece(new Coordinate[] {
			block(0, 0, 0),
			block(1, 0, 0),
			block(2, 0, 0),
			block(0, 1, 0),
		});

	public static PuzzlePiece PIECE5  = puzzlePiece(new Coordinate[] {
			block(0, 0, 0),
			block(1, 0, 0),
			block(1, 0, 1),
			block(2, 0, 1),
		});

	public static PuzzlePiece PIECE6  = puzzlePiece(new Coordinate[] {
			block(0, 0, 0),
			block(1, 0, 0),
			block(0, 1, 0),
			block(0, 0, 1),
		});
	
	public static final PuzzlePiece[] ALL_PIECES = new PuzzlePiece[] {
		PIECE0, PIECE1, PIECE2, PIECE3, PIECE4, PIECE5, PIECE6
	};
	
}
