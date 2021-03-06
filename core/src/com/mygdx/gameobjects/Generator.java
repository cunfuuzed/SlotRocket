package com.mygdx.gameobjects;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.mygdx.gameworld.GameWorld;
import com.mygdx.gameworld.ScoreKeeper;

public class Generator {

    private GameWorld myWorld;
    private float rockWidth;
    public float screenWidth;
    private float spawnInterval;
    private float spawnRate;
    private float fallSpeed;
    private Random randomizer;
    private float runTime = 0.0f;
    private float releaseTime = 0.0f;
    private ScoreKeeper scoreKeeper;
    private Vector2 burstPos;


    private final Array<Asteroid> liveAsteroids = new Array<Asteroid>(false, 16);

    private final Array<Vector2> bursts = new Array<Vector2>(false, 4);

    private final Array<Explosion> explosions = new Array<Explosion>(false, 10);

    private final Pool<Explosion> explosionPool = new Pool(10) {

        @Override
        protected Object newObject() {
            return new Explosion(0.03f, myWorld.getScreen().getExplosionFrames());
        }
    };

    private final Pool<Vector2> burstPool = new Pool(10) {
        @Override
        protected Object newObject() {
            return new Vector2();
        }
    };

    private final Pool<Asteroid> asteroidPool = new Pool(36) {
        @Override
        protected Asteroid newObject() {
            return new Asteroid(rockWidth);
        }
    };

    private final Pool<BombAsteroid> bombPool = new Pool(36) {
        @Override
        protected BombAsteroid newObject() {
            return new BombAsteroid(rockWidth);
        }
    };


    public Generator(GameWorld world) {

        this.spawnInterval = 3.0f;
        this.spawnRate = 2.0f;
        this.randomizer = new Random();
        this.myWorld = world;
        this.rockWidth = world.getRockWidth();
        this.screenWidth = world.getScreenWidth();
        this.burstPos = new Vector2();
//        this.fallSpeed = myWorld.getGround().getBounds().y / 6;
        this.scoreKeeper = myWorld.getScreen().getMyGame().getScoreKeeper();
//        this.fallSpeed = scoreKeeper.getCurrentlevel().getFallSpeed();


    }

    public void update(float delta) {
        // updates live asteroids
        Asteroid item;
        if (liveAsteroids.size > 0) {
            for (int i = 0; i < liveAsteroids.size; i++) { //loop for live asteroids
                //creates loop Asteroid object
                item = (Asteroid) liveAsteroids.get(i);
                //checks if the asteroid hits the ground and kills it and does damage if it does
                if (item.getBounds().overlaps(myWorld.getGround().getBounds())) {
                    item.instaKill();
                    scoreKeeper.doDamage();
//                    Gdx.app.log("Generator", item.getClass().toString());
                }
                if (item.isAlive()) {
                    item.update(delta);

                } else {

/*                  deprecated
*                   add a vector showing where an explosion should take place
                   Vector2 burst = burstPool.obtain(); */
//                  creates explosion object from a destroyed asteroid
//                    makeExplosion(item);
//                    liveAsteroids.removeIndex(i);
//                    // returns asteroid to it's respective pool type
//                    freeAsteroids(item);
                }
            }
            for (int i = 0; i <liveAsteroids.size ; i++) { //loop for dead asteroids
                item =  liveAsteroids.get(i);
                if(item.isAlive() == false){
                    makeExplosion(item);
                    myWorld.getScreen().getMyGame().getScoreKeeper().scoreHit(item);
                    liveAsteroids.removeIndex(i);
                    // returns asteroid to it's respective pool type
                    freeAsteroids(item);
                }
            }
        }
        // increase the total runTime by the frame render time: delta
        runTime += delta;
        // spawns first asteroid at time spawnInterval
//        if (releaseTime == 0.0f) {
//            releaseTime += spawnInterval;
//        }
//
//        if (runTime >= releaseTime) {
//
//            if (randomizer.nextFloat() > 0.5f) {
//                spawnBasic();
//            } else {
//                spawnBomb();
//            }
//        }

        spawn(runTime);

    }

