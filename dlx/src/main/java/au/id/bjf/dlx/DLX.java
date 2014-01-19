package au.id.bjf.dlx;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import au.id.bjf.dlx.data.ColumnObject;
import au.id.bjf.dlx.data.DataObject;
import au.id.bjf.dlx.data.DebugDataObject;

/**
 * Knuth's DLX algorithm.  This code implements a solution to the exact
 * matrix cover problem.  It works particularly well on large problems.
 * <p>
 * The code presented is a straightforward translation of the pseudo-code
 * supplied for the algorithm in the paper; use the paper as a starting point
 * for understanding the code.
 * <p>
 * See Knuth, D.; "Dancing Links", Stanford University, 2000.
 */
public class DLX {

	/**
	 * Build the DLX data structure from a byte matrix of ones and zeroes.
	 * @param input a list of rows of bytes.  All rows must be same size.
	 * @return root node of initialized data structure for DLX algorithm
	 */
	public static final ColumnObject buildSparseMatrix(byte[][] input) {
		return buildSparseMatrix(input, null, false);
	}

	/**
	 * Build the DLX data structure from a byte matrix of ones and zeroes.
	 * @param input a list of rows of bytes.  All rows must be same size.
	 * @param labels column labels
	 * @return root node of initialized data structure for DLX algorithm
	 */
	public static final ColumnObject buildSparseMatrix(byte[][] input,
			Object[] labels) {
		return buildSparseMatrix(input, labels, false);
	}

	/**
	 * Build the DLX data structure from a byte matrix of ones and zeroes.
	 * @param input a list of rows of bytes.  All rows must be same size.
	 * @param labels column labels
	 * @param dbgUseRowSquenceNumbers annotate data objects with a sequential
	 *     row number for debugging and testing purposes.  Set to
	 *     <code>false</code> for production use.
	 * @return root node of initialized data structure for DLX algorithm
	 */
	public static final ColumnObject buildSparseMatrix(byte[][] input,
			Object[] labels, boolean dbgUseRowSquenceNumbers) {

		// Make root node and column headers
		final ColumnObject h = new ColumnObject();
		h.L = h; h.R = h; h.U = h; h.D = h;

		for (int j = 0; j < input[0].length; ++j) {
			final ColumnObject newColHdr = new ColumnObject();
			newColHdr.U = newColHdr;
			newColHdr.D = newColHdr;
			newColHdr.R = h;
			newColHdr.L = h.L;
			h.L.R = newColHdr;
			h.L = newColHdr;
			if ((labels != null) && (j < labels.length) && (labels[j] != null))
				newColHdr.name = labels[j];
		}

		// For each row, build a list and stitch it onto the column headers
		long rowSequenceNumber = 0;
		final List<DataObject> thisRowObjects = new LinkedList<DataObject>();
		for (int j = 0; j < input.length; ++j) {
			thisRowObjects.clear();
			ColumnObject currentCol = (ColumnObject)h.R;
			for (int i = 0; i < input[j].length; ++i) {
				// Build data objects, attach to column objects
				if (input[j][i] != 0) {
					DataObject obj = null;
					if (dbgUseRowSquenceNumbers == true) {
						obj = new DebugDataObject();
						((DebugDataObject)obj).rowSeqNum = rowSequenceNumber;
					} else {
						obj = new DataObject();
					}
					obj.U = currentCol.U;
					obj.D = currentCol;
					obj.L = obj;
					obj.R = obj;
					obj.C = currentCol;
					currentCol.U.D = obj;
					currentCol.U = obj;
					currentCol.size++;
					thisRowObjects.add(obj);
				}
				currentCol = (ColumnObject)currentCol.R;
			}
			// Link all data objects built for this row horizontally
			if (thisRowObjects.size() > 0) {
				final Iterator<DataObject> iter = thisRowObjects.iterator();
				final DataObject first = iter.next();
				while (iter.hasNext()) {
					final DataObject thisObj = iter.next();
					thisObj.L = first.L;
					thisObj.R = first;
					first.L.R = thisObj;
					first.L = thisObj;
				}
			}
			rowSequenceNumber++;
		}

		return h;
	}

	/**
	 * Given the supplied sparse matrix, run Knuth's DLX algorithm over it,
	 * sending the results to the console.  It is highly advisable that when
	 * using this method, you build the sparse matrix with a meaningful set
	 * of labels, in order to get proper output.
	 *
	 * @param h the root of the sparse matrix to solve
	 * @param useSHeuristic flag specifying whether or not to attempt to
	 *     minimize the depth of the search tree by picking columns to cover
	 *     that contain the least ones in it
	 */
	public static final void solve(ColumnObject h, boolean useSHeuristic) {
		solve(h, useSHeuristic, new SimpleDLXResultProcessor());
	}

	/**
	 * Given the supplied sparse matrix, run Knuth's DLX algorithm over it,
	 * passing solutions to <code>resultProcessor</code> to process.
	 *
	 * @param h the root of the sparse matrix to solve
	 * @param useSHeuristic flag specifying whether or not to attempt to
	 *     minimize the depth of the search tree by picking columns to cover
	 *     that contain the least ones in it
	 * @param resultProcessor an object which supplies result processing
	 *     strategy
	 */
	public static final void solve(ColumnObject h, boolean useSHeuristic,
			DLXResultProcessor resultProcessor) {
		solve(h, useSHeuristic, resultProcessor, 0,
				new ArrayList<DataObject>());
	}

