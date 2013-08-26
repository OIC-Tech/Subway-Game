package entity;

import game.Boot;

import org.jbox2d.callbacks.ContactListener;

import utility.Camera;

public interface InterfaceHuman {
	
	public void draw(Camera camera);
	
	public void jump();
	
	public void walkLeft();
	
	public void walkRight();
	
	public void run();
	
	public void step();
	
	public ContactListener getContactListener();
}
