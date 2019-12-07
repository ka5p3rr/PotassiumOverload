package com.cucubananas.core.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Represents the main player object that is controlled by the player.
 * @author Aidan Reed
 * @author Alexis Araouzous
 */
public class Player extends MoveableObject {


  public Player(String texturePath, float x, float y) {
    super(texturePath, x, y);
  }

  @Override
  public void act(float delta) {
  }

  @Override
  public void draw(Batch batch, float alpha) {
    //batch.draw(firstFrame, this.getX(), this.getY(), (int)this.getOriginX(), (int) this.getOriginY(), 100, 100, 1.0f, 1.0f, 0);
  }

}
