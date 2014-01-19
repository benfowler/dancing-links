package au.id.bjf.sudoku;

public class SudokuException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SudokuException() {
		// Do nothing
	}

	public SudokuException(String message) {
		super(message);
	}

}
