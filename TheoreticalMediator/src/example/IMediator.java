package example;

/**
 * @university Univesity of Applied Sciences Western Switzerland (HES-SO)
 * @faculty School of Engineering and Business State of Vaud (HEIG-VD)
 * @field MCR Reusable Conception Models - Design Patterns
 * @project Mediator / Theorical Minimal Exemple
 * @date May 28 2015
 * @author Valentin D. Minder
 * @brief Generic Mediator Interface (only one concrete in this project)
 */

public interface IMediator {
	void repair(IWorker origin);
}
