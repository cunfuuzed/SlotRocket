package com.mygdx.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.screens.GameScreen;
import com.mygdx.screens.MenuScreen;
import com.mygdx.slotrocket.SRGame;
import com.mygdx.srHelpers.AbstractButton;
import com.mygdx.srHelpers.ScreenState;

/**
 * Button that appears when a game is over/lost. can be clicked to restart
 */

public class GameOverButton extends AbstractButton {

    private Rectangle bounds;
    private Vector2 position;
    private boolean clicked;
    private SRGame myGame;
    private GameScreen gameScreen;
    private MenuScreen menuScreen;


    public GameOverButton (Rectangle bounds, GameScreen gameScreen){

        this.bounds = bounds;
        this.position = new Vector2(bounds.getX(), bounds.getY());
        this.gameScreen = gameScreen;
        this.myGame = gameScreen.getMyGame();
        this.menuScreen = myGame.getMenuScreen();

        clicked = false;
    }
    @Override
    public boolean onPress() {

        gameScreen.getWorld().reset();
        myGame.getScoreKeeper().resetDamage();
        gameScreen.setState(ScreenState.RUNNING);

        return false;
    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }
}
