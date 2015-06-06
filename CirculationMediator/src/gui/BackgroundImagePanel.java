
package gui;

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
    
    public BackgroundImagePanel(String fileName) {
        
        backgroundPicture = null;
        
        try {
            backgroundPicture = ImageIO.read(new File(fileName));            
            setPreferredSize(new Dimension(backgroundPicture.getWidth(), backgroundPicture.getHeight()));
        } catch (IOException ex) {
            Logger.getLogger(BackgroundImagePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundPicture, 0, 0, this);
    }
}
