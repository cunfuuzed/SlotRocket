package com.mygdx.gameworld;

public class ScoreKeeper {
	
	private int asteroidsDestroyed;
	private int totalScore;
	private int level;
	private int damage; 
	private GameWorld world;
	private int maxAsteroids;
	private int maxMissiles;
	
	
	public ScoreKeeper(GameWorld world){
		
		asteroidsDestroyed = 0;
		totalScore = 0;
		level = 1;
		damage = 0;
		maxAsteroids = 0;
		maxMissiles = 0;
		
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


	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}


	public int getDamage() {
		return damage;
	}


	public void setDamage(int damage) {
		this.damage = damage;
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

}
