package entity;

public interface Entity {

	public void draw();
	public void update();
	public void setLocation(float x, float y);
	public void transform(float amountX, float amountY);
	public void setX(float x);
	public void setY(float y);
	public void setWidth(float width);
	public void setHeigth(float heigth);
	public float getX();
	public float getY();
	public float getHeigth();
	public float getWidth();
	public boolean intersects(Entity other);
	
	
}
