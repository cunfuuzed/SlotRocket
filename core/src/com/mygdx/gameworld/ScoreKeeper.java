package com.mygdx.gameworld;

import com.badlogic.gdx.Gdx;
import com.mygdx.gameobjects.Asteroid;
import com.mygdx.gameobjects.BombAsteroid;
import com.mygdx.slotrocket.SRGame;
import com.mygdx.srHelpers.ScreenState;

public class ScoreKeeper {
	
	private int asteroidsDestroyed;
	private int totalScore;
	private Level currentlevel;
	private int currentDamage;
	private SRGame myGame;
	private int maxAsteroids;
	private int maxMissiles;
	private int maxHealth;
	private GameWorld world;
	private Level level_1;
	private Level level_2;

	private float spawnRates[] = {
			2.0f,
			2.2f,
			2.5f
	};

	private float fallSpeeds[] = {
			Gdx.graphics.getWidth()/6,
			Gdx.graphics.getWidth()/5.5f,
			Gdx.graphics.getWidth()/5.0f
	};

	private int clearPoints[] = {
			100,
			200,
			300
	};

	private Class asteroidTypes[] = {
			Asteroid.class,
			BombAsteroid.class
	};

	private int asteroidPoints[] = {
			10,
			20
	};

	
	
	public ScoreKeeper(SRGame myGame){
		
		asteroidsDestroyed = 0;
		totalScore = 0;
		initLevels();
		currentlevel = level_1;
		currentDamage = 0;
		maxAsteroids = 0;
		maxMissiles = 0;
		this.myGame = myGame;
		maxHealth = 5;

		
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

	public Level getCurrentlevel() {
		return currentlevel;
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

	public void initWorld(){
		this.world = myGame.getGame().getWorld();
	}

	public void doDamage(){
		this.currentDamage += 1;
		Gdx.app.log("scoreKeeper" , "health = " + (maxHealth - currentDamage));
		if(currentDamage >= maxHealth){
			world.getScreen().setState(ScreenState.GAMEOVER);
		}
	}
	public void resetDamage(){
		this.currentDamage = 0;
	}

	private void initLevels(){


		this.level_1 = new Level(spawnRates[1], fallSpeeds[1], clearPoints[1],
				copyClasses(1, asteroidTypes), copyPoints(1, asteroidPoints));

		this.level_2 = new Level(spawnRates[2], fallSpeeds[2], clearPoints[2],
				copyClasses(2, asteroidTypes), copyPoints(2, asteroidPoints));

	}

	private Class[] copyClasses(int numTypes, Class[] toBeCopied){
		Class[] classesCopy = new Class[numTypes];

		System.arraycopy(toBeCopied, 0, classesCopy, 0, numTypes);

		return classesCopy;
	}

	private int[] copyPoints(int numTypes, int[] toBeCopied){
		int[] classesCopy = new int[numTypes];

		System.arraycopy(toBeCopied, 0, classesCopy, 0, numTypes);

		return classesCopy;
	}

}
