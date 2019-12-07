package com.cucubananas.core.actor;

import com.badlogic.gdx.Gdx;
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

  private static final int WIDTH = 52;
  private static final int HEIGHT = 100;

  private String xState;
  private String yState;
  private static final float MOVEMENT = 4;

  public Player(float x, float y) {
    this(x, y, 100);
  }

  public Player(float x, float y, float health) {
    super("characterspritesheet.png", x, y, health, WIDTH, HEIGHT);
    xState = RIGHT;
    yState = UP;

    spriteSheet.put(RIGHT + DOWN, new TextureRegion(texture,0,0,52,100));
    spriteSheet.put(RIGHT + UP, new TextureRegion(texture,52,0,52,100));
    spriteSheet.put(RIGHT + SHOOTING, new TextureRegion(texture,104,0,52,100));
    spriteSheet.put(LEFT + DOWN, new TextureRegion(texture,0,100,52,100));
    spriteSheet.put(LEFT + UP, new TextureRegion(texture,52,100,52,100));
    spriteSheet.put(LEFT + SHOOTING, new TextureRegion(texture,104,100,52,100));
    setBounds(x,y,52,100);
  }

  @Override
  public void draw(Batch batch, float alpha) {
    batch.draw(spriteSheet.get(xState + yState), getX(), getY(), 52, 100);
  }


  public void drop() {
    if (getY() < MOVEMENT/2)
      return;
    setY(getY() - MOVEMENT/2);
    yState = DOWN;
  }

  public void moveDown() {
    if (getY() < MOVEMENT)
      return;
    setY(getY() - MOVEMENT);
    yState = DOWN;
  }

  public void moveUp() {
    if (getY() > Gdx.graphics.getHeight() - getHeight() - MOVEMENT)
      return;

    setY(getY() + MOVEMENT);
    yState = UP;
  }

  public void moveLeft() {
    xState = LEFT;
  }

  public void moveRight() {
    xState = RIGHT;
  }

}
