package com.mygdx.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.gameworld.GameRenderer;
import com.mygdx.gameworld.GameWorld;
import com.mygdx.slotrocket.SRGame;
import com.mygdx.srHelpers.InputHandler;
import com.mygdx.srHelpers.PauseHandler;
import com.mygdx.srHelpers.ScreenState;
import com.mygdx.srHelpers.UpperHandler;

public class GameScreen implements Screen {

    private GameRenderer renderer;


    private float runTime = 0;
    private GameWorld world;
    private SRGame myGame;
    InputMultiplexer multiplexer;
    private ScreenState state;
    private Screen otherScreen;
    private Texture explosion;
    private FPSLogger logger;
    private TextureRegion[] explosionFrames;
    private BitmapFont gameFont;


    public enum GameState {

        READY, RUNNING, PAUSED, GAMEOVER
    }

    public GameState getCurrentState() {

        return currentState;
    }

    public void setCurrentState(GameState currentState) {
        this.currentState = currentState;
    }

    public GameState currentState;

    public GameScreen(SRGame myGame, Screen otherScreen) {
        /*
         * note, screen width and height can't be gotten before the screen object is initialized
         * so don't DRY it up past this point
         */
        this.myGame = myGame;
        float screenWidth = (float) Gdx.graphics.getWidth();
        float screenHeight = (float) Gdx.graphics.getHeight();
        //sets the lower screen buttons to a standard fraction of the screen
        //so it's the same ratio on different size device screens
        float buttonWidth = screenWidth / 9;
        //sets the ratio of the size of the falling asteroids to the devices screen width
        //so it's scaled to different size device screens
        float rockWidth = screenWidth / 7;

        //creates a gameworld and render object, these are the two main objects that talk to
        //each other to run the game logic (game obj) and render  the graphics (render obj)
        world = new GameWorld(buttonWidth, screenWidth, screenHeight, rockWidth, this);
        renderer = new GameRenderer(world, screenWidth, screenHeight, this);
        this.otherScreen = otherScreen;

        //creates an input multiplexer, allowing for two different input handlers,
        //one for the upper screen where the asteroids are and one for; controlling the blocks
        //on the bottom
        multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(new InputHandler(world));
        multiplexer.addProcessor(new UpperHandler(world));
        multiplexer.addProcessor(new PauseHandler(world));


//        Gdx.input.setInputProcessor(multiplexer);

        currentState = GameState.RUNNING;
        state = ScreenState.RUNNING;
        logger = new FPSLogger();


    }


    //this is called in the main thread of the game, calls game world update and then renders
    @Override
    public void render(float delta) {

        switch (state) {

            case READY:
                break;
            case RUNNING:
                world.update(delta);
                break;
            case PAUSED:
                break;
            case GAMEOVER:
                break;
        }

          /*
          update method calls are done in order, so the game world logic is updated,
          the the render method is called to render objects affected by logic
           */
        renderer.render(delta);
//        logger.log();
        runTime += delta;

    }

    //following methods print to the console changes to the gamescreen, incuding
    //resizing, opening and closing
    @Override
    public void resize(int width, int height) {
        Gdx.app.log("GameScreen", "resizing");

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this.multiplexer);

        state = ScreenState.RUNNING;
        Gdx.app.log("GameScreen", "show called");


    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
        currentState = GameState.PAUSED;
        Gdx.app.log("GameScreen", "hide called");

    }

    @Override
    public void pause() {
        state = ScreenState.PAUSED;
        Gdx.app.log("GameScreen", "pause called");

    }

    @Override
    public void resume() {
        state = ScreenState.READY;
        Gdx.app.log("GameScreen", "resume called");

    }

    @Override
    public void dispose() {
        renderer.dispose();

    }

    public ScreenState getState() {
        return state;
    }

    public void setState(ScreenState state) {
        this.state = state;
    }

    public SRGame getMyGame() {
        return myGame;
    }

    public Screen getOtherScreen() {
        return otherScreen;
    }

    public void setOtherScreen(Screen otherScreen) {
        this.otherScreen = otherScreen;
    }

    public GameWorld getWorld() {
        return world;
    }

    public Texture getExplosion() {
        return explosion;
    }

    public void initTexures() {
        explosion = myGame.getManager().get("data/explosion_W_alpha.png", Texture.class);
        gameFont = myGame.getManager().get("data/nevis.ttf", BitmapFont.class);
    }

    public void makeFrames() {


        int columns = 5;
        int rows = 2;
        TextureRegion[][] exlpoTmp = TextureRegion.split(explosion,
                102, 100);
        explosionFrames = new TextureRegion[10];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                explosionFrames[index++] = exlpoTmp[i][j];
            }
        }


    }

    public TextureRegion[] getExplosionFrames() {
        return explosionFrames;
    }

    public BitmapFont getGameFont() {
        return gameFont;
    }

    public GameRenderer getRenderer() {
        return renderer;
    }
}
