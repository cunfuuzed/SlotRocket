package com.mygdx.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
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
	private Rectangle bounds;
	private float boxOffset;
	
	public Missile(GameWorld world){
		
		//uses the gameWorld object as an argument to get the gap width to set the size of the missile
		//so it all stays as a relative fraction of the devices game screen
		gap = new int[4];
		position = new Vector2();
		velocity = new Vector2(0,-100);
		fits = false;
		isAlive = false;
		gapWidth = world.getGapWidth();
		boxOffset = gapWidth/2;
		bounds = new Rectangle(position.x + boxOffset, position.y, 
				4 *world.getGapWidth(), 4 * world.getGapWidth());
		Gdx.app.log("Missile", "bounds =" + bounds.height + " " + bounds.width);
			
		
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
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
		for(int i=0; i<gap.length; i++){
			this.gap[i] = gap[i];
		}
		
	}

	@Override
	public void reset() {
		this.velocity.y = 0f;
		this.isAlive = false;
		this.fits = false;
		
	}
	
	public void update(float delta){
			//only updates position if the missile is alive
		if(isAlive){
			position.y += velocity.y * delta;
			bounds.setPosition(position.x + boxOffset,
					position.y);
			
		}
			//kills missile when it goes off the top of the screen
		if(position.y < - (4*gapWidth)){
				isAlive = false;
			}
		}
	}
	

