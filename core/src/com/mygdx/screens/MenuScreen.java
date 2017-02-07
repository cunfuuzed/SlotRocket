package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.mygdx.menuworld.MenuRenderer;
import com.mygdx.menuworld.MenuWorld;
import com.badlogic.gdx.Game;

import com.mygdx.slotrocket.SRGame;
import com.mygdx.srHelpers.MenuHandler;
import com.mygdx.srHelpers.ScreenState;

/**
 * Created by User on 8/22/2016.
 */
public class MenuScreen implements Screen {

    private MenuWorld world;
    private MenuRenderer renderer;
    private SRGame myGame;
    InputMultiplexer multiplexer;
    private ScreenState state;
    private Screen otherScreen;


    public MenuScreen(SRGame myGame, Screen otherScreen) {
        float screenWidth = (float) Gdx.graphics.getWidth();
        float screenHeight = (float) Gdx.graphics.getHeight();
        this.myGame = myGame;
        world = new MenuWorld(this, screenWidth, screenHeight);
        renderer = new MenuRenderer(world, screenWidth, screenHeight, this);

        if (myGame != null) {
            Gdx.app.log("MenuScreen", "myGame is  not null");
        }
        multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(new MenuHandler(world));
        this.otherScreen = otherScreen;

        state = ScreenState.RUNNING;
    }

    public SRGame getMyGame() {
        return myGame;
    }

    @Override
    public void show() {
        Gdx.app.log("MenuScreen", "showing");
        state = ScreenState.RUNNING;

        Gdx.input.setInputProcessor(this.multiplexer);

    }

    @Override
    public void render(float delta) {
        switch (state) {

            case READY:
                break;
            case RUNNING:
//                world.update(delta);
                break;
            case PAUSED:
                break;
            case GAMEOVER:
                break;
        }

        renderer.render();


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        state = ScreenState.PAUSED;

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        state = ScreenState.PAUSED;
        Gdx.input.setInputProcessor(null);

    }

    @Override
    public void dispose() {

    }

    public ScreenState getState() {
        return state;
    }

    public void setState(ScreenState state) {
        this.state = state;
    }

    public void initTextures() {

    }
}
