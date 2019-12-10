package com.cucubananas.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.cucubananas.core.actor.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomStage extends Stage {
    private List<Missile> missilesToRemove;
    private List<Bullet> bulletsToRemove;

    private Player player;
    private List<Missile> missiles;
    private List<Bullet> bullets;

    public CustomStage(List<Missile> missiles, List<Bullet> bullets) {
        super();
        this.missiles = missiles;
        this.bullets = bullets;
        missilesToRemove = new ArrayList<>();
        bulletsToRemove = new ArrayList<>();
    }

    @Override
    public void addActor(Actor actor) {
        if (actor instanceof Player) player = (Player) actor;
        super.addActor(actor);
    }

    public boolean updateGameState() {
        moveMissiles();
        moveBullets();
        checkPlayerToMissileCollision();
        checkBulletToMissileCollision();

        disposeObjects();

        // Increase score
        player.setScore(player.getScore() + 1);

        // Make game harder by increasing randomisation factor, as well as the number of missiles and their health
        GameScreen.randomisationCounter++;
        GameScreen.missileHealth += 0.001;
        calculateEnemies();

        // TODO uncomment once Collectibles are implemented
         /*
        checkPlayerToCollectiblesCollision();
         */

        if (player.getHealth() <= 0) return false;
        else return true;
    }

    private void checkPlayerToMissileCollision() {
        for (Missile a : missiles) {
            if (player.checkCollision(a)) {
                player.setHealth(player.getHealth() - 10);
                missilesToRemove.add(a);
            }
        }
    }

    private void checkBulletToMissileCollision() {
        missiles.forEach(m -> bullets.forEach(b -> {
            if (m.checkCollision(b)) {
                m.setHealth(m.getHealth() - 10);
                if (m.getHealth() <= 0) {
                    missilesToRemove.add(m);
                    player.setScore(player.getScore() + 50);
                }
                bulletsToRemove.add(b);
            }
        }));
    }

        /*
        //TODO uncomment once Projectile and Collectibles are implemented
        public void checkCollectiblesCollision() {
            for (Actor a : this.getActors()) {
                if (a instanceof Collectible) {
                    player.checkCollision((MoveableObject) a);
                }
            }
        }
          */

    private void moveMissiles() {
        for (Missile a : missiles) {
            if (GameScreen.randomisationCounter % 10 == 0)
                a.setWeight(GameScreen.calculateWeight(GameScreen.randomisationRange));
            switch (a.getDirection()) {
                case MoveableObject.FACING_DIRECTIONS_LEFT:
                    a.setX(a.getX() - 2);
                    a.setY(a.getY() + getRandomYVariation(a, GameScreen.randomisationCounter));
                    if (a.getY() + a.getTextureHeight() >= Gdx.graphics.getHeight())
                        a.setY(((float) Gdx.graphics.getHeight()) - a.getTextureHeight());
                    if (a.getY() <= 0) a.setY(0);
                    if (a.getX() <= 0 || a.getX() >= Gdx.graphics.getWidth()) {
                        missilesToRemove.add(a);
                    }
                    break;
                case MoveableObject.FACING_DIRECTIONS_RIGHT:
                    a.setX(a.getX() + 2);
                    a.setY(a.getY() + getRandomYVariation(a, GameScreen.randomisationCounter));
                    if (a.getY() + a.getTextureHeight() >= Gdx.graphics.getHeight())
                        a.setY(((float) Gdx.graphics.getHeight()) - a.getTextureHeight());
                    if (a.getY() <= 0) a.setY(0);
                    if (a.getX() <= 0 || a.getX() >= Gdx.graphics.getWidth()) {
                        missilesToRemove.add(a);
                    }
                    break;
            }
        }
    }

    private void moveBullets() {
        bullets.forEach(a -> {
            switch (a.getDirection()) {
                case MoveableObject.FACING_DIRECTIONS_LEFT:
                    a.setX(a.getX() - Bullet.SPEED);
                    if (a.getY() <= 0) a.setY(0);
                    if (a.getX() <= 0 || a.getX() >= Gdx.graphics.getWidth()) {
                        bulletsToRemove.add(a);
                    }
                    break;
                case MoveableObject.FACING_DIRECTIONS_RIGHT:
                    a.setX(a.getX() + Bullet.SPEED);
                    if (a.getY() <= 0) a.setY(0);
                    if (a.getX() <= 0 || a.getX() >= Gdx.graphics.getWidth()) {
                        bulletsToRemove.add(a);
                    }
                    break;
            }
        });
    }

    private float getRandomYVariation(Missile m, Integer counter) {
        return (float) ((Math.sin(counter * 0.1)) * m.getWeight());
    }

    private void calculateEnemies() {
        GameScreen.numberOfEnemies = player.getScore() / 400;
    }

    private void disposeObjects() {
        missilesToRemove.forEach(m -> {
            missiles.remove(m);
            this.getRoot().removeActor(m);
        });

        bulletsToRemove.forEach(b -> {
            bullets.remove(b);
            this.getRoot().removeActor(b);
        });

        missilesToRemove.clear();
        bulletsToRemove.clear();

        System.gc();
    }
}
