package utility;

import game.WorldSettings;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import static game.WorldSettings.*;

public class Camera implements InterfaceCamera {

	private float x;
	private float y;
	private float xOffset = 0;
	private float yOffset = 0;

	private float widthBorder;
	private float heightBorder;

	public Camera(float x, float y, float widthBorder, float heightBorder) {
		this.x = x;
		this.y = y;
		this.widthBorder = widthBorder;
		this.heightBorder = heightBorder;
	}

	@Override
	public void step(float delta, Vec2 target, float speed) {
		xOffset = (((target.x * meterToPixel) - SCREEN_WIDTH/2) - x) * speed;
		yOffset = (((target.y * meterToPixel) - SCREEN_HEIGHT/2) - y) * speed;
		x += xOffset;
		y += yOffset;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}

	public float getWidthBorder() {
		return widthBorder;
	}

	public void setWidthBorder(float widthBorder) {
		this.widthBorder = widthBorder;
	}

	public float getHeightBorder() {
		return heightBorder;
	}

	public void setHeightBorder(float heightBorder) {
		this.heightBorder = heightBorder;
	}

}
