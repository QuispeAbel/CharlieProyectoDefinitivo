package poo;

public class Leon extends Personaje{

    private int posXhitbox = 10;
    private int posYhitbox = 10;
    private int widthHitbox = 40;
    private int heigthHitbox = 100;

  public Leon(String filename, double x, double y) {
		super(filename, x, y);
    super.setHitbox(posXhitbox, posYhitbox, widthHitbox, heigthHitbox);
	}

  public void setX(double x){
    super.setX(x,posXhitbox);
  }

  public void setY(double Y){
    super.setY(y,posYhitbox);
  }
}
