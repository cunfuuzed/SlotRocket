package com.mygdx.gameobjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by User on 2/7/2017.
 */

public class Explosion implements Pool.Poolable {

//    private Animation animation;
    private float time;
    private Vector2 position;
    private float duration;

    public Explosion (float duration){
        this.duration = duration;
        time = 0.0f;
    }

//    public Explosion(float duration, TextureRegion[] textures) {
//        animation = new Animation<TextureRegion>(duration, textures);
//        animation.setPlayMode(Animation.PlayMode.NORMAL);
//        time = 0.0f;
//        position = new Vector2();
//    }

    /**
     * moves the animation forward by the time delta
     * @param delta time since the last render frame call
     */
    public void advanceTime(float delta) {
        time += delta;
    }

//    public TextureRegion getFrame() {
//        TextureRegion frame = (TextureRegion) animation.getKeyFrame(time);
//        return frame;
//    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public float getX() {
        return this.position.x;
    }

    public float getY() {
        return this.position.y;
    }

    public float getTime() {
        return time;
    }

    public boolean isDone(float duration) {
        if (time > duration){
            return true;
        }else{
            return false;
        }



//        if (animation.isAnimationFinished(time)) {
//            return true;
//        } else {
//            return false;
//        }
    }

    @Override
    public void reset() {
        time = 0.0f;
    }
}
