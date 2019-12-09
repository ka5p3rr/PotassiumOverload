package com.cucubananas.core.actor;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.FileTextureData;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the base object of all {@link MoveableObject} in the game. All {@link MoveableObject}
 * objects must have a texture, x and y coordinates.
 *
 * @author Aidan Reed
 * @author Alexis Araouzous
 */
public abstract class MoveableObject extends Actor {

    float x, y;
    Texture texture;
    Rectangle hitbox;
    private FACING_DIRECTIONS direction;
    private float health;

    protected static final Map<FACING_DIRECTIONS, Boolean> DIR_TO_ROTATION = new HashMap<>();

    static {
        DIR_TO_ROTATION.put(FACING_DIRECTIONS.left, true);
        DIR_TO_ROTATION.put(FACING_DIRECTIONS.right, false);
    }

    public enum FACING_DIRECTIONS {left, right;}

    public MoveableObject(String texturePath, float xPos, float yPos, float health, int width, int height) {
        this(texturePath, xPos, yPos, health);
        hitbox.width = width;
        hitbox.height = height;
    }

    public MoveableObject(String texturePath, float xPos, float yPos, float health) {
        texture = new Texture(texturePath);
        direction = FACING_DIRECTIONS.right;
        this.setX(xPos);
        this.setY(yPos);
        x = xPos;
        y = yPos;  
        this.setHealth(health);
        hitbox = new Rectangle();
        hitbox.x = xPos;
        hitbox.y = yPos;
        hitbox.width = texture.getWidth();
        hitbox.height = texture.getHeight();
        setBounds(x, y, texture.getWidth(), texture.getHeight());
    }

    @Override
    public void draw(Batch batch, float alpha){
        batch.draw(texture, this.getX(), this.getY(), (float) texture.getWidth(), (float) texture.getHeight(), (int) this.getOriginX(), (int) this.getOriginY(),
                (int) this.getWidth(), (int) this.getHeight(), DIR_TO_ROTATION.get(direction), false);
    }

    public void updateHitbox() {
        hitbox.x = this.getX();
        hitbox.y = this.getY();
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public FACING_DIRECTIONS getDirection() {
        return direction;
    }

    public void setDirection(FACING_DIRECTIONS direction) {
        this.direction = direction;
    }

    public void setHealth(float health){
        this.health = health;
    }

    public float getHealth(){
        return health;
    }

    public int getTextureHeight() {
        return texture.getHeight();
    }

    public String getTextureName() {
        return ((FileTextureData) texture.getTextureData()).getFileHandle().path();
    }

    public boolean checkCollision(MoveableObject mo) {
        return this.getHitbox().overlaps(mo.getHitbox());
    }

    public void setHitboxWidth() {

    }

    public void setHitboxHeight() {

    }
}