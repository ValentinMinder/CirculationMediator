package gui.views;

import colleagues.Pedestrian;
import gui.GUICirculation;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class PedestrianView extends View {

    private final Pedestrian pedestrian;

    private static BufferedImage imgPedestrian;

    static {
        try {
            imgPedestrian = ImageIO.read(new File("imgPedestrian.png"));
        } catch (IOException ex) {
            Logger.getLogger(PedestrianView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public PedestrianView(GUICirculation parent, Pedestrian pedestrian) {
        super(parent, pedestrian);
        this.pedestrian = pedestrian;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(
                imgPedestrian,
                pedestrian.getX(),
                pedestrian.getY(),
                pedestrian.getWidth(),
                pedestrian.getHeight(),
                null);
    }
}
