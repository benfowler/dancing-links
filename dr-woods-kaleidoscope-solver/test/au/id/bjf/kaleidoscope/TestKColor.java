package au.id.bjf.kaleidoscope;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import au.id.bjf.kaleidoscope.KColor;

public class TestKColor {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testValidMatrix() {
		KColor[] result = TestData.CHALLENGE_001_CHECKERBOARD;
		Assert.assertEquals(result.length, 64);
		for (int i = 0; i < 64; ++i) {
			Assert.assertNotNull(result[i]);
		}
	}

	@Test
	public void testValidMatrix2() {
		KColor[] result = TestData.CHALLENGE_004_FANCY_CHESSBOARD;
		Assert.assertEquals(result.length, 64);
		for (int i = 0; i < 64; ++i) {
			Assert.assertNotNull(result[i]);
		}
	}

	@Test
	public void testInvalidMatrix() {
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
			Assert.fail("Meant to fail on illegal shortcodes in input");
		} catch (IllegalArgumentException expected) { };
	}

	@Test
	public void testInvalidMatrix2() {
		try {
			KColor.findFromShortCode(
					"        ");
			Assert.fail("Meant to fail on illegal shortcodes in input");
		} catch (IllegalArgumentException expected) { };
	}

	@Test
	public void testEmptyMatrix() {
		KColor[] result = KColor.findFromShortCode();
		Assert.assertEquals(result.length, 0);
	}

	@Test
	public void testEmptyMatrix2() {
		KColor[] result = KColor.findFromShortCode("", "", "");
		Assert.assertEquals(result.length, 0);
	}

	@After
	public void tearDown() throws Exception {
	}

}
