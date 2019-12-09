package com.cucubananas.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.cucubananas.core.actor.*;

import java.util.ArrayList;
import java.util.List;

public class CustomStage extends Stage {
    Player player;

    public void updateHitboxes() {
        for (Actor a : this.getActors()) {
            if (a instanceof MoveableObject) {
                ((MoveableObject) a).updateHitbox();
            }
        }
    }

    public void checkPlayerToMissileCollision(List<Missile> missiles) {
        for (Actor a : this.getActors()) {
            if (a instanceof Missile) {
                if (player.checkCollision((Missile) a)) {
                    this.getRoot().removeActor(a);
                    missiles.remove(a);
                }
            }
        }
        System.gc();
    }

    public void checkBulletToMissileCollision(List<Missile> missiles, List<Bullet> bullets) {
        for (int i = 0; i < this.getActors().size; i++) {
            Actor a = this.getActors().get(i);
            if (a instanceof Missile) {
                for (int x = 0; x < this.getActors().size; x++) {
                    Actor b = this.getActors().get(x);
                    if (b instanceof Bullet) {
                        if (((Missile) a).checkCollision((Bullet) b)) {
                            this.getRoot().removeActor(a);
                            missiles.remove(a);
                            this.getRoot().removeActor(b);
                            bullets.remove(b);
                        }
                    }
                }
            }
        }
        System.gc();
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

    public void moveMissiles(Integer counter, int range, List<Missile> missiles) {
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
        System.gc();
    }

    public void moveBullets(Integer counter, int range, List<Bullet> bullets) {
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
        System.gc();
    }

    @Override
    public void addActor(Actor actor) {
        if (actor instanceof Player) player = (Player) actor;
        super.addActor(actor);
    }

    private float getRandomYVariation(Missile m, Integer counter) {
        return (float) ((Math.sin(counter * 0.1)) * m.getWeight());
    }

}
