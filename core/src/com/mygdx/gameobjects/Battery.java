package com.mygdx.gameobjects;

import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector;
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
    private Vector2 tempCenter;
    private Vector2 tempCenter2;
    private float missileVelocity;
    private int instantFitsLeft;


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
        tempCenter = new Vector2();
        tempCenter2 = new Vector2();
//        missileVelocity = -100;
        missileVelocity = -Gdx.graphics.getHeight()/5;
        instantFitsLeft = 0;

    }

    public void update(float delta) {
        // updates live missiles, deletes destroyed ones
        Missile missile;
        boolean fitsonCollision = false;
        if (liveMissiles.size > 0) {
            for (int i = 0; i < liveMissiles.size; i++) {
                missile = (Missile) liveMissiles.get(i);

                if (missile.isAlive()) {
                    missile.update(delta);

                    // check loop missile fitting gap on asteroid collision
                    for(int j = 0; j < myWorld.getGenerator().getAsteroids().size; j++){
                        fitsonCollision = false;
                        Asteroid rock = (Asteroid) myWorld.getGenerator().getAsteroids().get(j);
                        if(rock.getBounds().contains(missile.getBounds())){
//                            Gdx.app.log("Battery", "missile inside rock");
                            fitsonCollision = fits(missile.getGap(),rock.getGap());
//                            Gdx.app.log("Battery", "Fits on collision = " + fitsonCollision);
                            if(fitsonCollision){
                                actionOnHit(rock); //performs special action if needed
                                rock.normalDamage(); // damages rock
                                missile.setAlive(false); // kills missile on fits() = true
                                if(!rock.isAlive()){
//                                    myWorld.getScreen().getMyGame().getScoreKeeper().scoreHit(rock);
                                }
                            }
                        }
                    }


                } else {// if missile is dead, removes it from array
                    liveMissiles.removeIndex(i);
                    missilePool.free(missile);
                }
            }
        }
    }

    private void shapeGap() {
        // translates launcher into one dimensional integer array, stores in
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
    // takes a missile and asteroid gap and returns true if the missile fits asteroid
    private boolean fits(int[] missleShape, int[] roidShape) {
        boolean result;
        if(Arrays.equals(missleShape, roidShape)){
            result = true;
        }else{
            result = false;
        }

        return result;

    }
    // fire method called when user clicks an an asteroid to fire a missile at that
    //specific asteroid
    public void fire(int index) {
        boolean fits;
        Missile item = missilePool.obtain();
        item.setPosition(generator.getAsteroids().get(index).getPosition().x
                - missileWidth.x * gapWidth, ground.getBounds().y - 4 * gapWidth);
        if(instantFitsLeft <= 0){
        shapeGap();
        item.setGap(launchGap);}
        else{
            item.setGap(generator.getAsteroids().get(index).getGap());
            if(instantFitsLeft > 0){
                instantFitsLeft -=1;
            }
        }
        fits = fits(launchGap, generator.getAsteroids().get(index).getGap());
        if (fits) {
            item.setFits(true);
        }
        item.setAlive(true);
        item.setVelocity(this.missileVelocity);
        liveMissiles.add(item);
        Gdx.app.log("Battery", "Fits at fire = " + fits);

    }

    public Array<Missile> getMissles() {
        return liveMissiles;
    }

    public void setInstantFitsLeft() {
        this.instantFitsLeft = this.instantFitsLeft +1;
        Gdx.app.log("Battery", this.instantFitsLeft + " Instant Fit Missiles");
    }

    //optional action to take on the asteroid that is hit
    private void actionOnHit(Asteroid rock){
        int actionType = rock.specialAction();

        switch (actionType){
            case 1:
                //case: asteroid hit is a bomb type, checks if any near asteroids are destroyed in blast
                BombAsteroid bombRock = (BombAsteroid) rock;
                bombRock.getBounds().getCenter(tempCenter);
                for(int j = 0; j < myWorld.getGenerator().getAsteroids().size; j++){
                    Asteroid target = (Asteroid) myWorld.getGenerator().getAsteroids().get(j);
                    target.getBounds().getCenter(tempCenter2);
                    if(tempCenter.dst(tempCenter2) > 0.0f && tempCenter.dst(tempCenter2) <
                            bombRock.getRadius()){
                        target.setHealth(-1);

                    }
                }
                break;
        }
    }

    public void reset(){

        liveMissiles.clear();
    }

}
