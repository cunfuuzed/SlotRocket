package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.mygdx.slotrocket.SRGame;
import com.badlogic.gdx.graphics.Color;



/**
 * Created by User on 1/29/2017.
 */

public class LoadScreen implements Screen {

    SRGame myGame;

    public LoadScreen(SRGame myGame) {
        this.myGame = myGame;
        FileHandleResolver resolver = new InternalFileHandleResolver();
        myGame.getManager().setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        myGame.getManager().setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));

        FreetypeFontLoader.FreeTypeFontLoaderParameter menuFont = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        menuFont.fontFileName = "data/VanadineBold.ttf";
        menuFont.fontParameters.size = Gdx.graphics.getWidth()/7;
//        menuFont.fontParameters.color = Color.WHITE;
        myGame.getManager().load("data/VanadineBold.ttf", BitmapFont.class, menuFont);

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
