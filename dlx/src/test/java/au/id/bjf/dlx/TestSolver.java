package au.id.bjf.dlx;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import au.id.bjf.dlx.data.ColumnObject;

class TestSolver {

	private static final byte[][] TEST_MATRIX_1 = {
		{ 0, 0, 1, 0, 1, 1, 0 },
		{ 1, 0, 0, 1, 0, 0, 1 },
		{ 0, 1, 1, 0, 0, 1, 0 },
		{ 1, 0, 0, 1, 0, 0, 0 },
		{ 0, 1, 0, 0, 0, 0, 1 },
		{ 0, 0, 0, 1, 1, 0, 1 } };

	private ColumnObject h;

	@BeforeEach
	void setUp() throws Exception {
		h = DLX.buildSparseMatrix(TEST_MATRIX_1,
				new Object[] { "A", "B", "C", "D", "E", "F", "G" }, true);
	}

	@AfterEach
	void tearDown() throws Exception {
		h = null;
	}

	@Test
	void runDlxSolver() {
		DLX.solve(h, false, new TestDLXResultProcessor(
				new Object[][] {
						{ "A", "D"},
						{ "B", "G" },
						{ "C", "E", "F" }}));
	}

	@Test
	void runDlxSolverWithSHeuristic() {
		DLX.solve(h, true, new TestDLXResultProcessor(
				new Object[][] {
						{ "A", "D"},
						{ "E", "F", "C" },
						{ "B", "G" }}));
	}

	class TestDLXResultProcessor implements DLXResultProcessor {

		Object[][] expectedResults;

		public TestDLXResultProcessor(Object[][] expectedResults) {
			this.expectedResults = expectedResults;
		}

		public boolean processResult(DLXResult result) {
			System.out.println(result.toString());

			final Iterator<List<Object>> rows = result.rows();
			int i = 0;
			while (rows.hasNext()) {
				final List<Object> row = rows.next();
				final Iterator<Object> nodes = row.iterator();
				int j = 0;
				while (nodes.hasNext()) {
					final Object node = nodes.next();
					assertTrue(j < expectedResults[i].length,
							"Encountered unexpected value '" + node +
							"' on row " + i + ", node" + j);
					assertEquals(expectedResults[i][j], node, "Object mismatch in output, row " + i +
							", node" + j);

					j++;
				}

				assertEquals(expectedResults[i].length, j, "Node count in row " + i + " is wrong");

				i++;
			}

			assertEquals(expectedResults.length, i, "Row count in expected and actual results must " +
					"be equal");

			return false;  // we want first result only
		}

	}

}
