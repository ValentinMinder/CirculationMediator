package gui.views;

import colleagues.IColleague;
import colleagues.Pedestrian;
import gui.GUICirculation;
import java.awt.Graphics;

public class PedestrianView extends View {
    private final Pedestrian pedestrian;

    public PedestrianView(GUICirculation parent, Pedestrian pedestrian) {
        super(parent, pedestrian);
        this.pedestrian = pedestrian;
    }

    @Override
    public void draw(Graphics g) {
        // TODO
    }   
}
