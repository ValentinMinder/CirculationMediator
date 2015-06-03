package mediator;

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

public class Mediator extends IMediator {

	IColleague v = new Vehicle(this);
	IColleague p = new Pedestrian(this);

	/* INSTANCIATION */
	public Mediator() {
		super();
		v = new Vehicle(this);
		p = new Pedestrian(this);
		register(v);
		register(p);
		register(new Vehicle(this));
		register(new Vehicle(this));
		register(new Pedestrian(this));
	}

	private void start() throws InterruptedException {
		Thread.sleep(1000);
		v.broadcast("Wroum wroum from " + v);
		Thread.sleep(1000);
		p.broadcast("Pfiuu!! I run away from " + p);
	}

	public static void main(String[] args) {
		try {
			new Mediator().start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void broadcast(IColleague sender, String payload) {
		for (IColleague colleague : colleagues) {
			if (colleague != sender) {
				colleague.receive(payload);
			}
		}
	}
}
