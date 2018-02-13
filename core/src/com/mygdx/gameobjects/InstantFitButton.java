package com.mygdx.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.screens.GameScreen;
import com.mygdx.screens.MenuScreen;
import com.mygdx.slotrocket.SRGame;
import com.mygdx.srHelpers.AbstractButton;

/**
 * Created by User on 2/12/2018.
 */

public class InstantFitButton extends AbstractButton {

    private Rectangle bounds;
    private Vector2 position;
    private boolean clicked;
    private SRGame myGame;
    private GameScreen gameScreen;
    private MenuScreen menuScreen;
    private int[] gap;

    public InstantFitButton(Rectangle bounds, GameScreen gameScreen){

        this.bounds = bounds;
        this.position = new Vector2(bounds.getX(), bounds.getY());
        this.gameScreen = gameScreen;
        this.myGame = gameScreen.getMyGame();
        this.menuScreen = myGame.getMenuScreen();
        this.gap = new int[4];

        clicked = false;
    }
    @Override
    public boolean onPress() {

        gameScreen.getWorld().getBattery().setInstantFitsLeft();
        return false;
    }

    @Override
    public Rectangle getBounds() {

        return bounds;
    }
}
