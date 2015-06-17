package colleagues;

import mediator.IMediator;

/**
 * Created by paulnta on 04.06.15.
 */
public class TrafficLight extends NonMovingColleague {
	public enum State { GREEN, ORANGE, RED}
	// stores if a traffic light is green, orange or red.
	private State current = State.ORANGE;

    public TrafficLight(IMediator med) {
        super(med);
    }
    
	public boolean isGreen() {
		return current == State.GREEN;
	}
        
        public boolean isOrange() {
            return current == State.ORANGE;
        }
        
        public boolean isRed() {
            return current == State.RED;
        }

	public void green() {
		current = State.GREEN;
		mediator.switchMoveEveryOneInArea(this, zone, false);
	}

	public void red() {
		current = State.RED;
		mediator.switchMoveEveryOneInArea(this, zone, true);
	}

	public void orange() {
		current = State.ORANGE;
		// we stop people by security
		mediator.switchMoveEveryOneInArea(this, zone, true);
	}

}
