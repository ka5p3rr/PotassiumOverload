package com.cucubananas.core.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Represents the base object of all {@link MoveableObject} in the game. All {@link MoveableObject}
 * objects must have a texture, x and y coordinates.
 * @author Aidan Reed
 * @author Alexis Araouzous
 */
public abstract class MoveableObject extends Actor {
    Texture texture;

    public MoveableObject (String texturePath, float xPos, float yPos){
        texture = new Texture(texturePath);
        this.setX(xPos);
        this.setY(yPos);
        setBounds(xPos, yPos, texture.getWidth(), texture.getHeight());
    }

    @Override
    public void draw(Batch batch, float alpha){
        batch.draw(texture, this.getX(), this.getY());
    }
}