package com.mygdx.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.mygdx.gameobjects.Asteroid;
import com.mygdx.gameobjects.Battery;
import com.mygdx.gameobjects.Block;
import com.mygdx.gameobjects.BombAsteroid;
import com.mygdx.gameobjects.Explosion;
import com.mygdx.gameobjects.GameOverButton;
import com.mygdx.gameobjects.Generator;
import com.mygdx.gameobjects.Ground;
import com.mygdx.gameobjects.InstantFitButton;
import com.mygdx.gameobjects.Missile;
import com.mygdx.gameobjects.PauseButton;
import com.mygdx.screens.GameScreen;
import com.mygdx.srHelpers.ScreenState;

public class GameRenderer {

    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batcher;
    private GameWorld myWorld;
    private Block launcher;
    private Generator generator;
    private Battery battery;
    private Array asteroids;
    private Array missiles;
    private float screenWidth, screenHeight;
    private Ground ground;
    private float gapWidth;
    private float halfGap;
    private PauseButton pauseButton;
    private GameOverButton gameOverButton;
    private InstantFitButton instantFitButton;
    private Texture explosion;
    private GameScreen gameScreen;
//    private TextureRegion[] explosionFrames;
//    private Animation standardExplosion;
    private boolean texturesLoaded;
    private float stateTime;
    private float rockWidth;
    private GlyphLayout rocksDestroyed;

//    private final Array<Animation> explosionAnims = new Array<Animation>(false, 4);
//
//    private final Pool<Animation> expoAnimsPool = new Pool(10) {
//        @Override
//        protected Object newObject() {
//            return new Animation(1.0f, explosionFrames);
//        }
//    };

