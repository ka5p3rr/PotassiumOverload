package com.cucubananas.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.cucubananas.core.actor.Missile;
import com.cucubananas.core.actor.MoveableObject;
import com.cucubananas.core.actor.Player;
import com.cucubananas.core.actor.Projectile;

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

    public void checkMissileCollision(List<Missile> projectiles) {
        for (Actor a : this.getActors()) {
            if (a instanceof Missile) {
                if(player.checkCollision((Missile) a)) {
                    this.getRoot().removeActor(a);
                    projectiles.remove(a);
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

    public void moveMissiles(Integer counter, int range, List<Missile> missiles) {
        for (Actor a : this.getActors()) {
            if (a instanceof Missile) {
                if (counter % 10 == 0) ((Missile) a).setWeight(GameScreen.calculateWeight(range));
                switch (((Projectile) a).getDirection()) {
                    case MoveableObject.FACING_DIRECTIONS_LEFT:
                        a.setX(a.getX() - 2);
                        a.setY(a.getY() + getRandomYVariation((Missile) a, counter));
                        if(a.getY() + ((Missile) a).getTextureHeight() >= Gdx.graphics.getHeight()) a.setY(((float) Gdx.graphics.getHeight()) - ((Missile) a).getTextureHeight());
                        if(a.getY() <= 0) a.setY(0);
                        if (a.getX() <= 0 || a.getX() >= Gdx.graphics.getWidth()) {
                            this.getRoot().removeActor(a);
                            missiles.remove(a);
                        }

                            break;
                    case MoveableObject.FACING_DIRECTIONS_RIGHT:
                        a.setX(a.getX() + 2);
                        a.setY(a.getY() + getRandomYVariation((Missile) a, counter));
                        if(a.getY() + ((Missile) a).getTextureHeight() >= Gdx.graphics.getHeight()) a.setY(((float) Gdx.graphics.getHeight())  - ((Missile) a).getTextureHeight());
                        if(a.getY() <= 0) a.setY(0);
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

    @Override
    public void addActor(Actor actor) {
        if (actor instanceof Player) player = (Player) actor;
        super.addActor(actor);
    }

    private float getRandomYVariation(Missile m, Integer counter) {
        return (float) ((Math.sin(counter * 0.1)) * m.getWeight());
    }

}
