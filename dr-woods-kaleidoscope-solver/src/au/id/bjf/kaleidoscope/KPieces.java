package au.id.bjf.kaleidoscope;


/**
 * Utility class that models all the different kinds of pieces in the game of
 * Kaleidoscope, including mirror images.
 */
public class KPieces {

	/*
	 * The IDs are used to encode the constraint in Dancing Links, that each
	 * puzzle piece is placed once, and ONLY once. Therefore the following MUST
	 * happen:
	 * 
	 * - first ID starts at zero - no gaps in the numbering - each ID assigned
	 * to a unique piece, it's flipped equivalent, and all its rotations
	 */

	private static final int ID_MONOMINO_1 = 0;
	private static final int ID_MONOMINO_2 = 1;
	private static final int ID_DOMINO = 2;
	private static final int ID_TETRAMINO_I = 3;
	private static final int ID_TETRAMINO_L_1 = 4;
	private static final int ID_TETRAMINO_L_2 = 5;
	private static final int ID_TETRAMINO_L_3 = 6;
	private static final int ID_TETRAMINO_L_4 = 7;
	private static final int ID_TETRAMINO_S_1 = 8;
	private static final int ID_TETRAMINO_S_2 = 9;
	private static final int ID_TETRAMINO_SQUARE = 10;
	private static final int ID_TETRAMINO_T_1 = 11;
	private static final int ID_TETRAMINO_T_2 = 12;
	private static final int ID_TROMINO_1 = 13;
	private static final int ID_TROMINO_2 = 14;
	private static final int ID_TROMINO_3 = 15;
	private static final int ID_TROMINO_4 = 16;
	private static final int ID_WAND = 17;

	public static final KPiece MONOMINO_1 = new KPiece("monomino-1", ID_MONOMINO_1, 
			".");
	public static final KPiece MONOMINO_1_FLIPPED = new KPiece("monomino-1-flip", ID_MONOMINO_1, 
			"R");

	public static final KPiece MONOMINO_2 = new KPiece("monomino-2", ID_MONOMINO_2, 
			".");
	public static final KPiece MONOMINO_2_FLIPPED = new KPiece("monomino-2-flip", ID_MONOMINO_2, 
			"B");

	public static final KPiece DOMINO = new KPiece("domino", ID_DOMINO, 
			".R");
	public static final KPiece DOMINO_FLIPPED = new KPiece("domino-flip", ID_DOMINO,
			".B");

	public static final KPiece TETRAMINO_I = new KPiece("i-tetramino", ID_TETRAMINO_I, 
			".R.R");
	public static final KPiece TETRAMINO_I_FLIPPED = new KPiece("i-tetramino-flip", ID_TETRAMINO_I, 
			".Y.B");

	public static final KPiece TETRAMINO_L_1 = new KPiece("l-tetramino-1", ID_TETRAMINO_L_1, 
			"R.", 
			" R", 
			" .");
	public static final KPiece TETRAMINO_L_1_FLIPPED = new KPiece("l-tetramino-1-flip", ID_TETRAMINO_L_1, 
			"Y.", 
			".", 
			"B");

	public static final KPiece TETRAMINO_L_2 = new KPiece("l-tetramino-2", ID_TETRAMINO_L_2, 
			".R", 
			" .", 
			" R ");
	public static final KPiece TETRAMINO_L_2_FLIPPED = new KPiece("l-tetramino-2-flip", ID_TETRAMINO_L_2, 
			"B.", 
			".", 
			"Y");

	public static final KPiece TETRAMINO_L_3 = new KPiece("l-tetramino-3", ID_TETRAMINO_L_3, 
			" .", 
			" R", 
			"R. ");
	public static final KPiece TETRAMINO_L_3_FLIPPED = new KPiece("l-tetramino-3-flip", ID_TETRAMINO_L_3, 
			".", 
			"B", 
			".Y");

	public static final KPiece TETRAMINO_L_4 = new KPiece("l-tetramino-4", ID_TETRAMINO_L_4, 
			" R", 
			" .", 
			".R ");
	public static final KPiece TETRAMINO_L_4_FLIPPED = new KPiece("l-tetramino-4-flip", ID_TETRAMINO_L_4, 
			".", 
			"Y", 
			".B");

	public static final KPiece TETRAMINO_S_1 = new KPiece("s-tetramino-1", ID_TETRAMINO_S_1, 
			" .R", 
			".R ");
	public static final KPiece TETRAMINO_S_1_FLIPPED = new KPiece("s-tetramino-1-flip", ID_TETRAMINO_S_1, 
			"Y. ", 
			" B.");

