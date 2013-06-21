package au.id.bjf.dlx;

import au.id.bjf.dlx.data.ColumnObject;

public class TestCoverUncover extends AbstractDLXTest {

	private static final byte[][] TEST_MATRIX_1 = {
		{ 0, 0, 1, 0, 1, 1, 0 },
		{ 1, 0, 0, 1, 0, 0, 1 },
		{ 0, 1, 1, 0, 0, 1, 0 },
		{ 1, 0, 0, 1, 0, 0, 0 },
		{ 0, 1, 0, 0, 0, 0, 1 },
		{ 0, 0, 0, 1, 1, 0, 1 }};

	private final ColumnObject h;

	public TestCoverUncover() {
		h = DLX.buildSparseMatrix(TEST_MATRIX_1, new Object[] {}, true);
		// find first column, cover, then uncover
		final ColumnObject columnA = (ColumnObject)h.R;
		DLX.cover(h, columnA);
		DLX.uncover(h, columnA);
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
