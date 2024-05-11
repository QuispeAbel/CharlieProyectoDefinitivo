package poo;
import java.awt.*;
import java.awt.geom.*;

import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.net.*; //nuevo para sonido


public class Fondo extends ObjetoGrafico1 {
    public Fondo(String filename) {
		super(filename);
		setPosition(0,0); // El fondo es una imagen estatica, pero muy grande
	}
 	
}
