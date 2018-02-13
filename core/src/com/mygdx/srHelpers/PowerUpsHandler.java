package com.mygdx.srHelpers;

import com.badlogic.gdx.InputProcessor;
import com.mygdx.gameobjects.InstantFitButton;
import com.mygdx.gameworld.GameWorld;

/**
 * Created by User on 2/12/2018.
 */

public class PowerUpsHandler implements InputProcessor {

    private GameWorld world;
    private InstantFitButton instantFitButton;

    public PowerUpsHandler(GameWorld world) {
        this.world = world;
        this.instantFitButton = world.getInstantFitButton();
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

        if (instantFitButton.getBounds().contains(screenX, screenY)) {
            instantFitButton.onPress();
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
