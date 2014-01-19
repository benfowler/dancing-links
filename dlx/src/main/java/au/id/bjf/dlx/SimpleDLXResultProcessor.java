package au.id.bjf.dlx;

public class SimpleDLXResultProcessor implements DLXResultProcessor {

	public boolean processResult(DLXResult result) {
		System.out.println(result.toString());
		return true;
	}

}
