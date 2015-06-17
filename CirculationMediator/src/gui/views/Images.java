
package gui.views;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Images {
    public static BufferedImage imgVehicleEast;
    public static BufferedImage imgVehicleNorth;
    public static BufferedImage imgVehicleSouth;
    public static BufferedImage imgVehicleWest;
    public static BufferedImage imgTrain;
    public static BufferedImage imgPedestrian;
    public static BufferedImage imgExplosion;
    
    static {
        try {
            imgVehicleEast = ImageIO.read(new File("imgVehicleEast.png"));
            imgVehicleNorth = ImageIO.read(new File("imgVehicleNorth.png"));
            imgVehicleSouth = ImageIO.read(new File("imgVehicleSouth.png"));
            imgVehicleWest = ImageIO.read(new File("imgVehicleWest.png"));
            imgPedestrian = ImageIO.read(new File("imgPedestrian.png"));
            imgTrain = ImageIO.read(new File("trainlong.png"));
            imgExplosion = ImageIO.read(new File("explosion.png"));
        } catch (IOException ex) {
            Logger.getLogger(Images.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
