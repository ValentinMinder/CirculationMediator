package gui.views;

import colleagues.MovingColleague;
import colleagues.Train;
import gui.GUICirculation;
import java.awt.Graphics;

public class TrainView extends View {
    private final Train train;
    
    public TrainView(GUICirculation parent, Train train) {
        super(parent, train);
        this.train = train;
    }
    
    @Override
    public void draw(Graphics g) {
    }
}
