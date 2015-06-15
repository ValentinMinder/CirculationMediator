package colleagues;

import java.util.Observable;

import protocol.KeepAliveData;
import mediator.IMediator;

/**
 * Created by paulnta on 04.06.15.
 */
public abstract class MovingColleague extends IColleague {

    private boolean isMoving = true;

    public MovingColleague(IMediator med) {
        super(med);
    }

    public void move() {
        if (isMoving) {
            zone.move();
            notifyViewObservers();
        }
    }

    @Override
    public void receiveKeepAlive(KeepAliveData data) {
        super.receiveKeepAlive(data);
        if (zone.willBeContainedIn(data.getZone())) {
            // I should stop in order to avoid problems...
            stop();
            System.out.println("I have someone on my road. I stopped.");
            // TODO: program the re-start in a while if no other stop.
        }
    }

    public void stop() {
        isMoving = false;
    }

    public void start() {
        isMoving = true;
    }

    @Override
    public void switchMove(boolean stopped) {
        if (stopped) {
            stop();
        } else {
            move();
        }
    }
}
