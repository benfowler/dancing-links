package au.id.bjf.dlx;

import au.id.bjf.dlx.data.ColumnObject;

public class TestBuildSparseMatrix extends AbstractDLXTest {

	private static final byte[][] TEST_MATRIX_1 = {
				{ 0, 0, 1, 0, 1, 1, 0 },
				{ 1, 0, 0, 1, 0, 0, 1 },
				{ 0, 1, 1, 0, 0, 1, 0 },
				{ 1, 0, 0, 1, 0, 0, 0 },
				{ 0, 1, 0, 0, 0, 0, 1 },
				{ 0, 0, 0, 1, 1, 0, 1 }
		};

	ColumnObject h;

	public TestBuildSparseMatrix() {
		h = DLX.buildSparseMatrix(TEST_MATRIX_1, new Object[] { }, true);
	}

	@Override
	public byte[][] getExpectedByteArray() {
		return TEST_MATRIX_1;
	}

	@Override
	public ColumnObject getSparseArrayRoot() {
		return h;
	}

}
