package poo;

public class Charlie extends Personaje {
  private int posXhitbox = 5;
  private int posYhitbox = 0;
  private int widthHitbox = 30;
  private int heigthHitbox = 60;

  public Charlie(String filename, double x, double y) {
    super(filename, x, y);
    super.setHitbox((int) x + posXhitbox, (int) y + posYhitbox, widthHitbox, heigthHitbox);
  }

  public void setX(double x) {
    super.setX(x, posXhitbox);
  }

  public void setY(double y) {
    super.setY(y, posYhitbox);
  }

  public void ganar(int x, int y) {
    this.setX(x);
    POSICION_Y_PISO = y;
  }

}
