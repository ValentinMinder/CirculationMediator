package mediator;

import protocol.Zone2D;

/**
 * Auto-regulated crossing. Attribution: FIFO/right priority.
 */
public class AutoRegulatedMediator extends CirculationMediator {

	public AutoRegulatedMediator(GeneralMediator superMediator, Zone2D zone) {
		super(superMediator, zone);
	}

}
