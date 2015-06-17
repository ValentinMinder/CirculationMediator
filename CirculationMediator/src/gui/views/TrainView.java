package gui.views;

import colleagues.Train;
import gui.GUICirculation;
import java.awt.Color;
import java.awt.Graphics;

public class TrainView extends View {
    private final Train train;
    
    public TrainView(GUICirculation parent, Train train) {
        super(parent, train);
        this.train = train;
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(train.getX(), train.getY(), train.getWidth(), train.getHeight());
    }
}
