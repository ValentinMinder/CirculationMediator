package mediator;

import gui.Controller;

import java.util.ArrayList;
import java.util.Collection;

import protocol.KeepAliveData;
import protocol.Zone2D;
import colleagues.IColleague;
import colleagues.NonMovingColleague;

/**
 * @university Univesity of Applied Sciences Western Switzerland (HES-SO)
 * @faculty School of Engineering and Business State of Vaud (HEIG-VD)
 * @field MCR Reusable Conception Models - Design Patterns
 * @project Circulation Mediator
 * @date June 18 2015
 * @author Valentin D. Minder
 * @brief Concrete Mediator
 */

public class GeneralMediator extends IMediator {
	// list of mediator
	private Collection<CirculationMediator> mediators;

	/* SINGLETON */
	private static GeneralMediator instance;
	public static GeneralMediator getInstance () {
		if (null == instance) {
			synchronized (Controller.class) {
				if (null == instance) {
					instance = new GeneralMediator();
				}
			}
		}
		return instance;
	}
	/* INSTANCIATION */
	private GeneralMediator() {
		super();
		// creation of specialized mediators.
		mediators = new ArrayList<CirculationMediator>();
	}
	
	public void addMediator (CirculationMediator mediator) {
		mediators.add(mediator);
	}

	@Override
	public void keepAlive(IColleague sender, KeepAliveData data) {
		checkZone(sender, data);
		broadcastKeepAlive(sender, data);
	}

	/**
	 * Broadcast the keep-alive message to ALL colleagues, no matter their
	 * current mediator.
	 * 
	 * @param sender
	 *            the sender
	 * @param data
	 *            keep-alive data.
	 */
	protected void broadcastKeepAlive(IColleague sender, KeepAliveData data) {
		// broadcast KeepAlive
		for (IColleague iColleague : colleagues) {
			if (iColleague != sender) { // comparaison de référence ok!
				iColleague.receiveKeepAlive(data);
			}
		}
	}

	@Override
	protected boolean checkZone(IColleague colleague, KeepAliveData data) {
		for (CirculationMediator mediator : mediators) {
			if (data.getZone().isContainedIn(mediator.getZone())) {
				// new mediator for subject
				colleague.registerMediator(mediator);
				// specialized mediator has a new subject
				mediator.registerColleague(colleague);
				return false;
			}
		}
		return true;
	}

	@Override
	public void switchMoveEveryOneInArea(NonMovingColleague sender,
			Zone2D area, boolean stop) {
		for (IColleague iColleague : colleagues) {
			if (iColleague != sender
					&& iColleague.getZone().isContainedIn(area)) {
				iColleague.switchMove(stop);
			}
		}
	}
}
