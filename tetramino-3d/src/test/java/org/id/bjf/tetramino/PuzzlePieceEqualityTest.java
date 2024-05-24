package org.id.bjf.tetramino;

import static org.id.bjf.tetramino.PuzzleUtils.block;
import static org.id.bjf.tetramino.PuzzleUtils.puzzlePiece;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

/**
 * Test puzzle piece equality
 */
class PuzzlePieceEqualityTest {

	@Test
	void equals() {
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

		assertNotEquals(piece1, piece2);
		assertNotEquals(piece2, piece1);

		assertEquals(piece1, piece1);
		assertEquals(piece2, piece2);
	}
	
}
