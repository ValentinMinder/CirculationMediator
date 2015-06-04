package colleagues;

import mediator.IMediator;

/**
 * @university Univesity of Applied Sciences Western Switzerland (HES-SO)
 * @faculty School of Engineering and Business State of Vaud (HEIG-VD)
 * @field MCR Reusable Conception Models - Design Patterns
 * @project Circulation Mediator
 * @date June 18 2015
 * @author Valentin D. Minder
 * @brief A type of concrete colleague, that represents a moving vehicle.
 */

public class Vehicle extends MovingColleague {

	public Vehicle(IMediator mediator) {
		super(mediator);
	}

	@Override
	public void move() {
		// TO IMPLEMENT
	}

	@Override
	public void stop() {

	}

	@Override
	public void start() {

	}

}
