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
  private static final float MOVEMENT = 4;
  private int shootingDuration = 0;

  public Player(float x, float y) {
    super("characterspritesheet.png", x, y);
    xState = RIGHT;
    yState = UP;

    spriteSheet.put(RIGHT + DOWN, new TextureRegion(texture,0,0,52,100));
    spriteSheet.put(RIGHT + UP, new TextureRegion(texture,52,0,52,100));
    spriteSheet.put(RIGHT + SHOOTING, new TextureRegion(texture,104,0,52,100));
    spriteSheet.put(LEFT + DOWN, new TextureRegion(texture,0,100,52,100));
    spriteSheet.put(LEFT + UP, new TextureRegion(texture,52,100,52,100));
    spriteSheet.put(LEFT + SHOOTING, new TextureRegion(texture,108,100,52,100));
    setBounds(x,y,52,100);
  }

  @Override
  public void draw(Batch batch, float alpha) {
    batch.draw(spriteSheet.get(xState + yState), getX(), getY(), 52, 100);
    shootingDuration++;
  }

  @Override
  public void act(float delta) {
    super.act(delta);
  }

  public void drop() {
    if (getY() < MOVEMENT/2)
      setY(0);
    setY(getY() - MOVEMENT/2);
    if(shootingDuration > 10)
      yState = DOWN;
  }

  public void moveDown() {
    if (getY() < MOVEMENT)
      setY(0);
    setY(getY() - MOVEMENT);
    yState = DOWN;
  }

  public void moveUp() {
    if (getY() < Gdx.graphics.getHeight() - getHeight() - MOVEMENT)
      setY(getY() + MOVEMENT);
    if (shootingDuration > 10)
      yState = UP;
  }

  public void moveLeft() {
    xState = LEFT;
  }

  public void moveRight() {
    xState = RIGHT;
  }

  public void shoot() {
    shootingDuration = 0;
    yState = SHOOTING;
  }

  public float getShootingPositionY() {
    return getY() + 70;
  }

  public float getShootingPositionX() {
    if(xState.equals(LEFT))
      return getX() - 5;
    else
      return getX() + 45;
  }

}
