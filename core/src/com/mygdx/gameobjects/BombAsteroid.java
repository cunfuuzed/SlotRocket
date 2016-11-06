package com.mygdx.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by User on 11/4/2016.
 */

public class BombAsteroid extends Asteroid {

    private Vector2 position;
    private Vector2 velocity;
    private Rectangle bounds;
    private int type;
    private int[] gap;
    private float rockWidth;
    private int health;
    private boolean isAlive;
    private float radius;

    public BombAsteroid(float rockWidth) {
        super(rockWidth);
        radius = 1.5f * rockWidth;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

}


