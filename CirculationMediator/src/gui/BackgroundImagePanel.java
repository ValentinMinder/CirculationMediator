
package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BackgroundImagePanel extends JPanel {
    
    private BufferedImage backgroundPicture;
    
    public static final int BACKGROUND_HEIGTH = 600;
    public static final int BACKGROUND_WIDTH = 1200;
    
    public BackgroundImagePanel(String fileName) {
        
        backgroundPicture = null;
        
        try {
            backgroundPicture = ImageIO.read(new File(fileName));
            setBackground(Color.WHITE);
            setPreferredSize(new Dimension(BACKGROUND_WIDTH, BACKGROUND_HEIGTH));
        } catch (IOException ex) {
            Logger.getLogger(BackgroundImagePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(backgroundPicture, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGTH, this);
    }
}
