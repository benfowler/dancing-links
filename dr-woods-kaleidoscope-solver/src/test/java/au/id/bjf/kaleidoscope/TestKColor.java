package au.id.bjf.kaleidoscope;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class TestKColor {

	@Test
	void validMatrix() {
		KColor[] result = TestData.CHALLENGE_001_CHECKERBOARD;
		assertEquals(64, result.length);
		for (int i = 0; i < 64; ++i) {
			assertNotNull(result[i]);
		}
	}

	@Test
	void validMatrix2() {
		KColor[] result = TestData.CHALLENGE_004_FANCY_CHESSBOARD;
		assertEquals(64, result.length);
		for (int i = 0; i < 64; ++i) {
			assertNotNull(result[i]);
		}
	}

	@Test
	void invalidMatrix() {
		try {
			KColor.findFromShortCode(
					"B.B.B.B.", 
					".B.B.B.B", 
					"B.foo.B.",
					"B.bar.B.", 
					"B.B.B.B.", 
					".B.B.B.B", 
					"B.B.B.B.", 
					".B.B.B.B");
			fail("Meant to fail on illegal shortcodes in input");
		} catch (IllegalArgumentException expected) { }
	}

	@Test
	void invalidMatrix2() {
		try {
			KColor.findFromShortCode(
					"        ");
			fail("Meant to fail on illegal shortcodes in input");
		} catch (IllegalArgumentException expected) { }
	}

	@Test
	void emptyMatrix() {
		KColor[] result = KColor.findFromShortCode();
		assertEquals(0, result.length);
	}

	@Test
	void emptyMatrix2() {
		KColor[] result = KColor.findFromShortCode("", "", "");
		assertEquals(0, result.length);
	}

}
