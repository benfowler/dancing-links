package org.id.bjf.tetramino;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test puzzle piece flipping
 */
public class PuzzlePieceFlipTest {

	@Test
	public void testFlipping() {
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
