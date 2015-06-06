package mediator;

import java.util.ArrayList;
import java.util.Collection;

import protocol.KeepAliveData;
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

	// Collection of colleagues that are attached to this mediator
	protected Collection<IColleague> colleagues = new ArrayList<IColleague>();

	/**
	 * Add a new colleague that will recieve message from this mediator.
	 * 
	 * @param colleague
	 *            the colleague to add.
	 */
	public boolean registerColleague(IColleague colleague) {
		return colleagues.add(colleague);
	}

	/**
	 * Remove a colleague that will no longer recieve message from this
	 * mediator.
	 * 
	 * @param colleague
	 *            the colleague to add.
	 */
	public boolean unRegisterColleague(IColleague colleague) {
		return colleagues.remove(colleague);
	}

	/**
	 * Broadcast test method with a simple String.
	 * 
	 * @param sender
	 *            the sender
	 * @param payload
	 *            the message
	 */
	public void broadcast(IColleague sender, String payload) {
		for (IColleague colleague : colleagues) {
			if (colleague != sender) {
				colleague.receive(payload);
			}
		}
	}

	/**
	 * The sender colleague want to share its keepAliveData with everyone. At
	 * the same time, it will be checked in which
	 * 
	 * @param sender
	 *            the sender
	 * @param data
	 *            its keep-alive data (eg position,...)
	 */
	public abstract void keepAlive(IColleague sender, KeepAliveData data);

	/**
	 * Checks if a subject has changed zone (false) or is in the right zone
	 * (true). Will change mediator of the colleague accordingly.
	 * 
	 * @param colleague
	 *            the colleague
	 * @param data
	 *            its keep-alive data.
	 * @return true if in correct zone, false if changed (has operated a change)
	 */
	protected abstract boolean checkZone(IColleague colleague,
			KeepAliveData data);

	// TODO complete with other methods
}
