package be.swsb.fiazard.ddd;

public abstract class AbstractCommand {

	private int versionOfReadModel;
	
	public AbstractCommand(int versionOfReadModel) {
		this.versionOfReadModel = versionOfReadModel;
	}
}
