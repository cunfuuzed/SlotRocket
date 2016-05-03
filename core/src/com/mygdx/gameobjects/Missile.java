package com.mygdx.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.mygdx.gameworld.GameWorld;

public class Missile implements Poolable {

	private int[] gap;
	private Vector2 position;
	private Vector2 velocity;
	private boolean fits;
	private boolean isAlive;
	private float gapWidth;
	
	
	public Missile(GameWorld world){
		
		//uses the gameworld object as an argument to get the gap width to set the size of the missile
		//so it all stays as a relative fraction of the devices game screen
		gap = new int[4];
		position = new Vector2();
		velocity = new Vector2(0,-100);
		fits = false;
		isAlive = false;
		gapWidth = world.getGapWidth();
		
	}

	public int[] getGap() {
		return gap;
	}

	public Vector2 getPosition() {
		return position;
	}

	public boolean isFits() {
		return fits;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setFits(boolean fits) {
		this.fits = fits;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public void setPosition(float x, float y){
		this.position.x = x;
		this.position.y = y;
	}
	
	public void setVelocity(float y){
		this.velocity.y = y;
		this.velocity.x = 0f;
	}
	
	public void setGap (int[] gap){
		this.gap = gap.clone();
	}

	@Override
	public void reset() {
		this.velocity.y = 0f;
		this.isAlive = false;
		this.fits = false;
		
	}
	
	public void update(float delta){
		
		if(isAlive){
			position.y += velocity.y * delta;
		}
			//kills missile when it goes off the top of the screen
		if(position.y < - (4*gapWidth)){
				isAlive = false;
			}
		}
	}
	

