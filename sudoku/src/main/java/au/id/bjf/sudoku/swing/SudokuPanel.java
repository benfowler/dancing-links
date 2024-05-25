package au.id.bjf.sudoku.swing;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;

/**
 * Implements a Sudoku puzzle grid
 */
public class SudokuPanel extends JPanel {

	private byte[] puzzle;


	/**
	 * Build a new SudokuPanel control
	 */
	public SudokuPanel(byte[] puzzle) {
		this.puzzle = puzzle;
		setLayout(new GridLayout(9, 9));
		setBorder(new BevelBorder(BevelBorder.LOWERED));
		buildControls(puzzle);
		setVisible(true);
	}
	
	private void buildControls(byte[] puzzle) {
		Dimension cellSize = new Dimension(20, 18);
		for (int i = 0; i < 81; ++ i) {
			Component newCmpnt = new JLabel(Byte.toString(puzzle[i]));
			newCmpnt.setPreferredSize(cellSize);
			this.add(newCmpnt);
		}
		refreshControls(puzzle);
	}
	
	private void refreshControls(byte[] puzzle) {
		Component[] cellControls = getComponents();
		for (int i = 0; i < 81; ++i) {
			byte thisCell = puzzle[i];
			if (thisCell < 1 || thisCell > 9) {
				thisCell = 0;
			}
			if (cellControls[i] instanceof JLabel jLabel) {
				if (thisCell != 0) {
					jLabel.setText(Byte.toString(puzzle[i]));
				} else {
					jLabel.setText(" ");
				}
			} else if (cellControls[i] instanceof JTextField jTextField) {
				if (thisCell != 0) {
					jTextField.setText(Byte.toString(puzzle[i]));
				} else {
					jTextField.setText(" ");
				}
			}
		}
	}

	/**
	 * @param puzzle the puzzle to set
	 */
	public void setPuzzle(byte[] puzzle) {
		this.puzzle = puzzle;
		refreshControls(puzzle);
	}

	/**
	 * @return the puzzle
	 */
	public byte[] getPuzzle() {
		return puzzle;
	}
	
	
}
