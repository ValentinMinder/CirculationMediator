package colleagues;

import mediator.IMediator;

/**
 * @university Univesity of Applied Sciences Western Switzerland (HES-SO)
 * @faculty School of Engineering and Business State of Vaud (HEIG-VD)
 * @field MCR Reusable Conception Models - Design Patterns
 * @project Circulation Mediator
 * @date June 18 2015
 * @author Valentin D. Minder
 * @brief Generic Abstract Class for Colleagues
 */
public abstract class IColleague {
	protected IMediator mediator;

	public IColleague(IMediator med) {
		mediator = med;
	}

	public void registerMediator(IMediator med){
		mediator = med;
	}

	public void receive(String simpleMessage){
		System.out.println(this + " recieved: " + simpleMessage);
	}

	public void broadcast(String simpleMessage) {
		System.out.println(this + " sends a broadcast: " + simpleMessage);
		mediator.broadcast(this, simpleMessage);
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
}
