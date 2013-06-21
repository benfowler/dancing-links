package au.id.bjf.kaleidoscope;

import au.id.bjf.kaleidoscope.KColor;

public class TestData {

	public static final KColor[] CHALLENGE_001_CHECKERBOARD = 
		KColor.findFromShortCode(
				"R.R.R.R.",
				".R.R.R.R",
				"R.R.R.R.",
				".R.R.R.R",
				"R.R.R.R.",
				".R.R.R.R",
				"R.R.R.R.",
				".R.R.R.R");
	
	public static final KColor[] CHALLENGE_004_FANCY_CHESSBOARD = 
		KColor.findFromShortCode(
				"B.B.B.B.",
				".Y.Y.Y.Y",
				"R.R.R.R.",
				".R.R.R.R",
				"R.R.R.R.",
				".R.R.R.R",
				"Y.Y.Y.Y.",
				".B.B.B.B");
	
}
