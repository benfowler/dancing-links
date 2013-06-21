package org.id.bjf.tetramino;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import static org.junit.Assert.*;

public class PuzzlePieceCountVariationsTest {

	@Test
	public void checkPuzzlePieceVariationCount() {
		checkPuzzlePiece(PuzzlePieces.PIECE0, 4);
		checkPuzzlePiece(PuzzlePieces.PIECE1, 2);
		checkPuzzlePiece(PuzzlePieces.PIECE2, 2);
		checkPuzzlePiece(PuzzlePieces.PIECE3, 2);
		checkPuzzlePiece(PuzzlePieces.PIECE4, 4);
		checkPuzzlePiece(PuzzlePieces.PIECE5, 4);
		checkPuzzlePiece(PuzzlePieces.PIECE6, 4);
	}

	private void checkPuzzlePiece(PuzzlePiece piece, int expectedCombos) {
		Set<PuzzlePiece> pieces = new HashSet<PuzzlePiece>();
		pieces.add(piece);
		
		PuzzlePiece rotated = piece.clone();
		rotated.rotate();
		pieces.add(rotated);

		PuzzlePiece flipped = piece.clone();
		flipped.flip();
		pieces.add(flipped);

		PuzzlePiece flippedRotated = piece.clone();
		flippedRotated.flip();
		flippedRotated.rotate();
		pieces.add(flippedRotated);
		
		assertEquals(expectedCombos, pieces.size());
	}
}
