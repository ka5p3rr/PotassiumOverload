package com.cucubananas.core.actor;

/**
 * Represents the main player object that is controlled by the player.
 * @author Aidan Reed
 * @author Alexis Araouzous
 */
public class Player extends MoveableObject {

  public Player(String texturePath, float x, float y) {
    this(texturePath, x, y, 100);
  }
  public Player(String texturePath, float x, float y, float health) {
    super(texturePath, x, y, health);
  }


  @Override
  public void act(float delta) {
  }

}
