package au.id.bjf.dlx;

/**
 * DLX result processor that simply discards results without doing anything.
 */
public class NullResultProcessor implements DLXResultProcessor {

	boolean keepGoing = false;

	public NullResultProcessor() {
		// Do nothing
	}

	public NullResultProcessor(boolean keepGoing) {
		this.keepGoing = keepGoing;
	}

	public boolean processResult(DLXResult result) {
		return keepGoing;
	}

}
