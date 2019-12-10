package com.cucubananas.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.cucubananas.core.actor.*;

import java.util.List;

public class CustomStage extends Stage {
    Player player;
    List<Missile> missiles;
    List<Bullet> bullets;

    public CustomStage(  List<Missile> missiles, List<Bullet> bullets) {
        super();
        this.missiles = missiles;
        this.bullets = bullets;
    }

    @Override
    public void addActor(Actor actor) {
        if (actor instanceof Player) player = (Player) actor;
        super.addActor(actor);
    }

    public boolean updateGameState(Integer counter, int range, List<Bullet> bullets, List<Missile> missiles) {
        moveMissiles(counter, range, missiles);
        moveBullets(counter, range, bullets);
        updateHitboxes();
        checkPlayerToMissileCollision(missiles);
        checkBulletToMissileCollision(missiles, bullets);

        // Increase score
        player.setScore(player.getScore() + 1);

        // Make game harder by increasing randomisation, number of missiles and their health
        GameScreen.randomisationCounter++;
        GameScreen.missileHealth += 0.001;
        calculateEnemies();
        // TODO uncomment once Collectibles are implemented
         /*
        checkPlayerToCollectiblesCollision();
         */

        System.gc();

        if(player.getHealth() <= 0 ) return false;
        else return true;
    }

    private void updateHitboxes() {
        for (Actor a : this.getActors()) {
            if (a instanceof MoveableObject) {
                ((MoveableObject) a).updateHitbox();
            }
        }
    }

    private void checkPlayerToMissileCollision(List<Missile> missiles) {
        for (Actor a : this.getActors()) {
            if (a instanceof Missile) {
                if (player.checkCollision((Missile) a)) {
                    player.setHealth(player.getHealth() - 10);
                    this.getRoot().removeActor(a);
                    missiles.remove(a);
                }
            }
        }
    }

    private void checkBulletToMissileCollision(List<Missile> missiles, List<Bullet> bullets) {
        for (int i = 0; i < this.getActors().size; i++) {
            Actor a = this.getActors().get(i);
            if (a instanceof Missile) {
                for (int x = 0; x < this.getActors().size; x++) {
                    Actor b = this.getActors().get(x);
                    if (b instanceof Bullet) {
                        if (((Missile) a).checkCollision((Bullet) b)) {
                            ((Missile) a).setHealth(((Missile) a).getHealth() - 10);
                            if (((Missile) a).getHealth() <= 0) {
                                this.getRoot().removeActor(a);
                                missiles.remove(a);
                                player.setScore(player.getScore() + 50);
                            }
                            this.getRoot().removeActor(b);
                            bullets.remove(b);
                        }
                    }
                }
            }
        }
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

    private void moveMissiles(Integer counter, int range, List<Missile> missiles) {
        for (Actor a : this.getActors()) {
            if (a instanceof Missile) {
                if (counter % 10 == 0) ((Missile) a).setWeight(GameScreen.calculateWeight(range));
                switch (((Missile) a).getDirection()) {
                    case MoveableObject.FACING_DIRECTIONS_LEFT:
                        a.setX(a.getX() - 2);
                        a.setY(a.getY() + getRandomYVariation((Missile) a, counter));
                        if (a.getY() + ((Missile) a).getTextureHeight() >= Gdx.graphics.getHeight())
                            a.setY(((float) Gdx.graphics.getHeight()) - ((Missile) a).getTextureHeight());
                        if (a.getY() <= 0) a.setY(0);
                        if (a.getX() <= 0 || a.getX() >= Gdx.graphics.getWidth()) {
                            this.getRoot().removeActor(a);
                            missiles.remove(a);
                        }

                        break;
                    case MoveableObject.FACING_DIRECTIONS_RIGHT:
                        a.setX(a.getX() + 2);
                        a.setY(a.getY() + getRandomYVariation((Missile) a, counter));
                        if (a.getY() + ((Missile) a).getTextureHeight() >= Gdx.graphics.getHeight())
                            a.setY(((float) Gdx.graphics.getHeight()) - ((Missile) a).getTextureHeight());
                        if (a.getY() <= 0) a.setY(0);
                        if (a.getX() <= 0 || a.getX() >= Gdx.graphics.getWidth()) {
                            this.getRoot().removeActor(a);
                            missiles.remove(a);
                        }
                        break;
                }
            }

        }
    }

    private void moveBullets(Integer counter, int range, List<Bullet> bullets) {
        for (Actor a : this.getActors()) {
            if (a instanceof Bullet) {
                switch (((Bullet) a).getDirection()) {
                    case MoveableObject.FACING_DIRECTIONS_LEFT:
                        a.setX(a.getX() - Bullet.SPEED);

                        if (a.getY() <= 0) a.setY(0);
                        if (a.getX() <= 0 || a.getX() >= Gdx.graphics.getWidth()) {
                            this.getRoot().removeActor(a);
                            bullets.remove(a);
                        }

                        break;
                    case MoveableObject.FACING_DIRECTIONS_RIGHT:
                        a.setX(a.getX() + Bullet.SPEED);

                        if (a.getY() <= 0) a.setY(0);
                        if (a.getX() <= 0 || a.getX() >= Gdx.graphics.getWidth()) {
                            this.getRoot().removeActor(a);
                            bullets.remove(a);
                        }
                        break;
                }
            }
        }
    }

    private float getRandomYVariation(Missile m, Integer counter) {
        return (float) ((Math.sin(counter * 0.1)) * m.getWeight());
    }

    private void calculateEnemies() {
        GameScreen.numberOfEnemies = player.getScore() / 400;
    }

}
