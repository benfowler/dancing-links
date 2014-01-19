package au.id.bjf.sudoku.killer;

/**
 * Immutable set of legal values to insert into a given square.  The universe
 * of all possible cage permutations in a Killer Sudoku is managed by
 * {@link au.id.bjf.sudoku.killer.Permutations}
 */
public class Permutation {

	static final int ELEMENT_MAX = 9;
	
	// indexed from 1, *not* zero
	private boolean[] elements = new boolean[ELEMENT_MAX+1];
	
	private int size, sum;
	
	private String stringRepr;
	
	
	/**
	 * Initialize permutation with a set of elements, which must be unique
	 * @param elements elements. No duplicates allowed.
	 * @throws IllegalArgumentException bad argument (duplicates, etc)
	 */
	public Permutation(final Integer[] elements) {
		for (int element: elements) {
			if (this.elements[element] == true)
				throw new IllegalArgumentException("elements");
			this.elements[element] = true;
			this.size++;
			this.sum += element; 
		}
		
		final StringBuilder builder = new StringBuilder(9);
		for (int i=1; i<=ELEMENT_MAX; ++i) {
			if (this.elements[i] != false)
				builder.append(i);
		}
		stringRepr = builder.toString();
	}

	/**
	 * Return the number of elements in the set
	 * @return the number of elements in the set
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Return the sum of the elements in the set
	 * @return the sum of the elements in the set
	 */
	public int sum() {
		return sum;
	}
	
	@Override
	public String toString() {
		return stringRepr;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((stringRepr == null) ? 0 : stringRepr.hashCode());
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
		Permutation other = (Permutation) obj;
		if (stringRepr == null) {
			if (other.stringRepr != null)
				return false;
		} else if (!stringRepr.equals(other.stringRepr))
			return false;
		return true;
	}

}
