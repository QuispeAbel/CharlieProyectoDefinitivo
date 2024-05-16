package poo;
import java.awt.Rectangle;

public class tarima extends obstaculo {
    
    tarima(String filename,double Y) {
        super(filename,190);
    }

    public tarima(String filename, double x, double y) {
        super(filename, (int) x, (int) y,128,103);
    }

    public Rectangle getColiton() {
            return this;
    }   
}
