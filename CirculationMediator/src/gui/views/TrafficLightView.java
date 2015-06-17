
package gui.views;

import colleagues.TrafficLight;
import gui.GUICirculation;
import java.awt.Color;
import java.awt.Graphics;

public class TrafficLightView extends View {
    private final TrafficLight trafficLight;

    public TrafficLightView(GUICirculation parent, TrafficLight trafficLight) {
        super(parent, trafficLight);
        this.trafficLight = trafficLight;
    }

    @Override
    public void draw(Graphics g) {
        if(trafficLight.isGreen())
            g.setColor(Color.green);
        else if(trafficLight.isRed())
            g.setColor(Color.red);
        else
            g.setColor(Color.orange);
        
        g.fillRect(
                trafficLight.getX(), 
                trafficLight.getY(), 
                trafficLight.getWidth(), 
                trafficLight.getHeight());
    }
}
