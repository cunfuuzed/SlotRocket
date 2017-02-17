package com.mygdx.gameworld;

import com.badlogic.gdx.Gdx;
import com.mygdx.gameobjects.Asteroid;
import com.mygdx.slotrocket.SRGame;
import com.mygdx.srHelpers.LevelsContainer;
import com.mygdx.srHelpers.ScreenState;

public class ScoreKeeper {

    private int asteroidsDestroyed;
    private int totalScore;
    private LevelsContainer currentlevel;
    private int currentDamage;
    private SRGame myGame;
    private int maxAsteroids;
    private int maxMissiles;
    private int maxHealth;
    private GameWorld world;
    private Level level_1;
    private Level level_2;

//    private float spawnRates[] = {
//            2.0f,
//            2.2f,
//            2.5f
//    };
//
//    private float fallSpeeds[] = {
//            Gdx.graphics.getWidth()/6,
//            Gdx.graphics.getWidth()/5.5f,
//            Gdx.graphics.getWidth()/5.0f
//    };
//
//    private int clearPoints[] = {
//            100,
//            200,
//            300
//    };
//
//    private Class asteroidTypes[] = {
//            Asteroid.class,
//            BombAsteroid.class
//    };
//
//    private int asteroidPoints[] = {
//            10,
//            20
//    };


    public ScoreKeeper(SRGame myGame) {

        asteroidsDestroyed = 0;
        totalScore = 0;
//		initLevels();
        currentlevel = LevelsContainer.LEVEL1;
        currentDamage = 0;
        maxAsteroids = 0;
        maxMissiles = 0;
        this.myGame = myGame;
        maxHealth = 100;



    }


    public int getAsteroidsDestroyed() {
        return asteroidsDestroyed;
    }


    public void setAsteroidsDestroyed(int asteroidsDestroyed) {
        this.asteroidsDestroyed = asteroidsDestroyed;
    }


    public int getTotalScore() {
        return totalScore;
    }


    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }


    public int getCurrentDamage() {
        return currentDamage;
    }


    public void setCurrentDamage(int currentDamage) {
        this.currentDamage = currentDamage;
    }

    public LevelsContainer getCurrentlevel() {
        return currentlevel;
    }

    public void setCurrentlevel(LevelsContainer currentlevel) {
        this.currentlevel = currentlevel;
    }

    public int getMaxAsteroids() {
        return maxAsteroids;
    }


    public void setMaxAsteroids(int maxAsteroids) {
        this.maxAsteroids = maxAsteroids;
    }


    public int getMaxMissiles() {
        return maxMissiles;
    }


    public void setMaxMissiles(int maxMissiles) {
        this.maxMissiles = maxMissiles;
    }

    public void initWorld() {
        this.world = myGame.getGameScreen().getWorld();
    }

    public void doDamage() {
//        this.currentDamage += 1;
//        Gdx.app.log("scoreKeeper" , "health = " + (maxHealth - currentDamage));
        if (currentDamage >= maxHealth) {
            world.getScreen().setState(ScreenState.GAMEOVER);
        }
    }

    public void scoreHit(Asteroid rock) {
        int type = rock.specialAction();


        switch (type) {
            case 0:
                totalScore += 10;
                break;
            case 1:
                totalScore += 15;
                break;
        }
        Gdx.app.log("ScoreKeeper", "Current Score:" + totalScore);
    }

    public void resetDamage() {
        this.currentDamage = 0;
    }

    public void advanceLevel(){
        for(LevelsContainer level : LevelsContainer.values()){
            level.ordinal();
        }
    }





}
