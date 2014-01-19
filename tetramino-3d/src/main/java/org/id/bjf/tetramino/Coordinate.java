package org.id.bjf.tetramino;

/**
 * Three-dimension coordinate of a puzzle piece
 */
public class Coordinate implements Cloneable, Comparable<Coordinate> {
	
	private int x, y, z;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}
	
	public void setAll(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (!obj.getClass().equals(this.getClass()))
			return false;
		
		Coordinate other = (Coordinate)obj;
		return ((x == other.x) && (y == other.y)
				&& (z == other.z));
	}
	
	@Override
	public int hashCode() {
		int val = 13;
		val += (17 * x);
		val += (17 * y);
		val += (17 * z);
		return val;
	}
	
	@Override
	protected Coordinate clone() {
		Coordinate result = new Coordinate();
		result.x = x;
		result.y = y;
		result.z = z;
		return result;
	}
	
	@Override
	public String toString() {
		return String.format("[%d, %d, %d]", x, y, z);
	}

	public int compareTo(Coordinate other) {
		if (z != other.z)
			return z - other.z;
		if (y != other.y)
			return y - other.y;
		if (x != other.x)
			return x - other.x;
		return 0;
	}
	
}
