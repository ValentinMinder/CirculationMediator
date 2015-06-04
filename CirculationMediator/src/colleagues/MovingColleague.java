package colleagues;

import mediator.IMediator;

/**
 * Created by paulnta on 04.06.15.
 */
public abstract class MovingColleague extends IColleague {

    public MovingColleague(IMediator med) {
        super(med);
    }

    public abstract void move();

    public abstract void stop();

    public abstract void start();
}
