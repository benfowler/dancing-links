package au.id.bjf.kaleidoscope;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * A Kaleidoscope playing piece.
 */
public class KPiece implements Iterable<KUnitSquare> {

	private Set<KUnitSquare> unitSquares;

	private String name = "unknown";

	private int pieceId;
	
	private int width = 0;
	
	private int height = 0;

	public KPiece(final String name, final int pieceId,
			final Set<KUnitSquare> unitSquares) {
		if (name != null)
			this.name = name;
		this.pieceId = pieceId;
		this.unitSquares = unitSquares;
		findWidthAndHeight();
	}

	public KPiece(String name, int pieceId, String... unitSquaresStr) {
		if (name != null)
			this.name = name;
		this.pieceId = pieceId;
		Set<KUnitSquare> results = new HashSet<KUnitSquare>();
		for (int y = 0; y < unitSquaresStr.length; ++y) {
			final String row = unitSquaresStr[y];
			for (int x = 0; x < row.length(); ++x) {
				final char shortCode = row.charAt(x);
				if (shortCode != ' ') // skip spaces, used to make funny shapes
					results.add(new KUnitSquare(x, y, 
							KColor.findFromShortCode(row.charAt(x))));
			}
		}
		unitSquares = results;
		findWidthAndHeight();
	}

	/**
	 * Get the human-readable name of this piece
	 * 
	 * @return human-readable string representing the piece's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get a piece ID, which is the same for a piece, irrespective of how it's
	 * flipped or rotated
	 * 
	 * @return unique piece ID
	 */
	public int getPieceId() {
		return pieceId;
	}

	/**
	 * Get the width of this piece in units, assuming that the piece's 
	 * top left edge is always (0, 0)
	 * @return width of piece in units
	 */
	public int getWidth() {
		return width;
	};
	
	/**
	 * Get the height of this piece in units, assuming that the piece's 
	 * top left edge is always (0, 0)
	 * @return height of piece in units
	 */
	public int getHeight() {
		return height;
	}

	@Override
	public Iterator<KUnitSquare> iterator() {
		return unitSquares.iterator();
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(name).append(": ");
		for (KUnitSquare square : unitSquares) {
			builder.append(square.toString());
		}
		return builder.toString();
	}

	/**
	 * Generate a new piece, with the coordinates rotated 90 degrees clockwise.
	 * Assumes that coordinates of the contained unit squares are zero-based and
	 * relative. The resulting piece's coordinates will still be zero-based,
	 * positive and relative.
	 * 
	 * @return a new {@link KPiece}, rotated 90 degrees clockwise
	 */
	public KPiece rotatedNinetyDegrees() {
		/*
		 * Scan for maximum height and width, assuming that origin is always
		 * (0,0)
		 */
		
		final Set<KUnitSquare> rotatedUnitSquares = new HashSet<KUnitSquare>();
		for (KUnitSquare unitSquare : unitSquares) {
			rotatedUnitSquares.add(new KUnitSquare(
					(getHeight() - 1) - unitSquare.getY(), // new X coordinate
					unitSquare.getX(),              // new Y coordinate
					unitSquare.getColor()));        // same color
		}
		return new KPiece(getName(), getPieceId(), rotatedUnitSquares);
	}

	private void findWidthAndHeight()
	{
		int maxX = -1;
		int maxY = -1;
		
		for (KUnitSquare unitSquare : unitSquares) {
			if (unitSquare.getX() > maxX)
				maxX = unitSquare.getX();
			if (unitSquare.getY() > maxY)
				maxY = unitSquare.getY();
		}

		this.width = maxX + 1;
		this.height = maxY + 1;		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + height;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + pieceId;
		result = prime * result
				+ ((unitSquares == null) ? 0 : unitSquares.hashCode());
		result = prime * result + width;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KPiece other = (KPiece) obj;
		if (height != other.height)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pieceId != other.pieceId)
			return false;
		if (unitSquares == null) {
			if (other.unitSquares != null)
				return false;
		} else if (!unitSquares.equals(other.unitSquares))
			return false;
		if (width != other.width)
			return false;
		return true;
	}
	
}
