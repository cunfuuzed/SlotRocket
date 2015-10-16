package com.mygdx.gameobjects;

import com.badlogic.gdx.math.Rectangle;

public class Ground {
	
	private Rectangle bounds;
	private float x, y, width, height ;
	
	
	public Ground(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		bounds = new Rectangle(x, y, width, height);
	}
	
	public Rectangle getBounds(){
		return bounds;
	}

}
