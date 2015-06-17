package mediator;

import java.util.Collection;
import java.util.LinkedList;

import colleagues.IColleague;
import colleagues.TrafficLight;
import protocol.KeepAliveData;
import protocol.Zone2D;

/**
 * Traffic Light crossing, regulated with lights.
 */
public class TrafficLightMediator extends CirculationMediator {

	private Collection<TrafficLight> lights;

	public TrafficLightMediator(GeneralMediator superMediator, Zone2D zone) {
		super(superMediator, zone);
		lights = new LinkedList<TrafficLight>();
	}
	
	@Override
	public void keepAlive(IColleague sender, KeepAliveData data) {
		super.keepAlive(sender, data);
		// if the sender is in the area of a traffic light...
		for (TrafficLight light : lights) {
			if (data.getZone().isContainedIn(light.getZone())) {
				requestGreen(light);
				return;
			}
		}
	}
	
	public void addTrafficLight(TrafficLight light) {
		lights.add(light);
	}
	
	private void makeOtherRed (TrafficLight otherLight ){
		for (TrafficLight light : lights) {
			if (light != otherLight) {
				light.red();
			}
		}
	}

	/**
	 * Request the green. If successful.
	 */
	public void requestGreen(TrafficLight otherLight) {
		
		if (otherLight.isGreen()) {
			// okay, already green, dont change ! 
			// just make sure everyone else is red.
			makeOtherRed(otherLight);
			otherLight.green();
		} else {
			// request processing: orange.
			otherLight.orange();
			for (TrafficLight light : lights) {
				if (light != otherLight) {
					if (light.isGreen()) {
						// at least one other not okay, goes to red.
						otherLight.red();
						return;
					}
				}
			}
			// okay, change green, just make others red.
			makeOtherRed(otherLight);
			otherLight.green();
		}
	}

}
