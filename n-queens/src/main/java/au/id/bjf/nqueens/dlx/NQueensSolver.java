package au.id.bjf.nqueens.dlx;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;

import au.id.bjf.dlx.DLX;
import au.id.bjf.dlx.DLXResult;
import au.id.bjf.dlx.DLXResultProcessor;
import au.id.bjf.dlx.data.ColumnObject;

/**
 * Solve the N-Queens problem using the DLX algorithm.
 */
public class NQueensSolver {

	private final int n;  // sentinel
	
	
	/**
	 * Initialize a solver for an <em>n</em> x <em>n</em>
	 * @param n the dimension of the chessboard to solve for
	 */
	public NQueensSolver(int n) {
		if (n < 4) {
			throw new IllegalArgumentException("n");
		}
		this.n = n;
	}

	/**
	 * Run the solver, sending the results to the supplied <code>ResultProcessor</code>
	 * @param resultProcessor
	 */
	public void solve(DLXResultProcessor resultProcessor) {
		ColumnObject h = doSetup();
		DLX.solve(h, true, resultProcessor);
	}

	/**
	 * Helper method to convert a DLXResult into something usable by the 
	 * N-Queens code
	 * @param result the {@link DLXResult} of the result
	 * @param n dimension of chessboard
	 * @return the result, converted into a byte array of length <em>n</em> x 
	 *     <em>n</em>, containing ones and zeroes; ones representing occupied
	 *     squares
	 */
	public static byte[] mapResultIntoChessboard(DLXResult result, int n) {
		if (n < 3) {
			throw new IllegalArgumentException("n");
		}
		byte[] chessboard = new byte[n * n];
		for (Iterator<List<Object>> i=result.rows(); i.hasNext(); ) {
			List<Object> piece = i.next();
			int rank = -1, file = -1; // sentinels
			for (Object label : piece) {
				if (label instanceof RankLabel) {
					rank = ((RankLabel)label).i;
				} else if (label instanceof FileLabel) {
					file = ((FileLabel)label).i;
				}
			}
			if ((rank >= 0) && (rank < n) && (file >=0) && (file < n))
				chessboard[rank * n + file] = 1;
		}
		return chessboard;
	}
	
	/**
	 * Prints a chessboard
	 * @param chessboard the board to print
	 * @param n the dimension of the chessboard
	 */
	public static void printChessboard(byte[] chessboard, int n) {
		printChessboard(chessboard, n, System.out);
	}
	
	/**
	 * Prints a chessboard
	 * @param chessboard the board to print
	 * @param n the dimension of the chessboard
	 * @param out where to send the output
	 */
	public static void printChessboard(byte[] chessboard, int n, PrintStream out) {
		if (n < 3) {
			throw new IllegalArgumentException("n");
		}
		for (int i = 0; i < n; ++i)
			out.print("+-");
		out.println("+");
		for (int rank = 0; rank < n; ++rank) {
			for (int file = 0; file < n; ++file) {
				if (chessboard[rank * n + file] != 0)
					out.print("|*");
				else
					out.print("| ");
			}
			out.println("|");
			for (int i = 0; i < n; ++i)
				out.print("+-");
			out.println("+");
		}
	}
	
	/** 
	 * Prints a chessboard in a compact form
	 * @param chessboard
	 * @param n the dimension of the chessboard
	 */
	public static void printChessboardCompact(byte[] chessboard, int n) {
		if (n > 26) {
			throw new IllegalArgumentException("Cannot display chessboard with " +
					"more than 26 files");
		}
		for (int i = 0; i < (n*n); ++i) {
			if (chessboard[i] != 0) {
				int rank = i / n;
				int file = i % n;
				System.out.print(Character.toChars('a' + file));
				System.out.print(rank + 1);
				System.out.print(' ');
			}
		}
		System.out.println();
	}

	private ColumnObject doSetup() {
		final int NUM_DIAGS = (2 * n - 1);
		final int RANK_OFFSET = 0;
		final int FILE_OFFSET = n;
		final int DIAG_OFFSET = n + n;
		final int RV_DIAG_OFFSET = n + n + NUM_DIAGS;
		final int NUM_MANDATORY_COLS = n + n;
		final int NUM_OPTIONAL_COLS = NUM_DIAGS + NUM_DIAGS;
		final int NUM_DLX_COLS = NUM_MANDATORY_COLS + NUM_OPTIONAL_COLS;
		final int NUM_CELLS = n * n;
		
		byte[][] chessboard = new byte[NUM_CELLS][NUM_DLX_COLS];
		
		for (int cell = 0; cell < NUM_CELLS; ++cell) {
			final int rank = cell / n;
			final int file = cell % n;
			chessboard[cell][RANK_OFFSET + rank] = 1;
			chessboard[cell][FILE_OFFSET + file] = 1;
			chessboard[cell][DIAG_OFFSET + (rank + file)] = 1;
			chessboard[cell][RV_DIAG_OFFSET + (n - 1 - rank + file)] = 1;
		}
		
		//debugDumpChessboard(NUM_DLX_COLS, NUM_CELLS, chessboard);
		
		// Labels for rows and columns only
		Object[] labels = new Object[NUM_DLX_COLS];
		for (byte i = 0; i < n; ++i) {
			labels[RANK_OFFSET + i] = new RankLabel(i);
			labels[FILE_OFFSET + i] = new FileLabel(i);
		}
		
		ColumnObject result = DLX.buildSparseMatrix(chessboard, labels);
		DLX.setColumnsAsOptional(result, NUM_MANDATORY_COLS, NUM_OPTIONAL_COLS);
		return result;
	}

	@SuppressWarnings("unused")
	private void debugDumpChessboard(final int NUM_DLX_COLS,
			final int NUM_CELLS, byte[][] chessboard) {
		for (int row=0; row < NUM_CELLS; ++ row) {
			for (int col=0; col < NUM_DLX_COLS; ++col) {
				System.out.print(chessboard[row][col] + " ");
			}
			System.out.println();
		}
	}

}


abstract class NQueensLabel {
}

class RankLabel extends NQueensLabel {
	final byte i;
	public RankLabel(byte i) {
		this.i = i;
	}
	@Override
	public String toString() {
		return "R=(" + i + ")";
	}
}

class FileLabel extends NQueensLabel {
	final byte i;
	public FileLabel(byte i) {
		this.i = i;
	}
	@Override
	public String toString() {
		return "F=(" + i + ")";
	}
}

