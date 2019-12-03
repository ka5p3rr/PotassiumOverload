package com.cucubananas.core.actor;

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

}
