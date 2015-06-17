package gui.views;

import colleagues.IColleague;
import colleagues.Vehicle;
import gui.GUICirculation;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class VehicleView extends View {

    private final Vehicle vehicle;
    private static BufferedImage imgVehicleNorth;
    private static BufferedImage imgVehicleEast;
    private static BufferedImage imgVehicleSouth;
    private static BufferedImage imgVehicleWest;

    static {
        try {
            imgVehicleNorth = ImageIO.read(new File("imgVehicleNorth.png"));
            imgVehicleEast = ImageIO.read(new File("imgVehicleEast.png"));
            imgVehicleWest = ImageIO.read(new File("imgVehicleWest.png"));
            imgVehicleSouth = ImageIO.read(new File("imgVehicleSouth.png"));
        } catch (IOException ex) {
            Logger.getLogger(PedestrianView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public VehicleView(GUICirculation parent, Vehicle vehicle) {
        super(parent, vehicle);
        this.vehicle = vehicle;
    }

    @Override
    public void draw(Graphics g) {
        
        BufferedImage i;
        
        if(vehicle.getZone().getDeltaX() > 0)
            i = imgVehicleEast;
        else if(vehicle.getZone().getDeltaX() < 0)
            i = imgVehicleWest;
        else if(vehicle.getZone().getDeltaY() > 0)
            i = imgVehicleSouth;
        else
            i = imgVehicleNorth;
                
        g.drawImage(i,
                vehicle.getX(),
                vehicle.getY(),
                vehicle.getWidth(),
                vehicle.getHeight(),
                null);
    }
}
