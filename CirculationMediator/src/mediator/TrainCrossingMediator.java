package mediator;

import protocol.KeepAliveData;
import protocol.Zone2D;
import colleagues.Barrier;
import colleagues.IColleague;
import colleagues.Train;

/**
 * Train crossing regulated with barriers.
 */
public class TrainCrossingMediator extends CirculationMediator {

	private Barrier rightBarrier = new Barrier(this);
	private Barrier leftBarrier = new Barrier(this);

	public TrainCrossingMediator(GeneralMediator superMediator, Zone2D zone) {
		super(superMediator, zone);
	}

	@Override
	public void keepAlive(IColleague sender, KeepAliveData data) {
		super.keepAlive(sender, data);
		// if the sender will be in the area of the barrier and its closed: stop.
		if (!rightBarrier.isOpen()
				&& sender.getZone().willBeContainedIn(rightBarrier.getZone())) {
			sender.switchMove(true);
		} else if (!leftBarrier.isOpen()
				&& sender.getZone().willBeContainedIn(rightBarrier.getZone())) {
			sender.switchMove(true);
		}
	}

	public boolean registerColleague(Train colleague) {
		trainArrives();
		return super.registerColleague(colleague);
	}

	public boolean unRegisterColleague(Train colleague) {
		trainLeaves();
		return super.unRegisterColleague(colleague);
	}

	private void trainArrives() {
		rightBarrier.switchOpen(false);
		leftBarrier.switchOpen(false);
	}

	private void trainLeaves() {
		rightBarrier.switchOpen(true);
		leftBarrier.switchOpen(true);
	}

}
