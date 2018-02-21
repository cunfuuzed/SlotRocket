package com.mygdx.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.screens.GameScreen;
import com.mygdx.slotrocket.SRGame;
import com.mygdx.srHelpers.AbstractButton;

/**
 * Created by User on 2/13/2018.
 */

public class ShieldButton extends AbstractButton {

    private SRGame myGame;

    public ShieldButton(Rectangle bounds, GameScreen gameScreen){
        this.bounds = bounds;
        this.gameScreen = gameScreen;
        this.myGame = gameScreen.getMyGame();
    }
    @Override
    public boolean onPress() {

        myGame.getScoreKeeper().shieldActive();
        return false;
    }

    @Override
    public Rectangle getBounds() {
        return this.bounds;
    }
}
