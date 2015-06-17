package gui.views;

import colleagues.IColleague;
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
    private static BufferedImage imgVehicle;

    static {
        try {
            imgVehicle = ImageIO.read(new File("imgVehicle.png"));
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
        g.drawImage(imgVehicle,
                vehicle.getX(),
                vehicle.getY(),
                vehicle.getWidth(),
                vehicle.getHeight(),
                null);
    }
}
