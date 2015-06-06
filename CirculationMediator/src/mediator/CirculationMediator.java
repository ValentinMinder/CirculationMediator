package mediator;

import protocol.KeepAliveData;
import protocol.Zone2D;
import colleagues.IColleague;

/**
 * 
 * generic circulation mediator (sends message to different colleagues according
 * to the type of mediator/regulation in the sub-classes.)
 *
 */
// TODO: should become abstract.
public class CirculationMediator extends IMediator {

	// reference to the main mediator
	private GeneralMediator generalMediator;
	// reference to my influence zone
	// (where colleagues are under my supervision)
	private Zone2D myInfluenceZone;

	public CirculationMediator(GeneralMediator superMediator, Zone2D zone) {
		super();
		this.generalMediator = superMediator;
		this.myInfluenceZone = zone;
	}

	@Override
	public void keepAlive(IColleague sender, KeepAliveData data) {
		checkZone(sender, data);
		// the keepalive transmission is left to the general mediator.
		generalMediator.broadcastKeepAlive(sender, data);
	}

	@Override
	protected boolean checkZone(IColleague subject, KeepAliveData data) {
		if (data.getZone().isContainedIn(myInfluenceZone)) {
			return true;
		} else {
			// out of my zone: unregister from me and change mediator !
			this.unRegisterColleague(subject);
			subject.registerMediator(generalMediator);
			return false;
		}
	}

	/**
	 * Zone of influence of this mediator.
	 */
	protected Zone2D getZone() {
		return myInfluenceZone;
	}
}
