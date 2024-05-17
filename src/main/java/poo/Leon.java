package poo;

public class Leon extends Personaje {

  private int posXhitbox = 30;
  private int posYhitbox = 10;
  private int widthHitbox = 60;
  private int heigthHitbox = 40;

  public Leon(String filename, double x, double y) {
    super(filename, x, y);
    super.setHitbox((int) x + posXhitbox, (int) y + posYhitbox, widthHitbox, heigthHitbox);
  }

  public void setX(double x) {
    super.setX(x, posXhitbox);
  }

  public void setY(double y) {
    super.setY(y, posYhitbox);
  }
}
