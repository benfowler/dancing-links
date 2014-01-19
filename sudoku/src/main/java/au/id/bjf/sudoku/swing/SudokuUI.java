package au.id.bjf.sudoku.swing;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import au.id.bjf.dlx.DLXResult;
import au.id.bjf.dlx.DLXResultProcessor;
import au.id.bjf.sudoku.dlx.DLXSudokuSolver;

/**
 * Swing UI onto Sudoku solver.  Use can either play Sudoku in the regular
 * manner, or use the program to solve problems.
 */
@SuppressWarnings("serial")
public class SudokuUI extends JFrame {

	final byte seventeen[] = {
			0, 0, 6, 9, 0, 0, 0, 7, 0,
			0, 0, 0, 0, 1, 0, 0, 0, 2,
			8, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 2, 0, 0, 0, 0, 0, 0, 4,
			0, 0, 0, 0, 0, 0, 0, 0, 1,
			0, 0, 5, 0, 0, 6, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 6, 0,
			0, 0, 0, 0, 0, 2, 0, 5, 0,
			0, 1, 0, 0, 4, 3, 0, 0, 0,
	};
	
	public SudokuUI() {	
		
		setLayout(new FlowLayout());
		final SudokuPanel puzzlePanel = new SudokuPanel(seventeen);
		Dimension puzzlePanelSize = new Dimension(300, 200);
		setSize(puzzlePanelSize);
		setMinimumSize(puzzlePanelSize);
		setMaximumSize(puzzlePanelSize);
		add(puzzlePanel, 0);
		
		JButton solveButton = new JButton("Solve");
		solveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				byte[] puzzle = puzzlePanel.getPuzzle();
				new DLXSudokuSolver().solve(puzzle, new DLXResultProcessor() {
					public boolean processResult(DLXResult result) {
						DLXSudokuSolver solver = new DLXSudokuSolver();
						puzzlePanel.setPuzzle(solver.decodeDLXResult(result));
						return false;
					}
				});
			}
		});
		add(solveButton, 1);
	}
	
	public static void main(String args[]) {
		SudokuUI ui = new SudokuUI();
		ui.setVisible(true);
	}
	
}
