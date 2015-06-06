
package gui.views;

import colleagues.TrafficLight;
import gui.GUICirculation;
import java.awt.Graphics;

public class TrafficLightView extends View {
    private final TrafficLight trafficLight;

    public TrafficLightView(GUICirculation parent, TrafficLight trafficLight) {
        super(parent, trafficLight);
        this.trafficLight = trafficLight;
    }

    @Override
    public void draw(Graphics g) {
    }
}
