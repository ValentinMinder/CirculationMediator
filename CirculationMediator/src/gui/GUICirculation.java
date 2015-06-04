
package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JPanel;

public class GUICirculation extends JFrame {
    
    private final Image background;
    
    private final JPanel mainPanel = new JPanel() {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.drawImage(background, 0, 0, 50, 50, this);
        }
    };
    
    public GUICirculation() {        
        background = Toolkit.getDefaultToolkit().createImage("plan.jpg");   

        mainPanel.setPreferredSize(new Dimension(background.getWidth(rootPane), background.getHeight(rootPane)));
        
        add(mainPanel);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setTitle("Projet Médiateur - Circulation");
        setVisible(true);
    }
}
