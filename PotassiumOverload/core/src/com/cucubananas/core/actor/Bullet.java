package com.cucubananas.core.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Represents the bullet that can be fired from both the {@link Enemy}Enemy or {@link Player} objects.
 *
 * @author Aidan Reed
 * @author Alexis Araouzous
 */
public class Bullet extends Projectile {
    public static final float SPEED = 5;

    public Bullet(float x, float y, String direction) {
        super("bullet.png", x, y, 0);
        xState = direction;
        hitbox.width = 13;
        hitbox.height = 8;
    }

    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(texture, getX(), getY(), 13, 8, 0, 0, 50, 30, DIR_TO_ROTATION.get(xState), false);
    }

}
