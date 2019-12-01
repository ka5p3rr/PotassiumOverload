package com.cucubananas.core.actor;

/**
 * Represents the bullet that can be fired from both the {@link Enemy}Enemy or {@link Player} objects.
 * @author Aidan Reed
 * @author Alexis Araouzous
 */
public class Bullet extends MoveableObject {
  public Bullet(int x, int y) {
    super("badlogic.jpg", x, y);
  }
}
