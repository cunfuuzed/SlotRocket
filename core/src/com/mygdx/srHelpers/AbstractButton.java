package com.mygdx.srHelpers;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.screens.GameScreen;

/**
 * generic button,
 * A basic rectangle bounding box and a method for handling clicks/inputs
 */
public abstract class AbstractButton {

    public Rectangle  bounds;
    public Vector2 position;
    public GameScreen gameScreen;






    public abstract boolean onPress();


    public abstract Rectangle getBounds();


}
