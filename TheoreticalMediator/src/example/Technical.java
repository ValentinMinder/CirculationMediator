package example;

/**
 * @university Univesity of Applied Sciences Western Switzerland (HES-SO)
 * @faculty School of Engineering and Business State of Vaud (HEIG-VD)
 * @field MCR Reusable Conception Models - Design Patterns
 * @project Mediator / Theorical Minimal Exemple
 * @date May 28 2015
 * @author Valentin D. Minder
 * @brief A type of concrete Worker, that represents someone who works (but has
 *        no idea from whom).
 */

public class Technical extends IWorker {

	public Technical(IMediator med) {
		super(med);
	}

	/**
	 * Execute the job (=>repairs the failure).
	 */
	public void repairFailure() {
		System.out.println(getClass().getName() + ": reppaired!");
	}
}
