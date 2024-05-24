package au.id.bjf.dlx;

import au.id.bjf.dlx.data.ColumnObject;
import au.id.bjf.dlx.data.DataObject;
import junit.framework.TestCase;

/**
 * Construct a sparse matrix with some optional columns, and then perform some
 * sanity checks.
 */
public class TestBuildSparseMatrixWithOptionalCols extends TestCase {

	private static final byte[][] TEST_MATRIX_1 = {
		{ 0, 0, 1, 0, 1, 1, 0 },
		{ 1, 0, 0, 1, 0, 0, 1 },
		{ 0, 1, 1, 0, 0, 1, 0 },
		{ 1, 0, 0, 1, 0, 0, 0 },
		{ 0, 1, 0, 0, 0, 0, 1 },
		{ 0, 0, 0, 1, 1, 0, 1 }};

	private static final int NUM_OPTIONAL = 3;

	private ColumnObject h = null;

	public void testBuildingSparseMatrixWithOptionalColumns() {
		
		// Build matrix in the regular (all columns mandatory 1..1) manner
		h = DLX.buildSparseMatrix(TEST_MATRIX_1, new Object[] {}, true);
		
		// Assert that root and columns are all linked together
		int expectedWidth = TEST_MATRIX_1[0].length;
		assertColumnRowSpans(h, expectedWidth);
		
		// Record addresses of columns for later use
		DataObject currentNode = h;
		DataObject[] columns = new DataObject[expectedWidth];
		currentNode = h.R;
		for (int i = 0; i < expectedWidth; ++i) {
			if (currentNode == h) {
				fail("Unexpectedly encountered root node while traversing " +
						"column headers");
			}
			columns[i] = currentNode;
			currentNode = currentNode.R;
		}
		
		// Now, make last three columns optional.  Check that they have been
		// configured correctly, according to Knuth's paper
		DLX.setColumnsAsOptional(h, expectedWidth - NUM_OPTIONAL, NUM_OPTIONAL);
		assertColumnRowSpans(h, expectedWidth - NUM_OPTIONAL);
		assertSame(columns[expectedWidth - NUM_OPTIONAL - 1].R, h);
		assertSame(h.L, columns[expectedWidth - NUM_OPTIONAL - 1]);
		
		for (int i = expectedWidth - NUM_OPTIONAL; i < expectedWidth; ++i) {
			assertSame("Optional column's L attribute doesn't point to itself", columns[i].L, columns[i]);
			assertSame("Optional column's R attribute doesn't point to itself", columns[i].R, columns[i]);
		}
		
	}
	
	private void assertColumnRowSpans(DataObject h, int expectedCols) {
		int counter = 0;
		DataObject currentNode = h.R;
		do {
			currentNode = currentNode.R;
			counter++;
		} while (currentNode != h);
		assertEquals(expectedCols, counter);
	}
	
}