	/**
	 * Reconfigure the sparse matrix to make a number of columns optional.
	 * By 'optional', we mean that a one may appear at most once in the solved
	 * matrix, as opposed to the usual case where a one must appear once and
	 * only once.  The sum of <code>numMandatory</code> and 
	 * <code>numOptional</code> must add up to the exact number of columns in
	 * the matrix.  If not, a <code>SudokuException</code> is thrown.
	 * 
	 * @param h the root node of the sparse matrix
	 * @param numMandatory the number of columns which are mandatory (always
	 *     columns 0 to (<em>numMandatory</em> - 1))
	 * @param numOptional the number of optional columns (always the last
	 *     <em>numOptional</em> columns)
	 */
	public static final void setColumnsAsOptional(ColumnObject h, 
			int numMandatory, int numOptional) {
		
		if (numMandatory < 0) throw new IllegalArgumentException("numMandatory");
		if (numOptional < 1) throw new IllegalArgumentException("numOptional");
		if (h == null) throw new IllegalArgumentException("total");
		
		int total = numMandatory + numOptional;
		DataObject columns[] = new DataObject[total];
		DataObject current = h.R;
		for (int i = 0; i < total; ++ i) {
			columns[i] = current;
			if (current == h) {
				// root seen too early, complain
				throw new DLXException("Expected at least " + total
						+ " columns");
			}
			current = current.R;
		}

		// Wrap mandatory columns
		if (numMandatory > 0) {
			columns[numMandatory - 1].R = h;
			h.L = columns[numMandatory - 1];
		}
		
		// Optional columns' L and R references point to itself
		for (int i = numMandatory; i < total; ++ i) {
			columns[i].L = columns[i];
			columns[i].R = columns[i];
		}
	}

	static final boolean solve(ColumnObject h, boolean useSHeuristic,
			DLXResultProcessor resultProcessor, int k, List<DataObject> o) {
		if (h.R == h) {
			return processResult(resultProcessor, o);
		}

		ColumnObject c = (ColumnObject)h.R;
		if (useSHeuristic) {
			int s = Integer.MAX_VALUE;
			ColumnObject j = ((ColumnObject)h.R);
			while (j != h) {
				if (j.size < s) {
					s = j.size;
					c = j;
				}
				j = (ColumnObject)j.R;
			}
		}

		boolean stillRunning = true;

		cover(h, c);
		DataObject r = c.D;
		while ((stillRunning) && (r != c)) {
			grow(o, k+1);
			o.set(k, r);

			DataObject j = r.R;
			while (j != r) {
				cover(h, (ColumnObject)j.C);
				j = j.R;
			}

			stillRunning = solve(h, useSHeuristic, resultProcessor, k+1, o);

			j = r.L;
			while (j != r) {
				uncover(h, (ColumnObject)j.C);
				j = j.L;
			}

			r = r.D;
		}
		uncover(h, c);

		return (stillRunning == true);
	}

    private static void grow(List<DataObject> o, int k) {
		if (o.size() < k) {
			while (o.size() < k) {
				o.add(null);
			}
		} else if (o.size() > k) {
			while (o.size() > k) {
				o.remove(o.size() - 1);
			}
		}
	}

	private static final boolean processResult(DLXResultProcessor processor,
			List<DataObject> o) {
		final List<List<Object>> resultSet = new LinkedList<List<Object>>();
		for (final DataObject oK : o) {
			final List<Object> resultRow = new LinkedList<Object>();
			DataObject node = oK;
			do {
				resultRow.add(((ColumnObject)node.C).name);
				node = node.R;
			} while (node != oK);

			resultSet.add(resultRow);
		}
		final DLXResult result = new DLXResult(resultSet);
		return processor.processResult(result);
	}

	/**
	 * Cover a column in the sparse matrix
	 * @param h the root of the sparse matrix
	 * @param columnObj the column header of the column to cover
	 */
	static final void cover(ColumnObject h, ColumnObject columnObj) {
		columnObj.R.L = columnObj.L;
		columnObj.L.R = columnObj.R;
		DataObject i = columnObj.D;
		while (i != columnObj) {
			DataObject j = i.R;
			while (j != i) {
				j.D.U = j.U;
				j.U.D = j.D;
				((ColumnObject)j.C).size--;
				j = j.R;
			}

			i = i.D;
		}
	}

	/**
	 * Uncovers a column in the sparse matrix
	 * @param h the root of the sparse matrix
	 * @param columnObj the column header of the column to uncover.  It must
	 *     have been covered by a prior
	 *     {@link #cover(ColumnObject, ColumnObject)} operation.
	 */
	static final void uncover(ColumnObject h, ColumnObject columnObj) {
		DataObject i = columnObj.U;
		while (i != columnObj) {
			DataObject j = i.L;
			while (j != i) {
				((ColumnObject)j.C).size++;
				j.D.U = j;
				j.U.D = j;
				j = j.L;
			}

			i = i.U;
		}
		columnObj.R.L = columnObj;
		columnObj.L.R = columnObj;
	}

}


