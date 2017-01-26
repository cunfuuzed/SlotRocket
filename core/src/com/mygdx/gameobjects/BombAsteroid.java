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
    private static int type = 1;
    private int[] gap;
    private float rockWidth;
    private int health;
    private boolean isAlive;
    private float radius;
    private String color;

    public BombAsteroid(float rockWidth) {
        super(rockWidth);
        radius = 1.5f * rockWidth;
        this.color = "Red";
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }



    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public int specialAction() {
        int type = 1;
        return type;
    }
}


