package com.mygdx.gameobjects;

import java.util.Random;

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
	

	private final Array<Asteroid> liveAsteroids = new Array<Asteroid>(false, 16);

	private final Pool<Asteroid> asteroidPool = new Pool<Asteroid>(36) {
		@Override
		protected Asteroid newObject() {
			return new Asteroid(rockWidth);
		}
	};

	private final Pool<BombAsteroid> bombPool = new Pool<BombAsteroid>(36) {
		@Override
		protected BombAsteroid newObject() { return new BombAsteroid(rockWidth);
		}
	};



	public Generator(GameWorld world) {

		this.spawnInterval = 2.0f;
		this.spawnRate = 2.0f;
		this.randomizer = new Random();
		this.myWorld = world;
		this.rockWidth = world.getRockWidth();
		this.screenWidth = world.getScreenWidth();
		this.fallSpeed = myWorld.getGround().getBounds().y / 6;
		this.scoreKeeper = myWorld.getScreen().getMyGame().getScoreKeeper();



		
	}

	public void update(float delta) {
		// updates live asteroids, deletes destroyed ones
		Asteroid item;
		if (liveAsteroids.size > 0) {
			for (int i = 0; i < liveAsteroids.size; i++) {
				//creates loop Asteroid object
				item = (Asteroid) liveAsteroids.get(i);
				//checks if the asteroid hits the ground and kills it if it does
				if (item.getBounds().overlaps(myWorld.getGround().getBounds())) {
					item.setHealth(0);
					scoreKeeper.doDamage();
				}
				if (item.isAlive()) {
					item.update(delta);

				}else {
					liveAsteroids.removeIndex(i);
					asteroidPool.free(item);
				}
			}
		}
		// increase the total runTime by the frame render time: delta
		runTime += delta;
		// spawns first asteroid at time spawnInterval
		if (releaseTime == 0.0f) {
			releaseTime += spawnInterval;
		}

		if (runTime >= releaseTime) {
			spawnBasic();
		}

	}

	private void spawnBasic() {
		// spawn method specifc to the basic Asteroid type
		Asteroid item = asteroidPool.obtain(); //init method Asteroid object from pool in this class
		item.setHealth(1);
		item.setType(1);
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
		for( int i = 0; i < item.getGap().length; i++){
			item.getGap()[i] = randomizer.nextInt(5);
		}
		

		item.setVelocity(fallSpeed);
		liveAsteroids.add(item);

		releaseTime += spawnInterval * randomizer.nextFloat();

	}

	private void spawnBomb() {
		// spawn method specifc to the BombAsteroid type
		BombAsteroid item = bombPool.obtain();
		item.setHealth(1);
		item.setType(1);
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
		for( int i = 0; i < item.getGap().length; i++){
			item.getGap()[i] = randomizer.nextInt(5);
		}


		item.setVelocity(fallSpeed);
		liveAsteroids.add(item);

		releaseTime += spawnInterval * randomizer.nextFloat();

	}

	public Array<Asteroid> getAsteroids() {
		return liveAsteroids;
	}

	public void reset(){

		liveAsteroids.clear();
		runTime = 0.0f;
		releaseTime = 0.0f;
	}

}
