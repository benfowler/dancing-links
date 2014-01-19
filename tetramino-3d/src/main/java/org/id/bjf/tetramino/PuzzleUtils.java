package org.id.bjf.tetramino;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Utility functions used for 3D tetramino solver.
 */
public class PuzzleUtils {
	
	public static Coordinate block(int x, int y, int z) {
		Coordinate result = new Coordinate();
		result.setAll(x, y, z);
		return result;
	}

	public static PuzzlePiece puzzlePiece(Coordinate[] blocks) {
		return new PuzzlePiece(Arrays.asList(blocks));
	}
	
	public static Set<PuzzlePiece> generateAllPlacementsOfAllOrientations(
			PuzzlePiece piece) {
		
		// Generate all orientations of the basic pieces
		Set<PuzzlePiece> allOrientations = new HashSet<PuzzlePiece>();
		allOrientations.add(piece);

		PuzzlePiece rotated = piece.clone();
		rotated.rotate();
		allOrientations.add(rotated);

		PuzzlePiece flipped = piece.clone();
		flipped.flip();
		allOrientations.add(flipped);

		PuzzlePiece flippedRotated = piece.clone();
		flippedRotated.flip();
		flippedRotated.rotate();
		allOrientations.add(flippedRotated);
		
		// Generate all placements of all orientations
		Set<PuzzlePiece> allPlacements = new HashSet<PuzzlePiece>();
		for (PuzzlePiece orientation : allOrientations) {
			allPlacements.addAll(generateAllPlacements(orientation));
		}
		
		return allPlacements;		
	}
		
	public static Set<PuzzlePiece> generateAllPlacements(
			PuzzlePiece orientation) {
		int[] dimensions = orientation.dimensions();
		int width = dimensions[0];
		int height = dimensions[1];
		int depth = dimensions[2];

		Set<PuzzlePiece> placements = new HashSet<PuzzlePiece>();
		for (int deltaX = 0; deltaX <= (3 - width); ++deltaX) {
			for (int deltaY = 0; deltaY <= (3 - height); ++deltaY) {
				for (int deltaZ = 0; deltaZ <= (3 - depth); ++deltaZ) {
					PuzzlePiece placement = orientation.clone();
					placement.translate(deltaX, deltaY, deltaZ);
					placements.add(placement);
				}
			}
		}
		return placements;
	}

	public static String dumpPuzzlePiece(PuzzlePiece piece) {
		StringBuffer buf = new StringBuffer(40);
		buf.append("Piece: ");
		buf.append(piece.toString());
		buf.append("\n");
		Set<PuzzlePiece> allWaysAround = new HashSet<PuzzlePiece>();
		buf.append("Before normalization: ");
		buf.append(piece.toString());
		buf.append("\n");
		
		
		// No rotation, normalized
		PuzzlePiece rot0 = piece.clone();
		PuzzlePiece.normalize(rot0);
		buf.append("Normalized: ");
		buf.append(piece.toString());
		buf.append("\n");
		
		allWaysAround.add(piece);
		
		// Rotated about Z axis
		PuzzlePiece rot1 = rot0.clone();
		rot1.rotate();
		buf.append("Rotated: ");
		buf.append(rot1.toString());
		buf.append("\n");
		
		allWaysAround.add(rot1);
		
		// Flipped
		PuzzlePiece rot2 = rot0.clone();
		rot2.flip();
		buf.append("Flipped: ");
		buf.append(rot2.toString());
		buf.append("\n");
		
		allWaysAround.add(rot2);
		
		// Flipped, rotated
		PuzzlePiece rot3 = rot0.clone();
		rot3.flip();
		rot3.rotate();
		buf.append("Flipped, rotated: ");
		buf.append(rot3.toString());
		buf.append("\n");
		
		allWaysAround.add(rot3);
		
		// Dump out everything in set of all possible rotations
		buf.append("Now, showing all pieces after putting in set:");
		buf.append("\n");
		for (PuzzlePiece aPiece : allWaysAround) {
			buf.append("   ");
			buf.append(aPiece.toString());
			buf.append("\n");
		}
		
		return buf.toString();
	}
	
}
