package colleagues;

import java.util.Timer;
import java.util.TimerTask;

import mediator.IMediator;

/**
 * Created by paulnta on 04.06.15.
 */
public class TrafficLight extends NonMovingColleague {
	private Timer timer = new Timer();
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

        private TimerTask tt;
	public void green() {
		if (current != State.GREEN) {
			// on redémarre tout le monde dans la zone.
			mediator.switchMoveEveryOneInArea(this, zone, false);
		}
		current = State.GREEN;
		if (tt != null) {
			tt.cancel();
		}
		tt = new TimerTask() {
			
			@Override
			public void run() {
				orange();
			}
		};
		timer.schedule(tt, 1000 * 5); // green for 5 seconds
	}

	public void red() {
		if (tt != null) {
			tt.cancel();
		}
		current = State.RED;
		mediator.switchMoveEveryOneInArea(this, zone, true);
	}

	public void orange() {
		current = State.ORANGE;
		// we stop people by security
		mediator.switchMoveEveryOneInArea(this, zone, true);
	}

}
