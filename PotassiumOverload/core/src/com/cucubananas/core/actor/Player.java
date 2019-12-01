package com.cucubananas.core.actor;

import com.badlogic.gdx.Gdx;

/**
 * Represents the main player object that is controlled by the player.
 * @author Aidan Reed
 * @author Alexis Araouzous
 */
public class Player extends MoveableObject {

  public Player(int x, int y) {
    super(Gdx.files.internal("../core/assets/bird.png").path(), x, y);
  }

  @Override
  public void act(float delta) {
  }

}
