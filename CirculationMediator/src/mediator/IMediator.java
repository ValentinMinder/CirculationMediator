package mediator;

import java.util.ArrayList;

import colleagues.IColleague;

/**
 * @university Univesity of Applied Sciences Western Switzerland (HES-SO)
 * @faculty School of Engineering and Business State of Vaud (HEIG-VD)
 * @field MCR Reusable Conception Models - Design Patterns
 * @project Circulation Mediator
 * @date June 18 2015
 * @author Valentin D. Minder
 * @brief Generic Mediator Interface
 */

public abstract class IMediator {

	protected ArrayList<IColleague> colleagues = new ArrayList<IColleague>();

	public boolean register(IColleague colleague) {
		return colleagues.add(colleague);
	}

	public boolean unRegister(IColleague colleague) {
		return colleagues.remove(colleague);
	}

	public abstract void broadcast(IColleague sender, String payload);

	// TODO complete with other methods
}