    public GameRenderer(GameWorld myWorld, float screenWidth, float screenHeight,
                        GameScreen gameScreen) {

        this.gameScreen = gameScreen;
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.myWorld = myWorld;
        this.gapWidth = myWorld.getGapWidth();
        this.halfGap = gapWidth / 2;
        this.pauseButton = myWorld.getPauseButton();
        cam = new OrthographicCamera();
        cam.setToOrtho(true, screenWidth, screenHeight);

        batcher = new SpriteBatch();
        // Attach batcher to camera
        batcher.setProjectionMatrix(cam.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
        texturesLoaded = false;
        stateTime = 0f;
        rockWidth = screenWidth / 7;
        rocksDestroyed = new GlyphLayout();

        initGameobjects();

    }

    public void render(float delta) {
        // first part of buffer draw
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//        Gdx.app.log("GameRender", String.valueOf(stateTime));
        // drawing black background for double buffer
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(200, 0, 0, 1);

        // drawing loop for launcher
        for (int i = 0; i < launcher.getRows(); i++) {
            for (int j = 0; j < launcher.getColumns(); j++) {
                if (launcher.getCellVis(i, j)) {
                    shapeRenderer.rect(launcher.getButtons()[i][j].getX(),
                            launcher.getButtons()[i][j].getY(),
                            launcher.getButtons()[i][j].getWidth(),
                            launcher.getButtons()[i][j].getHeight());
                }

            }
        }


        // draw loop for asteroids
//		shapeRenderer.setColor(0, 200, 0, 1);
        if (asteroids.size > 0) {
            for (int i = 0; i < asteroids.size; i++) {
                Asteroid item = (Asteroid) asteroids.get(i);
                if (item.getClass() == Asteroid.class) {
                    shapeRenderer.setColor(0, 200, 0, 1);
                } else if (item.getClass() == BombAsteroid.class) {
                    shapeRenderer.setColor(200, 0, 0, 1);
                }
                shapeRenderer.rect(item.getBounds().x, item.getBounds().y,
                        myWorld.getRockWidth(), myWorld.getRockWidth());

            }
        }

        // draw loop for gaps
        shapeRenderer.setColor(0, 0, 0, 1);
        if (asteroids.size > 0) {
            for (int i = 0; i < asteroids.size; i++) {
                Asteroid item = (Asteroid) asteroids.get(i);
                float start = item.getPosition().x + halfGap;
                float bottom = item.getPosition().y + myWorld.getRockWidth();
                int[] gap = item.getGap();
                for (int j = 0; j < gap.length; j++) {
                    shapeRenderer.rect(start + (j * gapWidth), bottom
                            - gapWidth * gap[j], gapWidth, gapWidth * gap[j]);
                }
            }
        }

        // draw loop for missiles
        shapeRenderer.setColor(200, 0, 200, 1);
        if (missiles.size > 0) {
            for (int i = 0; i < missiles.size; i++) {
                Missile item = (Missile) missiles.get(i);
                for (int j = 0; j < 4; j++) {
                    if (item.getGap()[j] > 0) {
                        shapeRenderer.rect(item.getPosition().x + (j * gapWidth) + halfGap,
                                item.getPosition().y + (4 - item.getGap()[j]) * gapWidth,
                                gapWidth, item.getGap()[j] * gapWidth);
                    }
                }
            }
        }

        // temporary ground
        shapeRenderer.setColor(0, 0, 200, 1);
        shapeRenderer.rect(ground.getBounds().x, ground.getBounds().y,
                ground.getBounds().width, ground.getBounds().height);

        //pause button
        shapeRenderer.rect(pauseButton.getBounds().x, pauseButton.getBounds().y,
                pauseButton.getBounds().width, pauseButton.getBounds().height);

        //instant fit button
        shapeRenderer.rect(instantFitButton.getBounds().x, instantFitButton.getBounds().y,
                instantFitButton.getBounds().width, instantFitButton.getBounds().height);



        /*
        game over button, HAS to be at this end of the solid shape stack to render over
        the asteroids
         */
        if (myWorld.getScreen().getState() == ScreenState.GAMEOVER) {
            shapeRenderer.setColor(157, 117, 55, 1);
            shapeRenderer.rect(gameOverButton.getBounds().x, gameOverButton.getBounds().y,
                    gameOverButton.getBounds().width, gameOverButton.getBounds().height);
        }


        shapeRenderer.end();
        //temporary outline render batch for bounding box outlines
        shapeRenderer.begin(ShapeType.Line);
        shapeRenderer.setColor(200, 200, 200, 1);
        if (missiles.size > 0) {
            for (int i = 0; i < missiles.size; i++) {
                Missile item = (Missile) missiles.get(i);
                shapeRenderer.rect(item.getBounds().x,
                        item.getBounds().y,
                        item.getBounds().width,
                        item.getBounds().height);
            }
        }
        shapeRenderer.end();

        batcher.begin();
//        batcher.disableBlending();
        // checking if there are any explosions to draw
        if (generator.getExplosions().size > 0) {
            Explosion burst;
//            Gdx.app.log("GameRender", String.valueOf(generator.getExplosions().size));
            for (int i = 0; i < generator.getExplosions().size; i++) {
                burst = generator.getExplosions().get(i);
                TextureRegion currentFrame = (TextureRegion) gameScreen.getStandardExplosion().getKeyFrame(burst.getTime());
                batcher.draw(currentFrame, burst.getX() - rockWidth, burst.getY() - rockWidth,
                        2 * rockWidth, 2 * rockWidth);
                burst.advanceTime(delta);
                if (burst.isDone(gameScreen.getStandardExplosion().getAnimationDuration())) { //when done, remove explosions
                    generator.getExplosions().removeIndex(i);
                    generator.explosionDone(burst);
                }
            }
        }
        // draw textures
        rocksDestroyed.setText(gameScreen.getGameFont(),
                "Score: " + gameScreen.getMyGame().getScoreKeeper().getAsteroidsDestroyed());
        gameScreen.getGameFont().draw(batcher, rocksDestroyed, rockWidth/2, rockWidth/2);
        batcher.end();
    }

    private void initGameobjects() {
        launcher = myWorld.getLauncher();
        generator = myWorld.getGenerator();
        asteroids = generator.getAsteroids();
        ground = myWorld.getGround();
        battery = myWorld.getBattery();
        missiles = battery.getMissles();
        gameOverButton = myWorld.getGameOverButton();
        instantFitButton = myWorld.getInstantFitButton();

    }


    public void dispose() {
        batcher.dispose();
    }


}
