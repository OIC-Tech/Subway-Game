package entity;


import org.lwjgl.opengl.*;

import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.opengl.Texture;

public class Button extends AbstractClickableEntity {
	
	public Button(float x, float y, float width, float height) {
		super(x, y, width, height);
	}
	public Button(float x, float y, float width, float height, Texture texture) {
		super(x, y, width, height, texture);
	}

	@Override
	public void draw() {
		if (textured) {
			glPushMatrix();
			glLoadIdentity();
			glBindTexture(GL_TEXTURE_2D, texture.getTextureID());
			glTranslatef(x, y, 0);
			glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex2f(0, 0); // Upper left

			glTexCoord2f(1, 0);
			glVertex2f(width, 0); // Upper right

			glTexCoord2f(1, 1);
			glVertex2f(width, height); // Lower right

			glTexCoord2f(0, 1);
			glVertex2f(0, height); // Lower left
			glEnd();
			glPopMatrix();
		} else {
			glPushMatrix();
			glLoadIdentity();
			glTranslatef(x, y, 0);
			glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex2f(0, 0); // Upper left

			glTexCoord2f(1, 0);
			glVertex2f(width, 0); // Upper right

			glTexCoord2f(1, 1);
			glVertex2f(width, height); // Lower right

			glTexCoord2f(0, 1);
			glVertex2f(0, height); // Lower left
			glEnd();
			glPopMatrix();
		}
	}

	@Override
	public void update() {

	}

}
