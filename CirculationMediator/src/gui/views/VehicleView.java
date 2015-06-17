package gui.views;

import colleagues.Vehicle;
import gui.GUICirculation;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class VehicleView extends View {

    private final Vehicle vehicle;

    public VehicleView(GUICirculation parent, Vehicle vehicle) {
        super(parent, vehicle);
        this.vehicle = vehicle;
    }

    @Override
    public void draw(Graphics g) {
        
        BufferedImage i;
        
        if(vehicle.getZone().getDeltaX() > 0)
            i = Images.imgVehicleEast;
        else if(vehicle.getZone().getDeltaX() < 0)
            i = Images.imgVehicleWest;
        else if(vehicle.getZone().getDeltaY() > 0)
            i = Images.imgVehicleSouth;
        else
            i = Images.imgVehicleNorth;
        
        g.drawImage(i,
                vehicle.getX(),
                vehicle.getY(),
                vehicle.getWidth(),
                vehicle.getHeight(),
                null);
        
        if(vehicle.isExploded()) {
            g.drawImage(Images.imgExplosion,
                    vehicle.getX(), 
                    vehicle.getY(), 
                    vehicle.getWidth(), 
                    vehicle.getHeight(), 
                    null);
        }
    }
}
