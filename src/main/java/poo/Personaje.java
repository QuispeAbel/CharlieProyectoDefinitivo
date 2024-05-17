package poo;

import java.awt.*;
import java.awt.geom.*;

public class Personaje extends ObjetoGrafico {
	private boolean onGround = false;
	// private boolean saltando = false;

	final int DIRECCION_DERECHA = 0;
	final int DIRECCION_IZQUIERDA = 1;

	final int ESTADO_QUIETO = -1;
	final int ESTADO_CAMINANDO = 0;
	final int ESTADO_ARROJANDO_GRANADA = 4;
	final int ESTADO_MURIENDO = 5;

	int direccionActual;
	int estadoActual;

	protected double velocityX = 0.0;
	protected double velocityY = 0.0;
	protected double gravity = 15.0;
	protected double angulo = 0.0;

	protected int direccionAngulo = 1;

	protected Rectangle hitbox;

	public final int POSICION_Y_PISO = 575;

	public Personaje(String filename, double x, double y) {
		super(filename, (int) x, (int) y);
	}

	public void jump() {
		if (onGround) {
			velocityY = -600;
			onGround = false;
		}
	}

	public void ganar(int y) {
		// gravity = 10000.0;
		// this.setY(y);
		// POSICION_Y_PISO = y;
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

		angulo = (angulo % 360);

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

	public void draw(Graphics2D g2) {
		/* Redefinicion de Display para poder hacer la rotacion cuando salta */

		// AffineTransform transform = new AffineTransform();
		// transform.rotate(Math.toRadians(angulo), this.getX() + getWidth() / 2,
		// this.getY() + getHeight() / 2);

		// AffineTransform old = g2.getTransform();
		// g2.transform(transform);

		// g2.drawImage(imagen, (int) this.getX(), (int) this.getY(), null);

		// g2.setTransform(old);

		// g2.fillRect(this.x, this.y, this.width, this.height);
	}

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

	// public void setPosicion(double x, double y){
	// super.setPosicion(x, y);
	// }

}
