package org.id.bjf.tetramino;

import static org.id.bjf.tetramino.PuzzleUtils.block;
import static org.id.bjf.tetramino.PuzzleUtils.puzzlePiece;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test puzzle piece equality
 */
public class PuzzlePieceEqualityTest {

	@Test
	public void testEquals() {
		PuzzlePiece piece1  = puzzlePiece(new Coordinate[] {
				block(0, 0, 0),
				block(1, 0, 0),
				block(1, 0, 1),
				block(1, 1, 1),
			});
		
		PuzzlePiece piece2  = puzzlePiece(new Coordinate[] {
				block(0, 0, 0),
				block(1, 0, 0),
				block(1, 0, 1),
				block(1, 2, 1),
			});
		
		assertTrue(!piece1.equals(piece2));
		assertTrue(!piece2.equals(piece1));

		assertTrue(piece1.equals(piece1));
		assertTrue(piece2.equals(piece2));
	}
	
}