	public static final KPiece TETRAMINO_S_2 = new KPiece("s-tetramino-2", ID_TETRAMINO_S_2, 
			".R ", 
			" .R");
	public static final KPiece TETRAMINO_S_2_FLIPPED = new KPiece("s-tetramino-2-flip", ID_TETRAMINO_S_2, 
			" .B", 
			".Y ");

	public static final KPiece TETRAMINO_SQUARE = new KPiece("square-tetramino", ID_TETRAMINO_SQUARE, 
			".R", 
			"R.");
	public static final KPiece TETRAMINO_SQUARE_FLIPPED = new KPiece("square-tetramino-flip", ID_TETRAMINO_SQUARE, 
			".Y", 
			"B.");

	public static final KPiece TETRAMINO_T_1 = new KPiece("t-tetramino-1", ID_TETRAMINO_T_1, 
			".R.", 
			" . ");
	public static final KPiece TETRAMINO_T_1_FLIPPED = new KPiece("t-tetramino-1-flip", ID_TETRAMINO_T_1, 
			"Y.B", 
			" Y ");

	public static final KPiece TETRAMINO_T_2 = new KPiece("t-tetramino-2", ID_TETRAMINO_T_2, 
			"R.R", 
			" R ");
	public static final KPiece TETRAMINO_T_2_FLIPPED = new KPiece("t-tetramino-2-flip", ID_TETRAMINO_T_2, 
			".B.",
			" . ");

	public static final KPiece TROMINO_1 = new KPiece("tromino-1", ID_TROMINO_1, 
			"R.R");
	public static final KPiece TROMINO_1_FLIPPED = new KPiece("tromino-1-flip", ID_TROMINO_1, 
			"B.Y");

	public static final KPiece TROMINO_2 = new KPiece("tromino-2", ID_TROMINO_2, 
			".R.");
	public static final KPiece TROMINO_2_FLIPPED = new KPiece("tromino-2-flip", ID_TROMINO_2, 
			".Y.");

	public static final KPiece TROMINO_3 = new KPiece("tromino-3", ID_TROMINO_3, 
			"R.", 
			".");
	public static final KPiece TROMINO_3_FLIPPED = new KPiece("tromino-3-flip", ID_TROMINO_3, 
			"Y.", 
			".");

	public static final KPiece TROMINO_4 = new KPiece("tromino-4", ID_TROMINO_4, 
			".R", 
			"R");
	public static final KPiece TROMINO_4_FLIPPED = new KPiece("tromino-4-flip", ID_TROMINO_4, 
			".Y", 
			"B");

	public static final KPiece WAND = new KPiece("wand", ID_WAND, 
			".R.R.R.R");
	public static final KPiece WAND_FLIPPED = new KPiece("wand-flip", ID_WAND,
			"B.Y.B.Y.");

	private KPieces() {
		// singleton
	}
	
	public static final KPiece[] ALL_PIECES_FLIPPED = {
		MONOMINO_1, MONOMINO_1_FLIPPED,
		MONOMINO_2, MONOMINO_2_FLIPPED,
		DOMINO, DOMINO_FLIPPED,
		TETRAMINO_I, TETRAMINO_I_FLIPPED,
		TETRAMINO_L_1, TETRAMINO_L_1_FLIPPED,
		TETRAMINO_L_2, TETRAMINO_L_2_FLIPPED,
		TETRAMINO_L_3, TETRAMINO_L_3_FLIPPED,
		TETRAMINO_L_4, TETRAMINO_L_4_FLIPPED,
		TETRAMINO_S_1, TETRAMINO_S_1_FLIPPED,
		TETRAMINO_S_2, TETRAMINO_S_2_FLIPPED,
		TETRAMINO_SQUARE, TETRAMINO_SQUARE_FLIPPED,
		TETRAMINO_T_1, TETRAMINO_T_1_FLIPPED,
		TETRAMINO_T_2, TETRAMINO_T_2_FLIPPED,
		TROMINO_1, TROMINO_1_FLIPPED,
		TROMINO_2, TROMINO_2_FLIPPED,
		TROMINO_3, TROMINO_3_FLIPPED,
		TROMINO_4, TROMINO_4_FLIPPED,
		WAND, WAND_FLIPPED
	};
	
}
