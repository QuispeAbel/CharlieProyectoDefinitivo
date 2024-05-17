package poo;

import java.awt.Rectangle;

// import processing.core.*;

public interface Hiteable {
	public Rectangle getHitbox();

	public void setHitbox(int width, int height);

	public void setHitbox(int x, int y, int width, int height);
}
