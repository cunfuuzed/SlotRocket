package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.slotrocket.SRGame;

/**
 * Created by User on 1/29/2017.
 */

public class LoadScreen implements Screen {

    SRGame myGame;

    public LoadScreen(SRGame myGame) {
        this.myGame = myGame;
        myGame.getManager().load("data/explosion_W_alpha.png", Texture.class);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (myGame.getManager().update()) {
            Gdx.app.log("LoadScreen", String.valueOf(myGame.getManager().getProgress()));
            myGame.getGameScreen().initTexures();
            myGame.getGameScreen().makeFrames();
            myGame.getMenuScreen().initTextures();
//            myGame.getGameScreen().getRenderer().makeTextures();
            myGame.setMenuScreen();
        } else {
            Gdx.app.log("LoadScreen", String.valueOf(myGame.getManager().getProgress()));

        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
