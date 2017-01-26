package com.mygdx.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool.Poolable;

public class Asteroid implements Poolable {

    private Vector2 position;
    private Vector2 velocity;
    private Rectangle bounds;
//    private static int type = 0;
    private int[] gap;
    private float rockWidth;
    private int health;
    private boolean isAlive;
    private String color;

    public Asteroid(float rockWidth) {

        this.rockWidth = rockWidth;
        position = new Vector2(0, -rockWidth - 2.0f);
        velocity = new Vector2();
        bounds = new Rectangle(position.x, position.y, rockWidth, rockWidth);
        gap = new int[4];
        health = 1;
        isAlive = true;
        this.color = "Green";


    }

    public void update(float delta) {

        if(health <= 0){
            isAlive = false;
        }

        if(isAlive){
            position.y += velocity.y * delta;
            bounds.set(position.x, position.y, rockWidth, rockWidth);
        }

    }

    @Override
    public void reset() {
        position.y = -rockWidth - 2.0f;
        velocity.y = 0;
        health = 1;
        isAlive = true;
        bounds.set(position.x, position.y, rockWidth, rockWidth);

    }

    public Vector2 getPosition(){
        return position;
    }

    public void setPosition(float x, float y){
        this.position.x = x;
        this.position.y = y;
        bounds.set(position.x, position.y, rockWidth, rockWidth);
    }

    public float getVelocity() {
        return velocity.y;
    }

    public void setVelocity(float velocity) {
        this.velocity.y = velocity;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setGap(int[] gap){
        this.gap = gap;
    }

    public int[] getGap(){
        return gap;
    }

    public boolean isAlive(){
        return isAlive;
    }

    public void setHealth(int health){
        this.health = health;
    }

//    public static int getType() {
//        return type;
//    }

    public String getColor() {
        return this.color;
    }

    public int specialAction(){
        int type = 0;
        return type;
    }


}
