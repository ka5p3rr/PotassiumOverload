package com.cucubananas.core.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Represents the main player object that is controlled by the player.
 * @author Aidan Reed
 * @author Alexis Araouzous
 */
public class Player extends AnimatedMoveableObject {

  private static final String DOWN = "DOWN";
  private static final String UP = "UP";
  private static final String SHOOTING = "SHOOTING";
  private static final String LEFT = "LEFT";
  private static final String RIGHT = "RIGHT";

  private String currentState;

  public Player(float x, float y) {
    super("characterspritesheet.png", x, y);
    currentState = DOWN + RIGHT;

    spriteSheet.put(DOWN + RIGHT, new TextureRegion(texture,0,0,52,100));
    spriteSheet.put(UP + RIGHT, new TextureRegion(texture,52,0,52,100));
    spriteSheet.put(SHOOTING + RIGHT, new TextureRegion(texture,104,0,52,100));
    spriteSheet.put(DOWN + LEFT, new TextureRegion(texture,52,0,52,100));
    spriteSheet.put(UP + LEFT, new TextureRegion(texture,0,0,52,100));
    spriteSheet.put(SHOOTING + LEFT, new TextureRegion(texture,0,0,52,100));
    setBounds(x,y,52,100);
  }

  @Override
  public void act(float delta) {

  }



  @Override
  public void draw(Batch batch, float alpha) {
    batch.draw(spriteSheet.get(currentState), getX(), getY(), 52, 100);
  }
}
