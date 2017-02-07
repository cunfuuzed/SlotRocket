package com.mygdx.menuobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.screens.MenuScreen;
import com.mygdx.slotrocket.SRGame;
import com.mygdx.srHelpers.AbstractButton;
import com.mygdx.srHelpers.ScreenState;

/**
 * Button on the menu screen that starts a game and switches to the GameScreen
 * other pre-game settings handles by some other menu object, this only starts the game
 * Created by User on 10/3/2016.
 */


public class StartGameButton extends AbstractButton {

    private Vector2 position;
    private Rectangle  bounds;
    private MenuScreen screen;
    private boolean paused;
    private SRGame myGame;



    public StartGameButton(float x, float y, float width, float height, MenuScreen screen){

        this.position = new Vector2(x, y);
        this.bounds = new Rectangle(x, y, width, height);
        this.screen = screen;
        myGame = screen.getMyGame();


        paused = false;

    }



    @Override
    public boolean onPress() {

        if(screen.getState() == ScreenState.RUNNING){
            myGame.setScreen(myGame.getGameScreen());
            Gdx.app.log("StartGameButton", "button pressed");


        }


        return false;
    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }
}
