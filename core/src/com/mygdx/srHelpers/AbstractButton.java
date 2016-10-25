package com.mygdx.srHelpers;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * generic button,
 * A basic rectangle bounding box and a method for handling clicks/inputs
 */
public abstract class AbstractButton {

    private Rectangle  bounds;
    private Vector2 position;





    public abstract boolean onPress();


    public abstract Rectangle getBounds();


}
