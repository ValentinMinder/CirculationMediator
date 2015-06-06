package gui;

import gui.views.View;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import javax.swing.JFrame;

public class GUICirculation extends JFrame {

    private final LinkedList<View> viewsToDraw;

    public GUICirculation() {

        viewsToDraw = new LinkedList<>();
        add(new BackgroundImagePanel("roadBackground.png"));
        
        getContentPane().addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Coordonnée X :" + e.getX() + " Coordonnée Y : " + e.getY());
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setTitle("Projet Médiateur - Circulation");
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        for(View v : viewsToDraw)
            v.draw(g);
    }

    public void addView(View view) {
        if (!viewsToDraw.contains(view)) {
            viewsToDraw.add(view);
        }
    }
    
    public void removeView(View view) {
        viewsToDraw.remove(view);
    }
}
