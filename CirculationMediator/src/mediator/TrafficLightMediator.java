package mediator;

import java.util.Collection;

import colleagues.TrafficLight;
import protocol.Zone2D;

/**
 * Traffic Light crossing, regulated with lights.
 */
public class TrafficLightMediator extends CirculationMediator {

	private Collection<TrafficLight> lights;

	public TrafficLightMediator(GeneralMediator superMediator, Zone2D zone) {
		super(superMediator, zone);
	}

	/**
	 * Request the green. If successful.
	 */
	public void requestGreen(TrafficLight otherLight) {
		for (TrafficLight light : lights) {
			if (light != otherLight) {
					// not okay, dont change
			}
		}
		// okay, change grren
	}

}
