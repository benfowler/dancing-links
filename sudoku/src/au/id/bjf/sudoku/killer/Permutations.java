package au.id.bjf.sudoku.killer;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;


/**
 * Utility class that obtains all permutations of digits that can fit into a
 * given cage.  The most interesting method here is 
 * {@link #getPermutations(int, int)}, which permits getting all permutations
 * of digits that are legal for a given cage.
 */
public class Permutations {

	private Map<Integer, Map<Integer, List<Permutation>>> lengthSumIndex 
		= new TreeMap<Integer, Map<Integer, List<Permutation>>>();

	
	public Permutations() {
		indexPermutations();
	}

	/**
	 * Obtain a list of all permutations for the given number of cage digits
	 * and their sum
	 * 
	 * @param numDigits number of digits in the cage
	 * @param sum sum of digits in the cage
	 * @return a list of all permutations of digits in this cage
	 */
	private List<Permutation> getPermutations(final int numDigits, 
			final int sum) {
		final Map<Integer, List<Permutation>> numDigitsBucket = 
				lengthSumIndex.get(numDigits);
		if (numDigitsBucket != null) {
			return numDigitsBucket.get(sum);
		}
		
		return null;
	}
	
	/**
	 * Generate all possible permutations of digits.  Uses some bit-diddling
	 * that will not scale beyond n=31.
	 * 
	 * @return a list of all permutations, in no special order
	 */
	private List<Permutation> generateAllPermutations() {
		
		final List<Permutation> results = new LinkedList<Permutation>();
		final Set<Integer> elements = new HashSet<Integer>();
		
		for (int i=1; i < 512; ++i) {    // FIXME: magic number
			// construct permutation using bits extracted from i
			int temp = i;
			for (int j = 1; j <= 9; ++ j) {
				if ((temp & 1) == 1)
					elements.add(j);
				temp >>= 1;
			}
			Permutation p = new Permutation((Integer[]) 
					elements.toArray(new Integer[] {}));
			results.add(p);
			elements.clear();
		}
		return results;
	}
	
	/**
	 * Generate all permutations, then return them as a nested set of 
	 * {@link Map}s organized by number of digits, then sum.
	 * 
	 * @return {@link Map} of ({@link Map}s of permutations keyed by sum),
	 *     keyed by number of digits. 
	 */
	private void indexPermutations() {
		
		final List<Permutation> allPermutations = generateAllPermutations();
		
		for (final Permutation permutation : allPermutations) {
			
			final int numDigits = permutation.size();
			final int sum = permutation.sum();
	
			// permutation number-of-digits bucket
			Map<Integer, List<Permutation>> numOfDigitsBucket = 
					lengthSumIndex.get(numDigits);
			if (numOfDigitsBucket == null) {
				numOfDigitsBucket = new TreeMap<Integer, List<Permutation>>();
				lengthSumIndex.put(numDigits, numOfDigitsBucket);
			}
			
			// permutation sum bucket
			List<Permutation> sumBucket = numOfDigitsBucket.get(sum);
			if (sumBucket == null) {
				sumBucket = new ArrayList<Permutation>();
				numOfDigitsBucket.put(sum, sumBucket);
			}

			sumBucket.add(permutation);
		}
		
	}
	
	private void dumpAllPermutations(PrintStream out) {
		for (Entry<Integer, Map<Integer, List<Permutation>>> entry 
				: lengthSumIndex.entrySet()) {
			
			final int numDigits = entry.getKey();
			out.println(String.format("%d digits:\n", numDigits));
			for (Entry<Integer, List<Permutation>> sumEntry 
					: entry.getValue().entrySet()) {
				final int sum = sumEntry.getKey();
				final List<Permutation> perms = sumEntry.getValue();
				final StringBuilder builder = new StringBuilder();
				builder.append(sum).append(": ");
				for (final Permutation p : perms) {
					builder.append(p.toString()).append(' ');
				}
				out.println(builder.toString());
			}
			out.println("\n");
		}
	}
	
}
