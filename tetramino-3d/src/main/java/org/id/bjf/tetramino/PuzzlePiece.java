package org.id.bjf.tetramino;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class PuzzlePiece implements Cloneable {

	Set<Coordinate> coordinates = new HashSet<>();

	public PuzzlePiece(Iterable<Coordinate> coordinates) {
		
		for (Coordinate coordinate : coordinates) {
			this.coordinates.add(coordinate);
		}

	}
	
	public static void normalize(PuzzlePiece piece)  {
		
		if (piece.coordinates.isEmpty())
			throw new IllegalArgumentException("Invalid piece");
		
		int minX = Integer.MAX_VALUE;
		int minY = Integer.MAX_VALUE;
		int minZ = Integer.MAX_VALUE;
		
		for (Coordinate coordinate : piece.coordinates) {
			if (coordinate.getX() < minX) 
				minX = coordinate.getX();
			if (coordinate.getY() < minY) 
				minY = coordinate.getY();
			if (coordinate.getZ() < minZ) 
				minZ = coordinate.getZ();
		}
		
		for (Coordinate coordinate : piece.coordinates) {
			coordinate.setX(coordinate.getX() - minX);
			coordinate.setY(coordinate.getY() - minY);
			coordinate.setZ(coordinate.getZ() - minZ);
		}		
	}

	/**
	 * Flip piece over
	 */
	public void flip() {
		normalize(this);
		Set<Coordinate> modifiedCoords = new HashSet<>();
		int[] dimensions = dimensions();
		int width = dimensions[0];
		int height = dimensions[1];
		int depth = dimensions[2];
		// Flip over (180 degrees around X axis)
		for (Coordinate coordinate : coordinates) {
			coordinate.setY(height - 1 -coordinate.getY());
			coordinate.setZ(depth - 1 - coordinate.getZ());
		}
		// Rotate 90 degrees 
		for (Coordinate coordinate : coordinates) {
			int newX = coordinate.getY();
			int newY = width - 1 - coordinate.getX();
			coordinate.setX(newX);
			coordinate.setY(newY);
			modifiedCoords.add(coordinate);
		}
		coordinates = modifiedCoords;
	}
	
	/**
	 * Rotate piece, i.e. rotate 180 degrees around Z axis
	 */
	public void rotate() {
		normalize(this);
		Set<Coordinate> modifiedCoords = new HashSet<>();
		int[] dimensions = dimensions();
		int width = dimensions[0];
		int height = dimensions[1];
		// Rotate (180 degrees around Z axis)
		for (Coordinate coordinate : coordinates) {
			coordinate.setX(width - 1 - coordinate.getX());
			coordinate.setY(height - 1 - coordinate.getY());
			modifiedCoords.add(coordinate);
		}
		coordinates = modifiedCoords;
	}
	
	public void translate(int deltaX, int deltaY, int deltaZ) {
		Set<Coordinate> modifiedCoords = new HashSet<>();
		for (Coordinate coord : coordinates) {
			coord.setAll(coord.getX() + deltaX, coord.getY() + deltaY, 
					coord.getZ() + deltaZ);
			modifiedCoords.add(coord);
		}
		coordinates = modifiedCoords;
	}

	public Collection<Coordinate> getCoordinates() {
		return Collections.unmodifiableCollection(coordinates);
	}
	
	@Override
	protected PuzzlePiece clone() {
		Set<Coordinate> cloneCoordinates = new HashSet<>();
		for (Coordinate coord : coordinates) {
			cloneCoordinates.add(coord.clone());
		}
		return new PuzzlePiece(cloneCoordinates);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (!obj.getClass().equals(getClass()))
			return false;
		
		PuzzlePiece otherPiece = (PuzzlePiece)obj;
		return (otherPiece.coordinates.equals(this.coordinates));
	}
	
	@Override
	public int hashCode() {
		int hash = 17;
		for (Coordinate coord : coordinates) {
			hash += coord.hashCode();
		}
		return hash;
	}
	
	public int[] dimensions() {
		
		int minX = Integer.MAX_VALUE;
		int minY = Integer.MAX_VALUE;
		int minZ = Integer.MAX_VALUE;
		
		int maxX = Integer.MIN_VALUE;
		int maxY = Integer.MIN_VALUE;
		int maxZ = Integer.MIN_VALUE;
		
		for (Coordinate coordinate : coordinates) {
			if (coordinate.getX() < minX) 
				minX = coordinate.getX();
			if (coordinate.getY() < minY) 
				minY = coordinate.getY();
			if (coordinate.getZ() < minZ) 
				minZ = coordinate.getZ();
			
			if (coordinate.getX() > maxX) 
				maxX = coordinate.getX();
			if (coordinate.getY() > maxY) 
				maxY = coordinate.getY();
			if (coordinate.getZ() > maxZ) 
				maxZ = coordinate.getZ();
		}
		
		return new int[] { 
				Math.abs(maxX - minX + 1),
				Math.abs(maxY - minY + 1),
				Math.abs(maxZ - minZ + 1) };
	}
	
	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append("{");
		for (Coordinate coord : coordinates) {
			buf.append(coord.toString());
			buf.append(", ");
		}
		buf.append("}");
		return buf.toString();
	}
		
}
