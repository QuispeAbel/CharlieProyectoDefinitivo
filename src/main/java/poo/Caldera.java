package poo;

import java.awt.*;
import java.awt.geom.*;
import java.awt.Rectangle;

public class Caldera extends obstaculo {

    Caldera(String filename,double Y) {
        super(filename,190);
    }

    public Caldera(String filename, double x, double y) {
        super(filename, (int) x, (int) y,10,10);
    }

    public Rectangle getColiton() {
            return this;
    }    

    @Override
    void desplazamientoX() {
    }// anula
}
