package mediator;

import java.util.ArrayList;
import java.util.Collection;

import protocol.KeepAliveData;
import protocol.Zone2D;
import colleagues.IColleague;
import colleagues.Pedestrian;
import colleagues.Vehicle;

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

	IColleague v = new Vehicle(this);
	IColleague p = new Pedestrian(this);

	/* INSTANCIATION */
	public GeneralMediator() {
		super();
		// creation of specialized mediators.
		mediators = new ArrayList<CirculationMediator>();
		mediators.add(new CirculationMediator(this,
				new Zone2D(30.0, 20.0, 8, 9)));
		mediators.add(new CirculationMediator(this,
				new Zone2D(60.0, 40.0, 8, 4)));

		// TODO: colleague must be instanciated with a "zone"
		v = new Vehicle(this);
		p = new Pedestrian(this);
		registerColleague(v);
		registerColleague(p);
		registerColleague(new Vehicle(this));
		registerColleague(new Vehicle(this));
		registerColleague(new Pedestrian(this));
	}

	private void start() throws InterruptedException {
		Thread.sleep(1000);
		v.broadcast("Wroum wroum from " + v);
		Thread.sleep(1000);
		p.broadcast("Pfiuu!! I run away from " + p);
	}

	public static void main(String[] args) {
		try {
			new GeneralMediator().start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
}
