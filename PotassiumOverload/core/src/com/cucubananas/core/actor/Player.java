package com.cucubananas.core.actor;

/**
 * Represents the main player object that is controlled by the player.
 * @author Aidan Reed
 * @author Alexis Araouzous
 */
public class Player extends MoveableObject {

  public Player(int x, int y) {
    super("bird.png", x, y);
  }

  @Override
  public void act(float delta) {
  }

}
