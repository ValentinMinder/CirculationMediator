package example;

/**
 * @university Univesity of Applied Sciences Western Switzerland (HES-SO)
 * @faculty School of Engineering and Business State of Vaud (HEIG-VD)
 * @field MCR Reusable Conception Models - Design Patterns
 * @project Mediator / Theorical Minimal Exemple
 * @date May 28 2015
 * @author Valentin D. Minder
 * @brief Concrete Mediator (only one in this project)
 */

public class Mediator implements IMediator {

	/*
	 * In this example, the mediator instanciate and knows all the workers... In
	 * the general case, it's at least supposed to be aware of them... Another
	 * way would be workers that register themselves to the mediator (or to an
	 * abstract mediator or even a load balancer)... Think for example of a Sky
	 * Control Central, where Planes from all types register to one of the
	 * conrete instance of the Central (eg, that could be split up among
	 * regions)
	 */

	// a technician
	private Technical tech;
	// an executive
	private Executive exec;

	// start of the project
	public static void main(String[] args) {
		new Mediator().start();
	}

	/* INSTANCIATION */
	public Mediator() {
		// the mediator instanciate the workers with a reference to him
		tech = new Technical(this);
		exec = new Executive(this);

		// logs
		System.out.println(getClass().getName() + " instanciate "
				+ exec.getClass().getName() + " and "
				+ tech.getClass().getName() + " with mediator reference");
	}

	public void start() {
		// the executive finds a techical failure.
		System.out.println(getClass().getName()
				+ " single starts the executive... ");
		exec.foundFailure();

		// the executive is threaded... such that it runs on itself
		System.out.println(getClass().getName() + " threads the executive... ");
		new Thread(exec).start();
	}

	/**
	 * PROTOTOCOL <br>
	 * When a technical failure is found (it can be by any worker), then a
	 * technician is called to repaire the problem.
	 * */
	@Override
	public void repair(IWorker origin) {
		System.out.println(getClass().getName() + ": request from:"
				+ origin.getClass().getName() + " transfered to "
				+ tech.getClass().getName());
		tech.repairFailure();
	}
}
