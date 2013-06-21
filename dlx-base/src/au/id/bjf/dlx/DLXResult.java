package au.id.bjf.dlx;

import java.util.Iterator;
import java.util.List;

/**
 * This class models a DLX algorithm search result. The result object contains
 * a list of rows of size <strong>k</strong>, where <strong>k</strong> is the
 * depth of the search when the result was found.  Each row is returned as
 * a sequence of column labels; thus it's important to initialize DLX with
 * a meaningful set of column labels to get sensible results.
 * <p>
 * @see DLX#search()
 */
public class DLXResult {

	private final List<List<Object> > resultData;

	public DLXResult(List<List<Object>> resultData) {
		this.resultData = resultData;
	}

	@Override
	public String toString() {
		final StringBuffer buffer = new StringBuffer();
		for (final List<Object> row : resultData) {
			for (final Object label : row) {
				buffer.append(label.toString());
				buffer.append(' ');
			}
			buffer.append('\n');
		}
		return buffer.toString();
	}

	public Iterator<List<Object>> rows() {
		return resultData.iterator();
	}

}
