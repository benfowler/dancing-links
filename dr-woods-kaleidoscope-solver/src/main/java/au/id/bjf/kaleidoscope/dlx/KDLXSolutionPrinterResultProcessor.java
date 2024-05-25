package au.id.bjf.kaleidoscope.dlx;

import java.util.Iterator;
import java.util.List;

import au.id.bjf.dlx.DLXResult;
import au.id.bjf.dlx.DLXResultProcessor;
import au.id.bjf.kaleidoscope.KColor;

public class KDLXSolutionPrinterResultProcessor implements DLXResultProcessor {

	private final KColor[] problem;   // unit squares 0..80 row by row

	private int count = 0;


	public KDLXSolutionPrinterResultProcessor(KColor[] problem) {
		problem.getClass();   // npe if null
		this.problem = problem;
	}

	@Override
	public boolean processResult(DLXResult result) {

		char[] solution = new char[64];   // list of cells by row from top left
		char[] pieces = "0123456789abcdefgh".toCharArray();

		System.out.println("Solution#: " + count);
		for (Iterator<List<Object>> iterator = result.rows(); iterator.hasNext(); ) {
			List<Object> labels = iterator.next();
			int pieceNum = -1;   // sentinel should generate crash on logic error

			// scan for piece number
			for (Object label : labels) {
				if (label instanceof PieceLabel)
					pieceNum = ((PieceLabel) label).i;
			}

			// scan for unit-square coordinates and write piece numbers into
			// solution
			for (Object label : labels) {
				if (label instanceof UnitSquareLabel) {
					final int x = ((UnitSquareLabel) label).x;
					final int y = ((UnitSquareLabel) label).y;
					solution[y * 8 + x] = pieces[pieceNum];
				}
			}
		}

		drawSolutionGrid(problem, solution);
		count++;
		return true;
	}

	/**
	 * Render a solution using fancy ASCII art
	 *
	 * @param problem  the problem (including color placement)
	 * @param solution the solution (including placement of pieces by number)
	 */
	private void drawSolutionGrid(KColor[] problem, char[] solution) {
		char[][] buffer = new char[17][33];

		// Initialize with blanks
		for (char[] row : buffer)
			for (int i = 1; i < row.length; ++i)
				row[i] = ' ';

		drawBoardEdges(buffer);
		drawColorLabels(problem, buffer);
		drawInteriorEdges(solution, buffer);
		drawJunctionsInsideBoardEdges(solution, buffer);
		drawJunctionsAlongBoardEdges(solution, buffer);

		// Dump results to console	
		for (char[] row : buffer)
			System.out.println(row);
	}

	/**
	 * Render the outside edge of the board
	 */
	private void drawBoardEdges(char[][] buffer) {
		// Draw outside bounding box
		for (int i = 1; i <= 32; ++i) {
			buffer[0][i] = '-';
			buffer[16][i] = '-';
		}
		for (int i = 1; i <= 16; ++i) {
			buffer[i][0] = '|';
			buffer[i][32] = '|';
		}
		buffer[0][0] = '+';
		buffer[16][0] = '+';
		buffer[0][32] = '+';
		buffer[16][32] = '+';
	}

	/**
	 * Add labels for unit-cell colors
	 *
	 * @param problem the problem (including color placement)
	 * @param buffer  buffer into which to draw the grid
	 */
	private void drawColorLabels(KColor[] problem, char[][] buffer) {
		// Draw colors
		for (int i = 0; i < 64; ++i) {
			int x = 4 * (i % 8) + 2;
			int y = 2 * (i / 8) + 1;
			buffer[y][x] = problem[i].getShortCode();
		}
	}

	/**
	 * Render interior piece edges on the playing board
	 *
	 * @param solution the solution (including placement of pieces by number)
	 * @param buffer   buffer into which to draw the grid
	 */
	private void drawInteriorEdges(char[] solution, char[][] buffer) {

		// Right side of unit cell
		for (int y = 0; y < 8; ++y) {
			for (int x = 0; x < 7; ++x) {
				int offset = y * 8 + x;

				if (solution[offset] != solution[offset + 1]) {
					int destY = 2 * y + 1;
					int destX = 4 * x + 4;
					buffer[destY][destX] = '|';
				}
			}
		}

		// Bottom side of unit cell
		for (int y = 0; y < 7; ++y) {
			for (int x = 0; x < 8; ++x) {
				int offset = y * 8 + x;

				if (solution[offset] != solution[offset + 8]) {
					int destY = 2 * y + 2;
					int destX = 4 * x + 2;
					buffer[destY][destX - 1] = '-';
					buffer[destY][destX] = '-';
					buffer[destY][destX + 1] = '-';
				}
			}
		}
	}

	/**
	 * Draw a junction symbol where pieces intersect in the interior of the
	 * board
	 *
	 * @param solution the solution (including placement of pieces by number)
	 * @param buffer   buffer into which to draw the grid
	 */
	private void drawJunctionsInsideBoardEdges(char[] solution, char[][] buffer) {
		for (int y = 0; y < 7; y++) {
			for (int x = 0; x < 7; ++x) {
				int offsetA = y * 8 + x;
				int offsetB = offsetA + 1;
				int offsetC = (y + 1) * 8 + x;
				int offsetD = offsetC + 1;
				int destY = 2 * y + 2;
				int destX = 4 * x + 4;

				if (solution[offsetA] == solution[offsetB]
						&& solution[offsetB] == solution[offsetC]
						&& solution[offsetC] == solution[offsetD]) {
					// do nothing
				} else if (solution[offsetA] == solution[offsetB]
						&& solution[offsetC] == solution[offsetD]) {
					buffer[destY][destX] = '-';
				} else if (solution[offsetA] == solution[offsetC]
						&& solution[offsetB] == solution[offsetD]) {
					buffer[destY][destX] = '|';
				} else {
					buffer[destY][destX] = '+';
				}
			}
		}
	}

	/**
	 * Draw junction nodes around board edges where pieces intersect on the
	 * edge of the board
	 *
	 * @param solution the solution (including placement of pieces by number)
	 * @param buffer   buffer into which to draw the grid
	 */
	private void drawJunctionsAlongBoardEdges(char[] solution, char[][] buffer) {

		// Internal nodes along top and bottom edges
		for (int x = 0; x < 7; ++x) {

			// Top...
			int topOffsetA = x;
			int topOffsetB = topOffsetA + 1;
			int destX = 4 * x + 4;

			if (solution[topOffsetA] != solution[topOffsetB]) {
				buffer[0][destX] = '+';
			}

			// ... and bottom
			int bottomOffsetA = 56 + x;
			int bottomOffsetB = bottomOffsetA + 1;

			if (solution[bottomOffsetA] != solution[bottomOffsetB]) {
				buffer[16][destX] = '+';
			}
		}

		// Internal nodes along left and right edges
		for (int y = 0; y < 7; ++y) {

			// Left...
			int leftOffsetA = y * 8;
			int leftOffsetB = leftOffsetA + 8;
			int destY = 2 * y + 2;

			if (solution[leftOffsetA] != solution[leftOffsetB]) {
				buffer[destY][0] = '+';
			}

			// ... and right
			int rightOffsetA = y * 8 + 7;
			int rightOffsetB = rightOffsetA + 8;

			if (solution[rightOffsetA] != solution[rightOffsetB]) {
				buffer[destY][32] = '+';
			}
		}
	}

	public int getNumSolutions() {
		return count;
	}

}