    private void spawnBasic() {
        // spawn method specifc to the basic Asteroid type
        Asteroid item = asteroidPool.obtain(); //init method Asteroid object from pool in this class
        item.setHealth(1);
//        item.setType(1);
        if (liveAsteroids.size < 1) { //spawnBasic asteroid with no overlap check if it's the first one generated
            float x = (screenWidth - rockWidth) * randomizer.nextFloat();
            item.setPosition(x, -rockWidth - 2.0f);

        } else {// overlap checking
            boolean overlap;
            do {
                overlap = false;
                float x = (screenWidth - rockWidth) * randomizer.nextFloat();
                item.setPosition(x, -rockWidth - 2.0f);
                for (int i = 0; i < liveAsteroids.size; i++) {
                    if (liveAsteroids.get(i).getBounds()
                            .overlaps(item.getBounds())) {
                        overlap = true;
                    }
                }
            } while (overlap);

        }
        for (int i = 0; i < item.getGap().length; i++) {
            item.getGap()[i] = randomizer.nextInt(5);
        }


        item.setVelocity(makeFallSpeed());
        liveAsteroids.add(item);

        releaseTime += spawnInterval * randomizer.nextFloat();
//        Gdx.app.log("Generator" , item.getClass().getCanonicalName());
//        Gdx.app.log("Generator" , String.valueOf(item.specialAction()));
    }

    private void spawnBomb() {
        // spawn method specifc to the BombAsteroid type
        BombAsteroid item = bombPool.obtain();
        item.setHealth(1);
//        item.setType(1);
        item.setRadius(rockWidth * 2.0f);
        if (liveAsteroids.size < 1) {
            float x = (screenWidth - rockWidth) * randomizer.nextFloat();
            item.setPosition(x, -rockWidth - 2.0f);

        } else {// overlap checking
            boolean overlap;
            do {
                overlap = false;
                float x = (screenWidth - rockWidth) * randomizer.nextFloat();
                item.setPosition(x, -rockWidth - 2.0f);
                for (int i = 0; i < liveAsteroids.size; i++) {
                    if (liveAsteroids.get(i).getBounds()
                            .overlaps(item.getBounds())) {
                        overlap = true;
                    }
                }
            } while (overlap);

        }
        for (int i = 0; i < item.getGap().length; i++) {
            item.getGap()[i] = randomizer.nextInt(5);
        }


        item.setVelocity(makeFallSpeed());
        liveAsteroids.add(item);

        releaseTime += spawnInterval * randomizer.nextFloat();
//        Gdx.app.log("Generator" , item.getClass().getCanonicalName());
//        Gdx.app.log("Generator" , String.valueOf(item.specialAction()));
    }

    private void freeAsteroids(Asteroid item) {
        if (item.getClass() == Asteroid.class) {
            asteroidPool.free(item);
        } else {
            bombPool.free((BombAsteroid) item);
        }
    }

    public Array<Asteroid> getAsteroids() {
        return liveAsteroids;
    }

    public void reset() {

        liveAsteroids.clear();
        runTime = 0.0f;
        releaseTime = 0.0f;
    }

    public float makeFallSpeed() {
        return scoreKeeper.getCurrentlevel().getFallSpeed();
    }

    public Array<Vector2> getBursts() {
        return bursts;
    }

    public Array<Explosion> getExplosions() {
        return explosions;
    }

    public void explosionDone(Explosion burst) {
        explosionPool.free(burst);
    }

    /**
     * creates an explosion object from the explosion pool, at the center of
     * the destroyed asteroid
     * @param rock the asteroid that is destroyed
     */
    public void makeExplosion(Asteroid rock){

        rock.getBounds().getCenter(burstPos);
        Explosion blast = explosionPool.obtain();
        blast.setPosition(burstPos);
        explosions.add(blast);
    }

    private void spawn(float runtime){
        if (releaseTime == 0.0f) {
            releaseTime += spawnInterval;
        }

        if (runTime >= releaseTime) {

            if (randomizer.nextFloat() > 0.5f) {
                spawnBasic();
            } else {
                spawnBomb();
            }
        }
    }

}
