package com.cucubananas.core.actor;


/**
 * Represents the enemy object that is capable of killing a {@link Player}.
 * @author Aidan Reed
 * @author Alexis Araouzous
 */
public class Enemy extends MoveableObject {
  public Enemy(int x, int y) {
    super("badlogic.jpg", x, y, 100);
  }
}
