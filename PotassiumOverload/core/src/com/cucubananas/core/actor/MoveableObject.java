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
    int x,y;
    Texture texture;

    public MoveableObject (String texturePath, int xPos, int yPos){
        texture = new Texture(texturePath);
        x = xPos;
        y = yPos;
        setBounds(x, y, texture.getWidth(), texture.getHeight());
    }

    @Override
    public void draw(Batch batch, float alpha){
        batch.draw(texture, x, y);
    }
}