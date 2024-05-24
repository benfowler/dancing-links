package org.id.bjf.tetramino;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import org.junit.jupiter.api.Test;

/**
 * Test puzzle piece rotation
 */
class PuzzlePieceRotateTest {

	@Test
	void rotatation() {
		for (PuzzlePiece piece : PuzzlePieces.ALL_PIECES) {
			testRotationForPiece(piece);
		}
	}

	private void testRotationForPiece(PuzzlePiece piece) {
		PuzzlePiece rotatedPiece = piece.clone();
		rotatedPiece.rotate();
		assertNotSame(rotatedPiece, piece);
		rotatedPiece.rotate();
		assertEquals(rotatedPiece, piece);
		rotatedPiece.rotate();
		assertNotSame(rotatedPiece, piece);
		rotatedPiece.rotate();
		assertEquals(rotatedPiece, piece);		
	}
	
}
