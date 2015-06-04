package colleagues;

import mediator.IMediator;

/**
 * @university Univesity of Applied Sciences Western Switzerland (HES-SO)
 * @faculty School of Engineering and Business State of Vaud (HEIG-VD)
 * @field MCR Reusable Conception Models - Design Patterns
 * @project Circulation Mediator
 * @date June 18 2015
 * @author Valentin D. Minder
 * @brief A type of concrete colleague, that represent a pedestrian...
 */

public class Pedestrian extends MovingColleague {

	public Pedestrian(IMediator mediator) {
		super(mediator);
	}

	@Override
	public void move() {

	}

	@Override
	public void stop() {

	}

	@Override
	public void start() {

	}

}
