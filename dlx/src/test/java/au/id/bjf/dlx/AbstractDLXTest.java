package au.id.bjf.dlx;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import au.id.bjf.dlx.data.ColumnObject;
import au.id.bjf.dlx.data.DataObject;
import au.id.bjf.dlx.data.DebugDataObject;

public abstract class AbstractDLXTest {

	public static final int DEFAULT_WALK_TIMEOUT = 100;

	/**
	 * How many nodes to traverse in a circular doubly-linked list before we
	 * consider the operation to have 'timed out'.
	 *
	 * @return timeout in number of traversals
	 */
	public int getListWalkTimeout() {
		return DEFAULT_WALK_TIMEOUT;
	}

	/**
	 * Return the root of the sparse matrix to run tests on.
	 * @return the root node of the sparse matrix
	 */
	public abstract ColumnObject getSparseArrayRoot();

	/**
	 * Return a byte matrix containing test data.  The values stored will be
	 * compared with the contents of the sparse matrix, and if differences are
	 * encountered, then tests will fail.
	 *
	 * @return an array of byte arrays.  The number of rows must be non zero.
	 *     Each row's length must be the same, and be greater than zero.
	 */
	public abstract byte[][] getExpectedByteArray();

	/**
	 * Check the counts of ones in columns in the sparse matrix are sensible.
	 */
	@Test
	public void columnCounts() {
		System.out.print("testColumnCounts(): ");
		final ColumnObject root = getSparseArrayRoot();
		ColumnObject curr = (ColumnObject)root.R;
		int i = 0;
		while (curr != root) {
			System.out.print(curr.size + " ");

			final int countFromSparseArray = curr.size;
			final int countFromByteArray = countOnesInArrayColumn(
					getExpectedByteArray(), i);
			assertEquals(countFromByteArray, countFromSparseArray, "Ones in columns in input byte array and output " +
					"sparse array don't match");

			curr = (ColumnObject)curr.R;
			i++;
		}
		System.out.println();
	}

	private int countOnesInArrayColumn(byte[][] input, int column) {
		int result = 0;
		for (byte[] bytes : input) {
			if (bytes[column] != 0)
				result++;
		}
		return result;
	}

	/**
	 * Traverse the generated sparse matrix to ensure that all data nodes point
	 * to their column header node.
	 */
	@Test
	public void columnHeaderLinks() {
		// For each column, scan children to ensure they all link to column
		// header node
		System.out.println("testColumnHeaderLinks()");
		final ColumnObject h = getSparseArrayRoot();
		ColumnObject currentColumn = (ColumnObject)h.R;
		assertNotSame(currentColumn, h, "No columns exist");
		while (currentColumn != h) {
			DataObject currentNode = currentColumn.D;
			while (currentNode != currentColumn) {
				assertSame(currentNode.C, currentColumn);
				currentNode = currentNode.D;
			}
			currentColumn = (ColumnObject)currentColumn.R;
		}
	}

	/**
	 * Check that the counts of ones in rows in the sparse matrix are
	 * sensible.
	 */
	@Test
	public void rowCounts() {
		// Get rows from sparse matrix.  Check counts.
		System.out.println("testRowCounts()");
		final List<DataObject> rows = getRowsFromSparseMatrix(getSparseArrayRoot());
		System.out.println(rows.size() + " rows found in sparse matrix.  " +
				"One counts are:");
		for (int i = 0; i < rows.size(); ++i) {
			final int countFromByteArray = countOnesInArrayRow(
					getExpectedByteArray(), i);
			final DataObject rowNode = rows.get(i);
			final int countFromSparseArray = countNodesInSparseArrayRow(rowNode);
			System.out.print(countFromSparseArray + " ");
			assertEquals(countFromByteArray,
					countFromSparseArray,
					"Row counts must match in input byte array and" +
					" output sparse array");
		}
		System.out.println();
	}

	// There must be at least one non-empty row.
	private int countOnesInArrayRow(byte[][] input, int row) {
		int result = 0;
		for (int i = 0; i < input[row].length; ++i) {
			if (input[row][i] != 0)
				result++;
		}
		return result;
	}

	private int countNodesInSparseArrayRow(DataObject rowNode) {
		int result = 1;
		DataObject curr = rowNode.R;
		while (curr != rowNode) {
			result++;
			curr = curr.R;
		}
		return result;
	}

