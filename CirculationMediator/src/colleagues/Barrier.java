package colleagues;

import mediator.IMediator;

/**
 * Created by paulnta on 04.06.15.
 */
public class Barrier extends NonMovingColleague {

    // tells if the barrier is open.

    private boolean isOpen = true;

    public Barrier(IMediator med) {
        super(med);
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void switchOpen(boolean isOpen) {
        this.isOpen = isOpen;
        // stop or restart everyone!
        mediator.switchMoveEveryOneInArea(this, zone, !isOpen);
    }

    @Override
    public void receive(String simpleMessage) {

    }
}
