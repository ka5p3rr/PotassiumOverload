package com.cucubananas.core.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.FileTextureData;
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

    protected static final Map<facingDirections, Boolean> DIR_TO_ROTATION = new HashMap<>();

    static {
        DIR_TO_ROTATION.put(facingDirections.left, true);
        DIR_TO_ROTATION.put(facingDirections.right, false);
    }

    public enum facingDirections {left, right;}

    private facingDirections direction;
    private Texture texture;
    private float health;

    public MoveableObject(String texturePath, float xPos, float yPos, float health) {
        texture = new Texture(texturePath);
        direction = facingDirections.right;
        this.setX(xPos);
        this.setY(yPos);
        this.setHealth(health);
        setBounds(xPos, yPos, texture.getWidth(), texture.getHeight());
    }

    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(texture, this.getX(), this.getY(), (float) texture.getWidth(), (float) texture.getHeight(), (int) this.getOriginX(), (int) this.getOriginY(),
                (int) this.getWidth(), (int) this.getHeight(), DIR_TO_ROTATION.get(direction), false);
    }

    public facingDirections getDirection() {
        return direction;
    }

    public void setDirection(facingDirections direction) {
        this.direction = direction;
    }

    public void setHealth(float health){
        this.health = health;
    }

    public float getHealth(){
        return health;
    }

    public String getTextureName() {
        return ((FileTextureData) texture.getTextureData()).getFileHandle().path();
    }

}