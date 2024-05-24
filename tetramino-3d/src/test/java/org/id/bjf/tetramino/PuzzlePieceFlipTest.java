package org.id.bjf.tetramino;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import org.junit.jupiter.api.Test;

/**
 * Test puzzle piece flipping
 */
class PuzzlePieceFlipTest {

	@Test
	void flipping() {
		for (PuzzlePiece piece : PuzzlePieces.ALL_PIECES) {
			testFlippingForPiece(piece);
		}
	}

	private void testFlippingForPiece(PuzzlePiece piece) {
		PuzzlePiece flippedPiece = piece.clone();
		flippedPiece.flip();
		assertNotSame(flippedPiece, piece);
		flippedPiece.flip();
		assertEquals(flippedPiece, piece);
		flippedPiece.flip();
		assertNotSame(flippedPiece, piece);
		flippedPiece.flip();
		assertEquals(flippedPiece, piece);		
	}

}
