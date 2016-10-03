package com.mygdx.menuworld;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by User on 8/22/2016.
 */
public class MenuRenderer {

    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    private MenuWorld menuWorld;
    private float screenWidth, screenHeight;
    private SpriteBatch batcher;

    public MenuRenderer(MenuWorld menuWorld, float screenWidth, float screenHeight) {

        this.menuWorld = menuWorld;
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        cam = new OrthographicCamera();
        cam.setToOrtho(true, screenWidth, screenHeight);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);

    }

}
