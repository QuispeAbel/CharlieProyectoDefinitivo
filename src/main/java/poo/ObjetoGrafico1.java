package poo;

import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.*; //Point2d

public class ObjetoGrafico1 {
    protected BufferedImage imagen = null;

	double positionX = 0;
	double positionY = 0;
	
    public ObjetoGrafico1(String filename) {
    		try {
				imagen= ImageIO.read(getClass().getResource(filename));

			} catch (IOException e) {
				System.out.println(e);
			}
    }

	public int getWidth(){
		return imagen.getWidth();
	}
	public int getHeight(){

		return imagen.getHeight();
	}

	public void setPosition(int x,int y){
		this.positionX = x;
		this.positionY = y;
	}

   	public void display(Graphics2D g2) {
		g2.drawImage(imagen,(int) this.positionX,(int) this.positionY,null);
  	}

	public double getX(){
		return positionX;
	}

	public double getY(){
		return positionY;
	}

}
