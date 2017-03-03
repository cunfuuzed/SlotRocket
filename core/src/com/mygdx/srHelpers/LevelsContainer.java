package com.mygdx.srHelpers;

import com.badlogic.gdx.Gdx;
import com.mygdx.gameobjects.Asteroid;
import com.mygdx.gameobjects.BombAsteroid;
import com.mygdx.gameworld.Level;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 11/16/2016.
 */

public enum LevelsContainer {


    LEVEL1(2.0f, Gdx.graphics.getWidth()/8, 100, new Class[]{Asteroid.class},
            new int[] {10}, new float[] {1.0f}),
    LEVEL2(2.0f, Gdx.graphics.getWidth()/8, 200, new Class[]{Asteroid.class, BombAsteroid.class},
            new int[] {10, 15}, new float[] {0.5f, 0.5f});
//    LEVEL3,
//    LEVEL4,
//    LEVEL5,
//    LEVEL6;

    private final Map asteroidPoints;
    private final float spawnRate;
    private final float fallSpeed;
    private final int clearPoints;
    private final float[] typeProbabilities;

    LevelsContainer (float spawnRate, float fallSpeed, int clearPoints, Class[] types,
                     int[] points, float[] typeProbabilities){

        this.asteroidPoints = new HashMap<Class, Integer>();
        this.spawnRate = spawnRate;
        this.fallSpeed = fallSpeed;
        this.clearPoints = clearPoints;
        this.typeProbabilities = typeProbabilities;
        if (types.length != typeProbabilities.length){
            throw new RuntimeException("number of spawn probabilities doesn't match number of asteroid types.");
        }
        if (types.length != points.length){
            throw new RuntimeException("types of asteroids don't correspond to points for each type of asteroid");
        }
        for(Class element : types){
            for(int number : points){
                asteroidPoints.put(element, Integer.valueOf(number));
            }
        }

    }

    public float getSpawnRate() {
        return spawnRate;
    }

    public float getFallSpeed() {
        return fallSpeed;
    }

    public int getClearPoints() {
        return clearPoints;
    }

    public int getPointValue(Class type){

        Integer value = (Integer) asteroidPoints.get(type);
        return value.intValue();
    }
}
