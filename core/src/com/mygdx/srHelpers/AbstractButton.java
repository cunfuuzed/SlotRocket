package com.mygdx.srHelpers;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by User on 9/5/2016.
 */
public abstract class AbstractButton {

    private Rectangle  bounds;
    private Vector2 position;





    public abstract boolean onPress();


    public abstract Rectangle getBounds();


}
