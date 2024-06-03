package poo;

public class MonoAzul extends Monito {

    private boolean onGround = false;

    protected double velocityX = 0.0;
    protected double velocityY = 0.0;
    protected double gravity = 30.0;
    protected double angulo = 0.0;

    public int POSICION_Y_PISO = 340;

    MonoAzul(String filename) {
        super(filename);

    }

    public void jump() {
        if (onGround) {
            velocityY = -600;
            onGround = false;
        }
    }

    public void update(double delta) {

        velocityY += gravity;
        this.setY(getY() + velocityY * delta);
        this.setX(getX() + velocityX * delta);

        if (getY() > POSICION_Y_PISO) {
            this.setY(POSICION_Y_PISO);
            velocityY = 0.0;
            onGround = true;
            /* ya toco el piso */
        }

    }
}
