package entity;

import org.jbox2d.callbacks.ContactListener;

public interface InterfaceHuman {
	
	public void draw();
	
	public void jump();
	
	public void walkLeft();
	
	public void walkRight();
	
	public void run();
	
	public void step();
	
	public ContactListener getContactListener();

}
