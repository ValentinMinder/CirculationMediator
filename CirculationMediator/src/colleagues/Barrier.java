package colleagues;

import mediator.IMediator;

/**
 * Created by paulnta on 04.06.15.
 */
public class Barrier extends NonMovingColleague {

    public Barrier(IMediator med) {
        super(med);
    }

    @Override
    public void receive(String simpleMessage) {

    }
}
