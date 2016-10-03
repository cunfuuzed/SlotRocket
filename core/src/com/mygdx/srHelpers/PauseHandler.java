package com.mygdx.srHelpers;

import com.badlogic.gdx.InputProcessor;
import com.mygdx.gameobjects.PauseButton;
import com.mygdx.gameworld.GameWorld;

/**
 * Created by User on 9/5/2016.
 */
public class PauseHandler implements InputProcessor {

    private GameWorld world;
    private PauseButton pauseButton;

    public PauseHandler ( GameWorld world){
        this.world = world;
        this. pauseButton = world.getPauseButton();
    }



    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if (pauseButton.getBounds().contains(screenX, screenY)) {
            pauseButton.onPress();
        }
        return false;
    }
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
