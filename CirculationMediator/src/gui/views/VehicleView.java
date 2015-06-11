package gui.views;

import colleagues.IColleague;
import colleagues.Vehicle;
import gui.GUICirculation;
import java.awt.Graphics;

public class VehicleView extends View {
    
    private final Vehicle vehicle;

    public VehicleView(GUICirculation parent, Vehicle vehicle) {
        super(parent, vehicle);
        this.vehicle = vehicle;
    }

    @Override
    public void draw(Graphics g) {
    }
}
