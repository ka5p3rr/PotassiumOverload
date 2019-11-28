package com.cucubananas.core.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class MoveableObject extends Actor {
  int x,y;
  Texture texture;

  public MoveableObject (String texturePath, int xPos, int yPos){
    texture = new Texture(texturePath);
    x = xPos;
    y = yPos;
  }

  @Override
  public void draw(Batch batch, float alpha){
    batch.draw(texture, x, y);
  }
}
