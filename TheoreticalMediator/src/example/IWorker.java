package example;

/**
 * @university Univesity of Applied Sciences Western Switzerland (HES-SO)
 * @faculty School of Engineering and Business State of Vaud (HEIG-VD)
 * @field MCR Reusable Conception Models - Design Patterns
 * @project Mediator / Theorical Minimal Exemple
 * @date May 28 2015
 * @author Valentin D. Minder
 * @brief Generic Abstract Class for Workers
 */
public abstract class IWorker {
	protected IMediator mediator;

	public IWorker(IMediator med) {
		mediator = med;
	}
}
