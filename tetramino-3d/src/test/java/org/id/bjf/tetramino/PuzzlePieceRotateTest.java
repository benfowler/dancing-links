package org.id.bjf.tetramino;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test puzzle piece rotation
 */
public class PuzzlePieceRotateTest {

	@Test
	public void testRotatation() {
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
