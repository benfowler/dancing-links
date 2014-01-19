package au.id.bjf.kaleidoscope;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import au.id.bjf.kaleidoscope.KPiece;
import au.id.bjf.kaleidoscope.KPieces;

public class TestKPiece {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testRotateTTetramino1Dims() {
		Assert.assertEquals(3, KPieces.TETRAMINO_T_1.getWidth());
		Assert.assertEquals(2, KPieces.TETRAMINO_T_1.getHeight());
		Assert.assertEquals(2, KPieces.TETRAMINO_T_1.rotatedNinetyDegrees().getWidth());
		Assert.assertEquals(3, KPieces.TETRAMINO_T_1.rotatedNinetyDegrees().getHeight());
	}

	@Test
	public void testRotateTTetramino1UniqueRotations() {
		Assert.assertEquals(4, numberOfUniqueRotations(KPieces.TETRAMINO_T_1));
		Assert.assertEquals(4, numberOfUniqueRotations(KPieces.TETRAMINO_T_1_FLIPPED)); 
		Assert.assertEquals(8, numberOfUniqueRotations(KPieces.TETRAMINO_T_1, 
				KPieces.TETRAMINO_T_1_FLIPPED));
	}

	@Test
	public void testRotateSTetramino1Dims() {
		Assert.assertEquals(3, KPieces.TETRAMINO_S_1.getWidth());
		Assert.assertEquals(2, KPieces.TETRAMINO_S_1.getHeight());
		Assert.assertEquals(2, KPieces.TETRAMINO_S_1.rotatedNinetyDegrees().getWidth());
		Assert.assertEquals(3, KPieces.TETRAMINO_S_1.rotatedNinetyDegrees().getHeight());
	}

	@Test
	public void testRotateSTetramino1UniqueRotations() {
		Assert.assertEquals(4, numberOfUniqueRotations(KPieces.TETRAMINO_S_1));
		Assert.assertEquals(4, numberOfUniqueRotations(KPieces.TETRAMINO_S_1_FLIPPED)); 
		Assert.assertEquals(8, numberOfUniqueRotations(KPieces.TETRAMINO_S_1, 
				KPieces.TETRAMINO_S_1_FLIPPED));
	}

	@Test
	public void testRotateMonomino1Dims() {
		Assert.assertEquals(1, KPieces.MONOMINO_1.getWidth());
		Assert.assertEquals(1, KPieces.MONOMINO_1.getHeight());
		Assert.assertEquals(1, KPieces.MONOMINO_1.rotatedNinetyDegrees().getWidth());
		Assert.assertEquals(1, KPieces.MONOMINO_1.rotatedNinetyDegrees().getHeight());
	}
	
	@Test
	public void testRotateMonomino1UniqueRotations() {
		Assert.assertEquals(1, numberOfUniqueRotations(KPieces.MONOMINO_1));
		Assert.assertEquals(1, numberOfUniqueRotations(KPieces.MONOMINO_1_FLIPPED));
	}
	
	@Test
	public void testRotateSquareTetraminoDims() {
		Assert.assertEquals(2, KPieces.TETRAMINO_SQUARE.getWidth());
		Assert.assertEquals(2, KPieces.TETRAMINO_SQUARE.getHeight());
		Assert.assertEquals(2, KPieces.TETRAMINO_SQUARE.rotatedNinetyDegrees().getWidth());
		Assert.assertEquals(2, KPieces.TETRAMINO_SQUARE.rotatedNinetyDegrees().getHeight());
	}

	@Test
	public void testRotateSquareTetraminoUniqueRotations() {
		Assert.assertEquals(2, numberOfUniqueRotations(KPieces.TETRAMINO_SQUARE));
		Assert.assertEquals(4, numberOfUniqueRotations(KPieces.TETRAMINO_SQUARE_FLIPPED));
	}
	
	@Test
	public void testRotateWandDims() {
		Assert.assertEquals(8, KPieces.WAND.getWidth());
		Assert.assertEquals(1, KPieces.WAND.getHeight());
		Assert.assertEquals(1, KPieces.WAND.rotatedNinetyDegrees().getWidth());
		Assert.assertEquals(8, KPieces.WAND.rotatedNinetyDegrees().getHeight());
	}
	
	@Test
	public void testRotateWandUniqueRotations() {
		Assert.assertEquals(4, numberOfUniqueRotations(KPieces.WAND));
		Assert.assertEquals(4, numberOfUniqueRotations(KPieces.WAND_FLIPPED));
	}
	
	@Test
	public void testHashCode() {
		KPiece rot = KPieces.TETRAMINO_T_1;
		int r0 = rot.hashCode();   // 0deg
		rot = rot.rotatedNinetyDegrees();
		int r90 = rot.hashCode();   // 90deg
		rot = rot.rotatedNinetyDegrees();
		Assert.assertTrue(!(r90 == r0));
		int r180 = rot.hashCode();   // 180deg
		rot = rot.rotatedNinetyDegrees();
		Assert.assertTrue(!(r180 == r90));
		int r270 = rot.hashCode();   // 270deg
		rot = rot.rotatedNinetyDegrees();
		Assert.assertTrue(!(r270 == r180));
		int r360 = rot.hashCode();   // 0deg
		rot = rot.rotatedNinetyDegrees();
		Assert.assertTrue(!(r360 == r270));
		Assert.assertTrue(r360 == r0);
		int r450 = rot.hashCode();   // 90deg
		rot = rot.rotatedNinetyDegrees();
		Assert.assertTrue(!(r450 == r360));
		int r540 = rot.hashCode();   // 180deg
		rot = rot.rotatedNinetyDegrees();
		Assert.assertTrue(!(r540 == r450));
		int r630 = rot.hashCode();   // 270deg
		rot = rot.rotatedNinetyDegrees();
		Assert.assertTrue(!(r630 == r540));
		int r720 = rot.hashCode();   // 0deg
		rot = rot.rotatedNinetyDegrees();
		Assert.assertTrue(!(r720 == r630));
		Assert.assertTrue(r720 == r360);
		Assert.assertTrue(r720 == r0);
	}
	
	@Test
	public void testEquals() {
		KPiece rot = KPieces.TETRAMINO_T_1;
		KPiece rot2 = rot.rotatedNinetyDegrees();
		Assert.assertNotSame(rot, rot2);
		KPiece rot3 = rot.rotatedNinetyDegrees();
		Assert.assertNotSame(rot2, rot3);
		KPiece rot4 = rot.rotatedNinetyDegrees();
		Assert.assertEquals(rot3, rot4);   // back to 0deg
		KPiece rot5 = rot.rotatedNinetyDegrees();
		Assert.assertNotSame(rot4, rot5);
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	private int numberOfUniqueRotations(KPiece... pieces)
	{
		Set<KPiece> allRotations = new HashSet<KPiece>();
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
