package poo;

import java.awt.*;
import java.awt.geom.*;
import java.awt.Rectangle;

public class Caldera extends obstaculo {

    Caldera(String filename,double Y) {
        super(filename,575);
    }

    public Caldera(String filename,double x, double alturaY,int width, int height) {
        super(filename,(int) x,(int) alturaY,width,height);
    }

    public Rectangle getColiton() {
        return this;
    }

    @Override
    void desplazamientoX() {
    }// anula
}
