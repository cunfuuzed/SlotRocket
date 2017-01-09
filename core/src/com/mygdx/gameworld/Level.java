package com.mygdx.gameworld;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 11/16/2016.
 */

public class Level {

    private Map asteroidPoints;
    private float spawnRate;
    private float fallSpeed;
    private int clearPoints;

    public Level (float spawnRate, float fallSpeed, int clearPoints, Class[] types, int[] points){

        asteroidPoints = new HashMap<Class, Integer>();
        this.spawnRate = spawnRate;
        this.fallSpeed = fallSpeed;
        this.clearPoints = clearPoints;
        if (types.length != points.length){
            throw new RuntimeException("types of asteroids don't correspond to points in level");
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

    public int getPointValue(Class type){

        Integer value = (Integer) asteroidPoints.get(type);
        return value.intValue();
    }

    public int getClearPoints() {
        return clearPoints;
    }
}
