package be.swsb.fiazard.ddd;

public abstract class AbstractCommand {

	protected static final int VERSION_FOR_NEW_AGGREGATE = 0;
	
	private int versionOfReadModel;
	
	public AbstractCommand(int versionOfReadModel) {
		this.versionOfReadModel = versionOfReadModel;
	}
}
