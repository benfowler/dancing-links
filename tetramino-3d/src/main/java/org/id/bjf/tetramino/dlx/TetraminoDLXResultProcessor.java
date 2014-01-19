package org.id.bjf.tetramino.dlx;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.id.bjf.tetramino.Coordinate;
import org.id.bjf.tetramino.PuzzlePiece;
import org.id.bjf.tetramino.PuzzleSolution;

import au.id.bjf.dlx.DLXResult;
import au.id.bjf.dlx.DLXResultProcessor;

public class TetraminoDLXResultProcessor implements DLXResultProcessor {

	Collection<PuzzleSolution> solutions = new ArrayList<PuzzleSolution>();
	
	public boolean processResult(DLXResult result) {
		
		PuzzleSolution solution = new PuzzleSolution();
		
		// For each row, process a single block
		Iterator<List<Object>> allRows = result.rows();
		while (allRows.hasNext()) {
			int pieceNumber = -1; // sentinel
			List<Object> row = (List<Object>)allRows.next();
			
			// Scan for piece number first
			for (Object label : row) {
				if (label instanceof PieceLabel) {
					PieceLabel pieceLabel = (PieceLabel)label;
					pieceNumber = pieceLabel.pieceNumber;
					break;
				}
			}
			assert(pieceNumber != -1);
			
			// Reconstruct block
			List<Coordinate> pieceCoordinates = new ArrayList<Coordinate>();
			for (Object label : row) {
				if (label instanceof PositionLabel) {
					PositionLabel positionLabel = (PositionLabel)label;
					Coordinate coord = new Coordinate();
					coord.setAll(positionLabel.x, positionLabel.y, positionLabel.z);
					pieceCoordinates.add(coord);
				}
			}
			assert(pieceCoordinates.size() > 0);

			// Build solution, remember it
			PuzzlePiece piece = new PuzzlePiece(pieceCoordinates);
			solution.addPieceToSolution(pieceNumber, piece);	
		}
		System.out.println();
		
		solutions.add(solution);
		
		return true;
	}
	
	public Collection<PuzzleSolution> getSolutions() {
		return Collections.unmodifiableCollection(solutions);
	}

}
