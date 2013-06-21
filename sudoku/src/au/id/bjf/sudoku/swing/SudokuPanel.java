package au.id.bjf.sudoku.swing;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

/**
 * Implements a Sudoku puzzle grid
 */
@SuppressWarnings("serial")
public class SudokuPanel extends JPanel {

	private byte[] puzzle = null;

	private LayoutManager layoutManager = new GridLayout(9, 9);
	
	
	/**
	 * Build a new SudokuPanel control
	 */
	public SudokuPanel(byte[] puzzle) {
		this.puzzle = puzzle;
		setLayout(layoutManager);
		setBorder(new BevelBorder(1));
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
			if (cellControls[i] instanceof JLabel) {
				if (thisCell != 0) {
					((JLabel)cellControls[i]).setText(Byte.toString(puzzle[i]));
				} else {
					((JLabel)cellControls[i]).setText(" ");
				}
			} else if (cellControls[i] instanceof JTextField) {
				if (thisCell != 0) {
					((JTextField)cellControls[i]).setText(Byte.toString(puzzle[i]));
				} else {
					((JTextField)cellControls[i]).setText(" ");
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
