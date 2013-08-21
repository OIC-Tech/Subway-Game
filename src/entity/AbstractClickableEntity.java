package entity;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

public abstract class AbstractClickableEntity extends AbstractEntity implements ClickableEntity {

	public AbstractClickableEntity(float x, float y, float width, float height) {
		super(x, y, width, height);
	}
	
	public AbstractClickableEntity(float x, float y, float width, float height, Texture texture) {
		super(x, y, width, height, texture);
	}

	public boolean isClicked(int mouseX, int mouseY) {
		if (Mouse.isButtonDown(0)) {
			if ((mouseX > x) && (mouseX < x + width) && (mouseY > y) && (mouseY < y + height)) {
				return true;
			}
		}
		return false;

	}

}
