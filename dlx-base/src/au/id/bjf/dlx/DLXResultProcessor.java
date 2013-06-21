package au.id.bjf.dlx;

/**
 * Acts as a sink for generated results.
 */
public interface DLXResultProcessor {

	/**
	 * Consume a result.  Return <code>true</code> to search for more results,
	 * <code>false</code> to cause DLX to terminate early.
	 *
	 * @param result a result
	 * @return <code>true</code> to search for more results, <code>false</code>
	 *     to stop processing.
	 */
	boolean processResult(DLXResult result);

}
