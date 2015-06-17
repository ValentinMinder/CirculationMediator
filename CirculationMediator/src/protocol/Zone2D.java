package protocol;

/**
 * Represent a 2D zone with coordinates, and moving vector.
 *
 */
public class Zone2D {

	// positition (du coin superieur gauche)
	double coordX;
	double coordY;

	// emprise sur le terrain
	double largeurX;
	double largeurY;

	// vecteur de déplacement
	double deltaX = 0.0;
	double deltaY = 0.0;

	/**
	 * Construct a Zone2D with given parameters, leaving move vector to default
	 * 0.0.
	 */
	public Zone2D(double coordX, double coordY, double largeurX, double largeurY) {
		super();
		this.coordX = coordX;
		this.coordY = coordY;
		this.largeurX = largeurX;
		this.largeurY = largeurY;
	}

	/**
	 * Construct a Zone2D with given parameters with a move vector.
	 */
	public Zone2D(double coordX, double coordY, double largeurX,
			double largeurY, double deltaX, double deltaY) {
		super();
		this.coordX = coordX;
		this.coordY = coordY;
		this.largeurX = largeurX;
		this.largeurY = largeurY;
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}

	/**
	 * Checks if this zone overlaps this other zone.
	 * 
	 * @param other other zone to check
	 * @return
	 */
	public boolean isContainedIn(Zone2D other) {
		if (coordX <= other.coordX + other.largeurX
				&& coordX + largeurX >= other.coordX
				&& coordY <= other.coordY + other.largeurY
				&& coordY + largeurY >= other.coordY) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if this zone will overlap this other zone after moving (including
	 * its current position). That is, it checks the whole zone from now to
	 * after the moving.
	 * 
	 * @param other other zone to check
	 * @return
	 */
	public boolean willBeContainedIn(Zone2D other) {
		double nextCoordX = coordX;
		double nextCoordY = coordY;
		double nextLargeurX = largeurX;
		double nextLargeurY = largeurY;

		if (deltaX < 0) {
			nextCoordX += deltaX;
		} else {
			nextLargeurX += deltaX;
		}

		if (deltaY < 0) {
			nextCoordY += deltaY;
		} else {
			nextLargeurY += deltaY;
		}
		
		double nextCoordXOTHER = other.coordX;
		double nextCoordYOTHER = other.coordY;
		double nextLargeurXOTHER = other.largeurX;
		double nextLargeurYOTHER = other.largeurY;
		
		if (other.deltaX < 0) {
			nextCoordXOTHER += other.deltaX;
		} else {
			nextLargeurXOTHER += other.deltaX;
		}

		if (other.deltaY < 0) {
			nextCoordYOTHER += other.deltaY;
		} else {
			nextLargeurYOTHER += other.deltaY;
		}

		if (nextCoordX <= nextCoordXOTHER + nextLargeurXOTHER
				&& nextCoordX + nextLargeurX >= nextCoordXOTHER
				&& nextCoordY <= nextCoordYOTHER + nextLargeurYOTHER
				&& nextCoordY + nextLargeurY >= nextCoordYOTHER) {
			return true;
		}
		return false;
	}

	/**
	 * Move this zone with its direction (without any checking).
	 */
	public void move() {
		coordX += deltaX;
		coordY += deltaY;
	}

	/* GETTERS & SETTERS */
	public double getCoordX() {
		return coordX;
	}

	public void setCoordX(double coordX) {
		this.coordX = coordX;
	}

	public double getCoordY() {
		return coordY;
	}

	public void setCoordY(double coordY) {
		this.coordY = coordY;
	}

	public double getLargeurX() {
		return largeurX;
	}

	public void setLargeurX(double largeurX) {
		this.largeurX = largeurX;
	}

	public double getLargeurY() {
		return largeurY;
	}

	public void setLargeurY(double largeurY) {
		this.largeurY = largeurY;
	}
}
