package com.mygdx.menuworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.menuobjects.StartGameButton;
import com.mygdx.screens.MenuScreen;

/**
 * Created by User on 8/22/2016.
 */
public class MenuRenderer {

    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    private MenuWorld myWorld;
    private float screenWidth, screenHeight;
    private SpriteBatch batcher;
    private StartGameButton startButton;
    private MenuScreen menuScreen;
    private BitmapFont bigFont;

    public MenuRenderer(MenuWorld myWorld, float screenWidth, float screenHeight,
                        MenuScreen menuScreen) {
        this.menuScreen = menuScreen;
        this.myWorld = myWorld;
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.startButton = myWorld.getStartResume();
        cam = new OrthographicCamera();
        cam.setToOrtho(true, screenWidth, screenHeight);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);

    }

    public void render() {
        // first part of buffer draw
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // drawing black background for double buffer

        //game start button
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 200, 1);
        shapeRenderer.rect(startButton.getBounds().x, startButton.getBounds().y,
                startButton.getBounds().width, startButton.getBounds().height);
        shapeRenderer.end();

    }
}
