package com.mygdx.menuworld;

import com.badlogic.gdx.Gdx;
import com.mygdx.menuobjects.StartGameButton;
import com.mygdx.screens.MenuScreen;
import com.mygdx.slotrocket.SRGame;


public class MenuWorld {

    private StartGameButton startResume;
    private MenuScreen screen;
    private float screenHeight;
    private float screenWidth;
    private SRGame myGame;

    /**
     * Master object container for the menu screen that holds and organizes all objects on it it
     * @param screen screen this object will reside in
     * @param screenWidth  screen width
     * @param screenHeight screen height
     *
     */

    public MenuWorld(MenuScreen screen, float screenWidth, float screenHeight) {

        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.screen = screen;
        this.myGame = screen.getMyGame();
        if (myGame == null){
            Gdx.app.log("MenuWorld", "myGame is null");
        }
        startResume = new StartGameButton(screenWidth / 7, 2 * screenHeight / 6,
                5 * screenWidth / 7, screenHeight / 6, this.screen);


    }

    public void update(float delta){

        throw new RuntimeException("MenuWorld.update not implimented");
    }

    public StartGameButton getStartResume() {
        return startResume;
    }



    public void setStartResume(StartGameButton startResume) {
        this.startResume = startResume;
    }
}
