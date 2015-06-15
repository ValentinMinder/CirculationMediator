package gui;

import gui.views.View;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JFrame;

public class GUICirculation extends JFrame {

    private final List<View> viewsToDraw;

    public GUICirculation() {

    	Controller c = Controller.getInstance(this);
    	new Thread(c).start();
        viewsToDraw = c.getListViews();
        add(new BackgroundImagePanel("roadBackground.png"));
        
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
