/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.views;

import colleagues.IColleague;
import gui.GUICirculation;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Raphaël Racine
 */
public abstract class View implements Observer {
    
    private final GUICirculation parent;
    
    public View(GUICirculation parent, IColleague model) {
        model.addViewObserver(this);
        this.parent = parent;
    }

    @Override
    public void update(Observable o, Object arg) {
        parent.repaint();
    }
    
    public abstract void draw(Graphics g);
}
