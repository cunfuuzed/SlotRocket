package com.mygdx.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.mygdx.gameworld.GameWorld;

public class Battery {

	private GameWorld myWorld;
	private Generator generator;
	private Ground ground;
	private Block launcher;
	private float gapWidth;
	private int[] launchGap;
	private Vector2 missileWidth;
	private Vector2 roidWidth;
	private float missileVelocity;
	

	//all render calls for missiles are made on the liveMissiles array
	private final Array<Missile> liveMissiles = new Array<Missile>(false, 16);

	// missilePool is a gdx.util Pool of objects that can be reused to avoid
	//making new objects that have to be garbage collected later
	private final Pool<Missile> missilePool = new Pool<Missile>(36) {
		@Override
		protected Missile newObject() {
			return new Missile(myWorld);
		}
	};

	public Battery(GameWorld myWorld) {
		this.myWorld = myWorld;
		generator = myWorld.getGenerator();
		ground = myWorld.getGround();
		launcher = myWorld.getLauncher();
		gapWidth = myWorld.getGapWidth();
		launchGap = new int[4];
		missileWidth = new Vector2();
		roidWidth = new Vector2();
		missileVelocity = -100;

	}

	public void update(float delta) {
		// updates live missiles, deletes destroyed ones
		Missile item;
		if (liveMissiles.size > 0) {
			for (int i = 0; i < liveMissiles.size; i++) {
				item = (Missile) liveMissiles.get(i);

				if (item.isAlive()) {
					item.update(delta);

				} else {// if missile is dead, removes it from array
					liveMissiles.removeIndex(i);
					missilePool.free(item);
				}
			}
		}
	}

	private void shapeGap() {
		// translates launcher into one dimetional integer array, stores in
		// launchGap
		for (int i = 0; i < launcher.getRows(); i++) {
			int height = 0;
			for (int j = 0; j < launcher.getColumns(); j++) {
				if (launcher.getCellVis(i, j) == true) {
					height += 1;
				}
			}
			launchGap[i] = height;
		}
	}

	private boolean fits(int[] missleShape, int[] roidShape) {
		boolean result = true;
		for (int i = 0; i < 4; i++) {
			if (missleShape[i] > 0) {
				missileWidth.x = i;
				break;
			}
		}
		for (int i = 3; i > -1; i--) {
			if (missleShape[i] > 0) {
				missileWidth.y = i;
				break;
			}
		}
		for (int i = 0; i < 4; i++) {
			if (roidShape[i] > 0) {
				roidWidth.x = i;
				break;
			}
		}
		for (int i = 3; i > -1; i--) {
			if (roidShape[i] > 0) {
				roidWidth.y = i;
				break;
			}
		}
		if ((missileWidth.y - missileWidth.x) != (roidWidth.y - roidWidth.x)) {
			result = false;
		} else {
			for (int i = (int) missileWidth.x; i < (int) missileWidth.y + 1; i++) {
				for (int j = (int) roidWidth.x; i < (int) roidWidth.y + 1; i++) {
					if (missleShape[i] != roidShape[j]) {
						result = false;
						break;
					}
				}
			}
		}

		return result;

	}

	public void fire(int index) {
		boolean fits = false;
		Missile item = missilePool.obtain();
		item.setPosition(generator.getAsteroids().get(index).getPosition().x
				- missileWidth.x * gapWidth, ground.getBounds().y - 4 * gapWidth);
		shapeGap();
		item.setGap(launchGap);
		fits = fits(launchGap, generator.getAsteroids().get(index).getGap());
		if (fits) {
			item.setFits(true);
		}
		item.setAlive(true);
		item.setVelocity(this.missileVelocity);
		liveMissiles.add(item);

	}

	public Array<Missile> getMissles() {
		return liveMissiles;
	}

}
