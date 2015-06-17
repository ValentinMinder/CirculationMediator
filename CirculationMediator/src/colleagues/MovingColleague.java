package colleagues;

import java.util.Timer;
import java.util.TimerTask;

import mediator.IMediator;
import protocol.KeepAliveData;

/**
 * Created by paulnta on 04.06.15.
 */
public abstract class MovingColleague extends IColleague {

	private static Timer timer = new Timer();
    private boolean isMoving = true;

    public MovingColleague(IMediator med) {
        super(med);
    }

    public void move() {
        if (isMoving) {
            zone.move();
        }
    }
    
    public void notifyMove() {
    	notifyViewObservers();
    }
    
    /**
     * sends a keepalive to the mediator (only for moving colleagues)
     */
    @Override
    public void issueKeepAlive() {
    	super.issueKeepAlive();
        mediator.keepAlive(this, cachedData);
    }
    
    private TimerTask tt = null;

    @Override
    public void receiveKeepAlive(KeepAliveData data) {
        super.receiveKeepAlive(data);
        
        if (!isExploded && data.getZone().isContainedIn(zone)) {
			// woooups... accident!
            // TODO: destroy / stop / images / ...
            System.err.println(this + " - woooups! Accdident! I explode!");
            isExploded = true;
            isMoving = false;
            notifyViewObservers();
        }
        
        if (isSignalisationAware && zone.willBeContainedIn(data.getZone())) {
            // I should stop in order to avoid problems...
            if (isMoving) {
            	System.err.println(this + " - I have someone on my road. I stopped for 2 seconds.");
            }
            stop();
            if (null != tt) {
            	tt.cancel();
            }
            final MovingColleague t = this;
            tt = new TimerTask() {
				
				@Override
				public void run() {
					System.err.println(t.toString() + " - I have waited. I'll try again to move.");
					start();
					
				}
			};
            timer.schedule(tt, 1000 * 2);
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
