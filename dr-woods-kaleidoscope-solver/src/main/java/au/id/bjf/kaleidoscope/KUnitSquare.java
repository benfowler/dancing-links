package au.id.bjf.kaleidoscope;

/**
 * A unit square of a piece.  Consists of a set of relative (x, y) coordinates
 * and a color.  A list of these is used to model a {@link KPiece}.
 */
public class KUnitSquare {
	
	private int x, y;
	private KColor color;
	
	/** 
	 * Construct a KUnitSquare, to form part of a {@link KPiece}
	 * 
	 * @param x zero-based X coordinate, relative to origin at top-left
	 * @param y zero-based Y coordinate, relative to origin at top-left
	 * @param color color of the unit square
	 */
	public KUnitSquare(int x, int y, KColor color) {
		super();
		this.x = x;
		this.y = y;
		this.color = color;
	}

	/**
	 * Get the zero-based X coordinate, relative to origin at top-left
	 * @return X coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * Get the zero-based Y coordinate, relative to origin at top-left
	 * @return Y coordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * Get the color
	 * @return
	 */
	public KColor getColor() {
		return color;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("(x=").append(x).append(", y=").append(y)
			.append(", color=").append(color).append(")");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + x;
		result = prime * result + y;
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
		KUnitSquare other = (KUnitSquare) obj;
		if (color != other.color)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
		
}
