package protocol;

/**
 * Represent data that are transmitted in a keep-alive message. <br>
 * Only a zone at the moment, but could evoluate.
 *
 */
public class KeepAliveData {
	
	private Zone2D zone;

	public KeepAliveData(Zone2D zone) {
		super();
		this.zone = zone;
	}

	public Zone2D getZone() {
		return zone;
	}

	public void setZone(Zone2D zone) {
		this.zone = zone;
	}

}