	/**
	 * Tests the integrity of the generated sparse matrix by traversing all
	 * the node links in all directions.
	 */
	@Test
	public void allListsAreCircular() {
		System.out.println("testAllListsAreCircular()");
		final ColumnObject h = getSparseArrayRoot();
		int a, b;

		// Check that root and column header list is a doubly linked list
		System.out.println("Traversing left and right from root node");
		a = walkListLeft(h, getListWalkTimeout());
		b = walkListRight(h, getListWalkTimeout());
		assertEquals(a, b, "Count traversing list in opposite directions must be" +
				" equal");

		// For each column, scan children to ensure they all link to column
		// header node
		ColumnObject currentColumn = (ColumnObject)h.R;
		assertNotSame(currentColumn, h, "No columns exist");
		while (currentColumn != h) {
			System.out.println("Now scanning column '" +
					currentColumn.name.toString() + "'");
			DataObject currentNode = currentColumn.D;
			while (currentNode != currentColumn) {
				System.out.println("Scanning node '" + currentNode.toString() + "'");
				assertSame(currentNode.C, currentColumn);
				a = walkListLeft(currentNode, getListWalkTimeout());
				b = walkListRight(currentNode, getListWalkTimeout());
				assertEquals(a, b, "Count traversing list in opposite directions " +
						"(left and right) must be equal");
				a = walkListUp(currentNode, getListWalkTimeout());
				b = walkListDown(currentNode, getListWalkTimeout());
				assertEquals(a, b, "Count traversing list in opposite directions " +
						"(up and down) must be equal");
				currentNode = currentNode.D;
			}
			currentColumn = (ColumnObject)currentColumn.R;
		}
	}

	interface ListWalkStrategy {
		DataObject walk(DataObject aNode);
	}

	protected int walkListUp(DataObject aNode, int maxTimeOut) {
		return walkList(aNode, maxTimeOut, true, new ListWalkStrategy() {
			@Override
			public String toString() { return "up"; }
			public DataObject walk(DataObject aNode) { return aNode.U; }
		});
	}

	protected int walkListDown(DataObject aNode, int maxTimeOut) {
		return walkList(aNode, maxTimeOut, true, new ListWalkStrategy() {
			@Override
			public String toString() { return "down"; }
			public DataObject walk(DataObject aNode) { return aNode.D; }
		});
	}

	protected int walkListLeft(DataObject aNode, int maxTimeOut) {
		return walkList(aNode, maxTimeOut, false, new ListWalkStrategy() {
			@Override
			public String toString() { return "left"; }
			public DataObject walk(DataObject aNode) { return aNode.L; }
		});
	}

	protected int walkListRight(DataObject aNode, int maxTimeOut) {
		return walkList(aNode, maxTimeOut, false, new ListWalkStrategy() {
			@Override
			public String toString() { return "right"; }
			public DataObject walk(DataObject aNode) { return aNode.R; }
		});
	}

	private int walkList(DataObject aNode, int maxTimeOut,
			boolean assertMustEncounterColumnHeader,
			ListWalkStrategy walkStrategy) {
		int timeOut = maxTimeOut;
		boolean seenColumnHeader = false;
		assertTrue(maxTimeOut > 0, "Timeout must be greater than 0");
		DataObject current = aNode;
		while (timeOut > 0) {
			current = walkStrategy.walk(current);
			if (current == aNode)
				break;
			if (current.getClass().equals(ColumnObject.class))
				seenColumnHeader = true;
			timeOut--;
		}
		if (timeOut == 0) {
			fail("Timed out while walking node '" + aNode.toString() +
					"' using walk strategy '" + walkStrategy + "'");
		}
		if (assertMustEncounterColumnHeader) {
			assertTrue(seenColumnHeader, "Must encounter column header");
		}
		System.out.println("Walked list node using strategy '" +
				walkStrategy + "'. " + (maxTimeOut - timeOut) +
				" traversal(s).");
		return (maxTimeOut - timeOut);
	}

	protected List<DataObject> getRowsFromSparseMatrix(ColumnObject root) {
		final Map<Long, DataObject> firstNodesOfEachRow = new TreeMap<Long, DataObject>();
		ColumnObject currentColumn = (ColumnObject)root.R;
		while (currentColumn != root) {
			DataObject node = currentColumn.D;
			while (node != currentColumn) {
				if (node instanceof DebugDataObject) {
					final long rowKey = ((DebugDataObject)node).rowSeqNum;
					if (!firstNodesOfEachRow.containsKey(rowKey))
						firstNodesOfEachRow.put(rowKey, node);
				}
				node = node.D;
			}
			currentColumn = (ColumnObject)currentColumn.R;
		}
		return new LinkedList<DataObject>(firstNodesOfEachRow.values());
	}

	@BeforeEach
	public void setUp() throws Exception {
		System.out.println();
	}

}
