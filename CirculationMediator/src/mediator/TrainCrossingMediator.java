package mediator;

import colleagues.Barrier;
import protocol.Zone2D;

/**
 * Train crossing regulated with barriers.
 */
public class TrainCrossingMediator extends CirculationMediator {

	private Barrier rightBarrier;
	private Barrier leftBarrier;

	public TrainCrossingMediator(GeneralMediator superMediator, Zone2D zone) {
		super(superMediator, zone);
	}

	public void trainArrives() {
		// TODO: fermer les barrieres.
	}

	public void barrierIsClosed() {
		// TODO: stopper tout le monde!
	}

	public void barrierIsOpen() {
		// TODO: demarrer tout le monde!
	}

	public void trainLeaves() {
		// TODO: ouvrir les barrieres
	}

}
