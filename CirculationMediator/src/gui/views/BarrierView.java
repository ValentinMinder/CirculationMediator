package gui.views;

import colleagues.Barrier;
import gui.GUICirculation;
import java.awt.Graphics;

public class BarrierView extends View {

    private final Barrier barrier;
    
    public BarrierView(GUICirculation parent, Barrier barrier) {
        super(parent, barrier);
        this.barrier = barrier;
    }

    @Override
    public void draw(Graphics g) {
    }    
}
