
package gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

public class GUICirculation extends JFrame {
    
    public GUICirculation() {
        
        add(new BackgroundImagePanel("backgroundtest.jpg"));
        
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setTitle("Projet Médiateur - Circulation");
        setVisible(true);
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        // Ici on dessinera les vues...
        g.setColor(Color.red);
        g.fillRect(0, 0, 50, 50);
    }
}
