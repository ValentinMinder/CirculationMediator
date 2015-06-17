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
				new Zone2D(4 * w / 6, 4 * h / 9, h / 9, h / 9));
		med.addMediator(lightMed);

		TrainCrossingMediator trainMed = new TrainCrossingMediator(med,
				new Zone2D(9 * w / 20, 4 * h / 9, h / 9, h / 9));
		med.addMediator(trainMed);

		/* TRAFFIC LIGHTS */

		TrafficLight traffic;
		View v;

		traffic = new TrafficLight(lightMed);
		traffic.setZone(new Zone2D(4 * w / 6 + h / 9 / 3, 4 * h / 9, h / 9 / 3,
				h / 9 / 3));
		v = new TrafficLightView(gui, traffic);
		listViews.add(v);
		med.registerColleague(traffic);

		traffic = new TrafficLight(lightMed);
		traffic.setZone(new Zone2D(4 * w / 6 + h / 9 / 3, 4 * h / 9 + 2 * h / 9
				/ 3, h / 9 / 3, h / 9 / 3));
		v = new TrafficLightView(gui, traffic);
		listViews.add(v);
		med.registerColleague(traffic);

		traffic = new TrafficLight(lightMed);
		traffic.setZone(new Zone2D(4 * w / 6, 4 * h / 9 + h / 9 / 3, h / 9 / 3,
				h / 9 / 3));
		v = new TrafficLightView(gui, traffic);
		listViews.add(v);
		med.registerColleague(traffic);

		traffic = new TrafficLight(lightMed);
		traffic.setZone(new Zone2D(4 * w / 6 + 2 * h / 9 / 3, 4 * h / 9 + h / 9
				/ 3, h / 9 / 3, h / 9 / 3));
		v = new TrafficLightView(gui, traffic);
		listViews.add(v);
		med.registerColleague(traffic);

		/* VEHICLES & OTHER MOVING */

		MovingColleague c;

		c = new Train(med);
		c.setZone(new Zone2D(w / 2 - w/100, h, 20, 100, 0, -10));
		listColleague.add(c);
		v = new TrainView(gui, (Train) c);
		listViews.add(v);

		c = new Train(med);
		c.setZone(new Zone2D(w / 2 - 3* w / 100, -100, 20, 100, 0, 10));
		listColleague.add(c);
		v = new TrainView(gui, (Train) c);
		listViews.add(v);

		c = new Vehicle(med);
		c.setZone(new Zone2D(-10, h / 2 + 30, 50, 30, 3, 0));
		listColleague.add(c);
		v = new VehicleView(gui, (Vehicle) c);
		listViews.add(v);

		c = new Vehicle(med);
		c.setZone(new Zone2D(w + 10, h / 2 - 15, 50, 30, -3, 0));
		listColleague.add(c);
		v = new VehicleView(gui, (Vehicle) c);
		listViews.add(v);

		c = new Vehicle(med);
		listColleague.add(c);
		v = new VehicleView(gui, (Vehicle) c);
		listViews.add(v);

		c = new Pedestrian(med);
		c.setZone(new Zone2D(5 * w / 6, h, 25, 50, 0, -2.5));
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
