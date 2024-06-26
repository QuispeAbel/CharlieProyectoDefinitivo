package poo;

import java.awt.*;

public class Personaje extends ObjetoGrafico implements Hiteable {
	private boolean onGround = false;
	// private boolean saltando = false;

	final int ESTADO_QUIETO = -1;

	int direccionActual;
	int estadoActual;

	protected double velocityX = 0.0;
	protected double velocityY = 0.0;
	protected double gravity = 15.0;
	protected double angulo = 0.0;

	protected int direccionAngulo = 1;

	protected Rectangle hitbox;

	public int POSICION_Y_PISO = 575;

	Personaje(String filename, double alturaY) {
		super(filename, alturaY);
		// this.alturaY = alturaY;
	}

	public Personaje(String filename, double x, double y) {
		super(filename, (int) x, (int) y);
	}

	public void jump() {
		if (onGround) {
			velocityY = -600;
			onGround = false;
		}
	}

	public void ganar(int x, int y) {
		this.setX(x);
		POSICION_Y_PISO = y;
	}

	public void perder(int y) {
		POSICION_Y_PISO = y;
	}

	public void quieto() {
		estadoActual = ESTADO_QUIETO;
		// acceleration.mult(0);
	}

	public void left(double despazamiento) {
		this.setX(this.getX() + despazamiento);
	}

	public void right(double despazamiento) {
		this.setX(this.getX() - despazamiento);
	}

	public void update(double delta) {

		velocityY += gravity;
		this.setY(getY() + velocityY * delta);
		this.setX(getX() + velocityX * delta);

		Mundo m = Mundo.getInstance();

		/* Rebota contra los margenes X del mundo */
		if ((getX() + (this.getWidth())) > m.getWidth()) {
			// positionX = m.getWidth() - (this.getWidth());
			velocityX *= -1;
		}
		/* Rebota contra la X=0 del mundo */
		if ((getX()) < 0) {
			velocityX *= -1;
			this.setX(0);
		}

		if (getY() > POSICION_Y_PISO) {
			this.setY(POSICION_Y_PISO);
			velocityY = 0.0;
			onGround = true;
			angulo = 0;
			/* ya toco el piso */
		}

	}

	// NUEVO PARA DIBUJAR LAS HITBOX
	// public void display(Graphics2D g2) {
	// super.display(g2);
	// g2.draw(hitbox);
	// }

	public Rectangle getColiton() {
		return this;
	}

	public void setHitbox(int x, int y, int width, int height) {
		hitbox = new Rectangle(x, y, width, height);
	}

	public Rectangle getHitbox() {
		return hitbox;
	}

	public void setX(double x, int posXhitbox) {
		super.setX(x);
		hitbox.x = (int) x + posXhitbox;
	}

	public void setY(double y, int posYhitbox) {
		super.setY(y);
		hitbox.y = (int) y + posYhitbox;
	}

	public void setPiso(int PosNueva) {
		POSICION_Y_PISO = PosNueva;
	}
	// public void setPosicion(double x, double y){
	// super.setPosicion(x, y);
	// }

	@Override
	public void setHitbox(int width, int height) {
		// TODO Auto-generated method stub
	}

}
