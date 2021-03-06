package gui;

import gui.views.*;

import java.util.LinkedList;
import java.util.List;

import mediator.*;
import protocol.Zone2D;
import colleagues.*;

public class Controller implements Runnable {

	private final List<MovingColleague> listColleague = new LinkedList<>();
	private final List<View> listViews = new LinkedList<>();

	public List<View> getListViews() {
		return listViews;
	}

	/* SINGLETON */
	private static Controller instance;

	public static Controller getInstance(GUICirculation gui) {
		if (null == instance) {
			synchronized (Controller.class) {
				if (null == instance) {
					instance = new Controller(gui);
				}
			}
		}
		return instance;
	}

	private Controller(GUICirculation gui) {
		// mediateur principal
		GeneralMediator med = GeneralMediator.getInstance();

		// sucre syntaxique (simplification)
		double w = BackgroundImagePanel.BACKGROUND_WIDTH;
		double h = BackgroundImagePanel.BACKGROUND_HEIGTH;

		/* MEDIATORS */

		AutoRegulatedMediator autoMed = new AutoRegulatedMediator(med,
				new Zone2D(2 * w / 6, 4 * h / 9, h / 9, h / 9));
		med.addMediator(autoMed);

		TrafficLightMediator lightMed = new TrafficLightMediator(med,
				new Zone2D(4 * w / 6 - 6* h/9/3, 4 * h / 9 - 3 * h / 9
						/ 3, h / 3, h / 3));
		med.addMediator(lightMed);

		TrainCrossingMediator trainMed = new TrainCrossingMediator(med,
				new Zone2D(9 * w / 20, 4 * h / 9, h / 9, h / 9));
		med.addMediator(trainMed);

		/* TRAFFIC LIGHTS */

		TrafficLight traffic;
		View v;

		// right
		traffic = new TrafficLight(lightMed);
		traffic.setZone(new Zone2D(4 * w / 6 - 13, 4 * h / 9 + 5*h/9/3, h / 9 / 2,
				h / 9/4));
		v = new TrafficLightView(gui, traffic);
		listViews.add(v);
		lightMed.addTrafficLight(traffic);

		// up
		traffic = new TrafficLight(lightMed);
		traffic.setZone(new Zone2D(4 * w / 6 - 2 * h / 9 / 3 - 8, 4 * h / 9 - 10, h / 9 / 2, h / 9/4));
		v = new TrafficLightView(gui, traffic);
		listViews.add(v);
		lightMed.addTrafficLight(traffic);

		// left
		traffic = new TrafficLight(lightMed);
		traffic.setZone(new Zone2D(4 * w / 6 - 4 * h/9/3, 4 * h / 9 + 3 * h / 9 / 3 - 7, h / 9/4 ,
				h / 9 / 2));
		v = new TrafficLightView(gui, traffic);
		listViews.add(v);
		lightMed.addTrafficLight(traffic);
		
		// down
		traffic = new TrafficLight(lightMed);
		traffic.setZone(new Zone2D(4 * w / 6 +  h / 9 / 3, 4 * h / 9 + h / 9
				/ 3 - 7, h / 9/4, h / 9 / 2));
		v = new TrafficLightView(gui, traffic);
		listViews.add(v);
		lightMed.addTrafficLight(traffic);
		
		/* VEHICLES & OTHER MOVING */

		MovingColleague c;

		/* trains */
		c = new Train(med);
		c.setZone(new Zone2D(w / 2 - w/100, h, 20, 100, 0, -10));
		listColleague.add(c);
		v = new TrainView(gui, (Train) c);
		listViews.add(v);
		
		c = new Train(med);
		c.setZone(new Zone2D(w / 2 - w/100, h/2, 20, 100, 0, -10));
		listColleague.add(c);
		v = new TrainView(gui, (Train) c);
		listViews.add(v);

		c = new Train(med);
		c.setZone(new Zone2D(w / 2 - 3 * w / 100, -10, 20, 100, 0, 10));
		listColleague.add(c);
		v = new TrainView(gui, (Train) c);
		listViews.add(v);
		
		c = new Train(med);
		c.setZone(new Zone2D(w / 2 - 3 * w / 100, h/2, 20, 100, 0, 10));
		listColleague.add(c);
		v = new TrainView(gui, (Train) c);
		listViews.add(v);

		/* horizontal vehciles */ 
		
		c = new Vehicle(med);
		c.setZone(new Zone2D(-20, h / 2 + 30, 50, 30, 5, 0));
		listColleague.add(c);
		v = new VehicleView(gui, (Vehicle) c);
		listViews.add(v);		
		
		c = new Vehicle(med);
		c.setZone(new Zone2D(w/2 - 130, h / 2 + 30, 50, 30, 4, 0));
		listColleague.add(c);
		v = new VehicleView(gui, (Vehicle) c);
		listViews.add(v);
		
		c = new Vehicle(med);
		c.setZone(new Zone2D(5* w/6, h / 2 + 30, 50, 30, 3, 0));
		listColleague.add(c);
		v = new VehicleView(gui, (Vehicle) c);
		listViews.add(v);

		c = new Vehicle(med);
		c.setZone(new Zone2D(w + 20, h / 2 - 15, 50, 30, -5, 0));
		listColleague.add(c);
		v = new VehicleView(gui, (Vehicle) c);
		listViews.add(v);
		
		c = new Vehicle(med);
		c.setZone(new Zone2D(w/6, h / 2 - 15, 50, 30, -4, 0));
		listColleague.add(c);
		v = new VehicleView(gui, (Vehicle) c);
		listViews.add(v);
		
		c = new Vehicle(med);
		c.setZone(new Zone2D(w/2 - 130, h / 2 - 15, 50, 30, -5, 0));
		listColleague.add(c);
		v = new VehicleView(gui, (Vehicle) c);
		listViews.add(v);
		
		c = new Vehicle(med);
		c.setZone(new Zone2D(5 * w / 6, h / 2 - 15, 50, 30, -3, 0));
		listColleague.add(c);
		v = new VehicleView(gui, (Vehicle) c);
		listViews.add(v);

		/* vertical vehicles on the left */ 
		c = new Vehicle(med);
		c.setZone(new Zone2D(w/3 - 10, h / 6, 30, 50, 0, -3));
		listColleague.add(c);
		v = new VehicleView(gui, (Vehicle) c);
		listViews.add(v);
		
		c = new Vehicle(med);
		c.setZone(new Zone2D(w/3 - 10, 5 * h / 6, 30, 50, 0, -10));
		listColleague.add(c);
		v = new VehicleView(gui, (Vehicle) c);
		listViews.add(v);
		
		c = new Vehicle(med);
		c.setZone(new Zone2D(w/3 - 10, h + 20, 30, 50, 0, -6));
		listColleague.add(c);
		v = new VehicleView(gui, (Vehicle) c);
		listViews.add(v);
		
		c = new Vehicle(med);
		c.setZone(new Zone2D(w/3 - 50, -20, 30, 50, 0, 6));
		listColleague.add(c);
		v = new VehicleView(gui, (Vehicle) c);
		listViews.add(v);
		
		c = new Vehicle(med);
		c.setZone(new Zone2D(w/3 - 50, h/3 - 100, 30, 50, 0, 6));
		listColleague.add(c);
		v = new VehicleView(gui, (Vehicle) c);
		listViews.add(v);
		
		c = new Vehicle(med);
		c.setZone(new Zone2D(w/3 - 50, 2*h/3 + 150, 30, 50, 0, 6));
		listColleague.add(c);
		v = new VehicleView(gui, (Vehicle) c);
		listViews.add(v);
		
		/* vertical vehicle on the right */
		
		c = new Vehicle(med);
		c.setZone(new Zone2D(2*w/3 - 10, h / 3 - 150, 30, 50, 0, -6));
		listColleague.add(c);
		v = new VehicleView(gui, (Vehicle) c);
		listViews.add(v);
		
		c = new Vehicle(med);
		c.setZone(new Zone2D(2*w/3 - 10, 2*h/3 + 150, 30, 50, 0, -6));
		listColleague.add(c);
		v = new VehicleView(gui, (Vehicle) c);
		listViews.add(v);
		
		c = new Vehicle(med);
		c.setZone(new Zone2D(2*w/3 - 50, h/3 - 160, 30, 50, 0, 6));
		listColleague.add(c);
		v = new VehicleView(gui, (Vehicle) c);
		listViews.add(v);
		
		c = new Vehicle(med);
		c.setZone(new Zone2D(2*w/3 - 50, 2*h/3 + 150, 30, 50, 0, 6));
		listColleague.add(c);
		v = new VehicleView(gui, (Vehicle) c);
		listViews.add(v);

		/* first series of pedestrian on the right */
		
		c = new Pedestrian(med);
		c.setZone(new Zone2D(5 * w / 6 - 10, h, 25, 50, 0, -2.0));
		listColleague.add(c);
		v = new PedestrianView(gui, (Pedestrian) c);
		listViews.add(v);
		
		c = new Pedestrian(med);
		c.setZone(new Zone2D(5 * w / 6 - 10, h/4, 25, 50, 0, -2.0));
		listColleague.add(c);
		v = new PedestrianView(gui, (Pedestrian) c);
		listViews.add(v);
		
		c = new Pedestrian(med);
		c.setZone(new Zone2D(5 * w / 6 - 10, 3*h/4, 25, 50, 0, -2.0));
		listColleague.add(c);
		v = new PedestrianView(gui, (Pedestrian) c);
		listViews.add(v);
		
		c = new Pedestrian(med);
		c.setZone(new Zone2D(5 * w / 6 - 50, 3*h/4, 25, 50, 0, 2.0));
		listColleague.add(c);
		v = new PedestrianView(gui, (Pedestrian) c);
		listViews.add(v);
		
		c = new Pedestrian(med);
		c.setZone(new Zone2D(5 * w / 6 - 50, h/4, 25, 50, 0, 2.0));
		listColleague.add(c);
		v = new PedestrianView(gui, (Pedestrian) c);
		listViews.add(v);
		
		c = new Pedestrian(med);
		c.setZone(new Zone2D(5 * w / 6 - 50, h, 25, 50, 0, 2.0));
		listColleague.add(c);
		v = new PedestrianView(gui, (Pedestrian) c);
		listViews.add(v);
		
		/* seconde series of pedestrian on the left */
		c = new Pedestrian(med);
		c.setZone(new Zone2D(w / 6 - 60, h, 25, 50, 0, 2.0));
		listColleague.add(c);
		v = new PedestrianView(gui, (Pedestrian) c);
		listViews.add(v);
		
		c = new Pedestrian(med);
		c.setZone(new Zone2D(w / 6 - 60, h/4, 25, 50, 0, 2.0));
		listColleague.add(c);
		v = new PedestrianView(gui, (Pedestrian) c);
		listViews.add(v);
		
		c = new Pedestrian(med);
		c.setZone(new Zone2D(w / 6 - 60, 3*h/4, 25, 50, 0, 2.0));
		listColleague.add(c);
		v = new PedestrianView(gui, (Pedestrian) c);
		listViews.add(v);
		
		c = new Pedestrian(med);
		c.setZone(new Zone2D(w / 6 - 20, h, 25, 50, 0, -2.0));
		listColleague.add(c);
		v = new PedestrianView(gui, (Pedestrian) c);
		listViews.add(v);
		
		c = new Pedestrian(med);
		c.setZone(new Zone2D(w / 6 - 20, h/4, 25, 50, 0, -2.0));
		listColleague.add(c);
		v = new PedestrianView(gui, (Pedestrian) c);
		listViews.add(v);
		
		c = new Pedestrian(med);
		c.setZone(new Zone2D(w / 6 - 20, 3*h/4, 25, 50, 0, -2.0));
		listColleague.add(c);
		v = new PedestrianView(gui, (Pedestrian) c);
		listViews.add(v);

		// TODO: add barrieres +
		// zones de contact pour priorité droite et passage piéton.
		
		// register des collegues
		for (MovingColleague movingColleague : listColleague) {
			med.registerColleague(movingColleague);
		}
	}

	// refresh des figures qui avancent
	@Override
	public void run() {
		long interval = 200; // target: 40 = 1/25 sec
		while (true) {
			for (MovingColleague movingColleague : listColleague) {
				movingColleague.issueKeepAlive();
			}
			
			for (MovingColleague movingColleague : listColleague) {
				movingColleague.move();
			}
			listColleague.get(0).notifyMove();
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
                        
                        
		}

	}

}
