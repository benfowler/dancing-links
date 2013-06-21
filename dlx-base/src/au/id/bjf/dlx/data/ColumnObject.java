package au.id.bjf.dlx.data;


public class ColumnObject extends DataObject {

	private static final String UNNAMED = "unnamed";

	public int size = 0;
	public Object name = UNNAMED;

	@Override
	public String toString() {
		if (name == UNNAMED)
			return UNNAMED + "_" + super.toString();
		return name.toString();
	}

}
