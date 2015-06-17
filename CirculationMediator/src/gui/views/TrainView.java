package gui.views;

import colleagues.Train;
import gui.GUICirculation;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class TrainView extends View {
    private final Train train;
    
    public TrainView(GUICirculation parent, Train train) {
        super(parent, train);
        this.train = train;
    }
    
    @Override
    public void draw(Graphics g) {
        g.drawImage(Images.imgTrain,
                train.getX(),
                train.getY(),
                train.getWidth(),
                train.getHeight(),
                null);
        
        if(train.isExploded()) {
            g.drawImage(Images.imgTrain,
                train.getX(),
                train.getY(),
                train.getWidth(),
                train.getHeight(),
                null);
        }
    }
}
