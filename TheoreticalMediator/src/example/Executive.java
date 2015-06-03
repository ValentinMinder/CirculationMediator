package example;

/**
 * @university Univesity of Applied Sciences Western Switzerland (HES-SO)
 * @faculty School of Engineering and Business State of Vaud (HEIG-VD)
 * @field MCR Reusable Conception Models - Design Patterns
 * @project Mediator / Theorical Minimal Exemple
 * @date May 28 2015
 * @author Valentin D. Minder
 * @brief A type of concrete Worker, that represents someone who gives orders
 *        (but have no idea to whom).
 */

public class Executive extends IWorker implements Runnable {

	public Executive(IMediator mediator) {
		super(mediator);
	}

	/**
	 * Finds something wrong and report it to the mediator...
	 */
	public void foundFailure() {
		System.out.println(getClass().getName() + ": reports failure to "
				+ mediator.getClass().getName());
		mediator.repair(this);
	}

	// Executive threaded now finds something wrong every 2 sec...
	@Override
	public void run() {
		while (true) {
			System.out.println(getClass().getName() + ": reports failure to "
					+ mediator.getClass().getName());
			mediator.repair(this);
			try {
				System.out.println(getClass().getName()
						+ ": sleeping for 2 sec...");
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
