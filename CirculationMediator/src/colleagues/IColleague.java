package colleagues;

import java.util.Observable;
import java.util.Observer;

import protocol.KeepAliveData;
import protocol.Zone2D;
import mediator.IMediator;

/**
 * @university Univesity of Applied Sciences Western Switzerland (HES-SO)
 * @faculty School of Engineering and Business State of Vaud (HEIG-VD)
 * @field MCR Reusable Conception Models - Design Patterns
 * @project Circulation Mediator
 * @date June 18 2015
 * @author Valentin D. Minder
 * @brief Generic Abstract Class for Colleagues
 */
public abstract class IColleague {

    protected Zone2D zone = new Zone2D(0, 0, 0, 0);
    protected KeepAliveData cachedData = new KeepAliveData(zone);
    protected IMediator mediator;
    protected boolean isExploded = false;

    public boolean isExploded() {
		return isExploded;
	}

	public void setExploded(boolean isExploded) {
		this.isExploded = isExploded;
	}

	// Observable pour les vues
    private final Observable viewObservable = new Observable() {
        @Override
        public void notifyObservers() {
        	super.setChanged();
        	super.notifyObservers();
        }
        
        @Override
        public void notifyObservers(Object arg) {
        	super.setChanged();
        	super.notifyObservers(arg);
        }
    };

    public IColleague(IMediator med) {
        mediator = med;
    }

    public void registerMediator(IMediator med) {
        mediator = med;
    }

    /**
     * sends a keepalive to the mediator (only for moving colleagues)
     */
    public void issueKeepAlive() {
    	// non-moving dont issue keepalive
    }

    public void receiveKeepAlive(KeepAliveData data) {
        // dont care if non-moving...
    }

    public Zone2D getZone() {
        return zone;
    }

    public void setZone(Zone2D zone) {
        cachedData = new KeepAliveData(zone);
        this.zone = zone;
    }

    public void receive(String simpleMessage) {
        System.out.println(this + " recieved: " + simpleMessage);
    }

    public void broadcast(String simpleMessage) {
        System.out.println(this + " sends a broadcast: " + simpleMessage);
        mediator.broadcast(this, simpleMessage);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public void addViewObserver(Observer view) {
        viewObservable.addObserver(view);
    }

    protected void notifyViewObservers() {
        viewObservable.notifyObservers();
    }
    
    protected void notifyViewObservers(Object arg) {
        viewObservable.notifyObservers(arg);
    }

    public void switchMove(boolean stopped) {
        // does nothing.
    }
    
    public int getX() {
        return (int)zone.getCoordX();
    }
    
    public int getY() {
        return (int)zone.getCoordY();
    }
    
    public int getWidth() {
        return (int)zone.getLargeurX();
    }
    
    public int getHeight() {
        return (int)zone.getLargeurY();
    }
}
