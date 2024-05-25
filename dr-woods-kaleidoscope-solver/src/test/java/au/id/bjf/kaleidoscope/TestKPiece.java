package au.id.bjf.kaleidoscope;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import org.junit.jupiter.api.Test;

class TestKPiece {

	@Test
	void rotateTTetramino1Dims() {
		assertEquals(3, KPieces.TETRAMINO_T_1.getWidth());
		assertEquals(2, KPieces.TETRAMINO_T_1.getHeight());
		assertEquals(2, KPieces.TETRAMINO_T_1.rotatedNinetyDegrees().getWidth());
		assertEquals(3, KPieces.TETRAMINO_T_1.rotatedNinetyDegrees().getHeight());
	}

	@Test
	void rotateTTetramino1UniqueRotations() {
		assertEquals(4, numberOfUniqueRotations(KPieces.TETRAMINO_T_1));
		assertEquals(4, numberOfUniqueRotations(KPieces.TETRAMINO_T_1_FLIPPED)); 
		assertEquals(8, numberOfUniqueRotations(KPieces.TETRAMINO_T_1, 
				KPieces.TETRAMINO_T_1_FLIPPED));
	}

	@Test
	void rotateSTetramino1Dims() {
		assertEquals(3, KPieces.TETRAMINO_S_1.getWidth());
		assertEquals(2, KPieces.TETRAMINO_S_1.getHeight());
		assertEquals(2, KPieces.TETRAMINO_S_1.rotatedNinetyDegrees().getWidth());
		assertEquals(3, KPieces.TETRAMINO_S_1.rotatedNinetyDegrees().getHeight());
	}

	@Test
	void rotateSTetramino1UniqueRotations() {
		assertEquals(4, numberOfUniqueRotations(KPieces.TETRAMINO_S_1));
		assertEquals(4, numberOfUniqueRotations(KPieces.TETRAMINO_S_1_FLIPPED)); 
		assertEquals(8, numberOfUniqueRotations(KPieces.TETRAMINO_S_1, 
				KPieces.TETRAMINO_S_1_FLIPPED));
	}

	@Test
	void rotateMonomino1Dims() {
		assertEquals(1, KPieces.MONOMINO_1.getWidth());
		assertEquals(1, KPieces.MONOMINO_1.getHeight());
		assertEquals(1, KPieces.MONOMINO_1.rotatedNinetyDegrees().getWidth());
		assertEquals(1, KPieces.MONOMINO_1.rotatedNinetyDegrees().getHeight());
	}

	@Test
	void rotateMonomino1UniqueRotations() {
		assertEquals(1, numberOfUniqueRotations(KPieces.MONOMINO_1));
		assertEquals(1, numberOfUniqueRotations(KPieces.MONOMINO_1_FLIPPED));
	}

	@Test
	void rotateSquareTetraminoDims() {
		assertEquals(2, KPieces.TETRAMINO_SQUARE.getWidth());
		assertEquals(2, KPieces.TETRAMINO_SQUARE.getHeight());
		assertEquals(2, KPieces.TETRAMINO_SQUARE.rotatedNinetyDegrees().getWidth());
		assertEquals(2, KPieces.TETRAMINO_SQUARE.rotatedNinetyDegrees().getHeight());
	}

	@Test
	void rotateSquareTetraminoUniqueRotations() {
		assertEquals(2, numberOfUniqueRotations(KPieces.TETRAMINO_SQUARE));
		assertEquals(4, numberOfUniqueRotations(KPieces.TETRAMINO_SQUARE_FLIPPED));
	}

	@Test
	void rotateWandDims() {
		assertEquals(8, KPieces.WAND.getWidth());
		assertEquals(1, KPieces.WAND.getHeight());
		assertEquals(1, KPieces.WAND.rotatedNinetyDegrees().getWidth());
		assertEquals(8, KPieces.WAND.rotatedNinetyDegrees().getHeight());
	}

	@Test
	void rotateWandUniqueRotations() {
		assertEquals(4, numberOfUniqueRotations(KPieces.WAND));
		assertEquals(4, numberOfUniqueRotations(KPieces.WAND_FLIPPED));
	}

	@Test
	void testHashCode() {
		KPiece rot = KPieces.TETRAMINO_T_1;
		int r0 = rot.hashCode();   // 0deg
		rot = rot.rotatedNinetyDegrees();
		int r90 = rot.hashCode();   // 90deg
		rot = rot.rotatedNinetyDegrees();
		assertFalse((r90 == r0));
		int r180 = rot.hashCode();   // 180deg
		rot = rot.rotatedNinetyDegrees();
		assertFalse((r180 == r90));
		int r270 = rot.hashCode();   // 270deg
		rot = rot.rotatedNinetyDegrees();
		assertFalse((r270 == r180));
		int r360 = rot.hashCode();   // 0deg
		rot = rot.rotatedNinetyDegrees();
		assertFalse((r360 == r270));
		assertEquals(r360, r0);
		int r450 = rot.hashCode();   // 90deg
		rot = rot.rotatedNinetyDegrees();
		assertFalse((r450 == r360));
		int r540 = rot.hashCode();   // 180deg
		rot = rot.rotatedNinetyDegrees();
		assertFalse((r540 == r450));
		int r630 = rot.hashCode();   // 270deg
		rot = rot.rotatedNinetyDegrees();
		assertFalse((r630 == r540));
		int r720 = rot.hashCode();   // 0deg
		rot.rotatedNinetyDegrees();
		assertFalse((r720 == r630));
		assertEquals(r720, r360);
		assertEquals(r720, r0);
	}

	@Test
	void equals() {
		KPiece rot = KPieces.TETRAMINO_T_1;
		KPiece rot2 = rot.rotatedNinetyDegrees();
		assertNotSame(rot, rot2);
		KPiece rot3 = rot.rotatedNinetyDegrees();
		assertNotSame(rot2, rot3);
		KPiece rot4 = rot.rotatedNinetyDegrees();
		assertEquals(rot3, rot4);   // back to 0deg
		KPiece rot5 = rot.rotatedNinetyDegrees();
		assertNotSame(rot4, rot5);
	}

	private int numberOfUniqueRotations(KPiece... pieces)
	{
		Set<KPiece> allRotations = new HashSet<>();
		for (KPiece piece : pieces) {
			allRotations.add(piece);
			KPiece rotation = piece.rotatedNinetyDegrees();  // 90deg
			allRotations.add(rotation);
			rotation = rotation.rotatedNinetyDegrees();      // 180deg
			allRotations.add(rotation);
			rotation = rotation.rotatedNinetyDegrees();      // 270 deg
			allRotations.add(rotation);			
		}
		
		return allRotations.size();
	}

}
