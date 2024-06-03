package poo;

import java.awt.Rectangle;

public class Tarima extends Obstaculo {

    Tarima(String filename, double Y) {
        super(filename, 190);
    }

    public Tarima(String filename, double x, double y) {
        super(filename, (int) x, (int) y);
    }

    public Rectangle getColiton() {
        return this;
    }
}
