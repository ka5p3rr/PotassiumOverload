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
  private static final int WIDTH = 52;
  private static final int HEIGHT = 100;

  private String xState;
  private String yState;
  private static final float MOVEMENT = 4;
  private int shootingDuration = 0;

  public Player(float x, float y) {
    this(x, y, 100);
  }

  public Player(float x, float y, float health) {
    super("characterspritesheet.png", x, y, health, WIDTH, HEIGHT);
    xState = RIGHT;
    yState = UP;

    spriteSheet.put(RIGHT + DOWN, new TextureRegion(texture,0,0,WIDTH,HEIGHT));
    spriteSheet.put(RIGHT + UP, new TextureRegion(texture,52,0,WIDTH,HEIGHT));
    spriteSheet.put(RIGHT + SHOOTING, new TextureRegion(texture,104,0,WIDTH,HEIGHT));
    spriteSheet.put(LEFT + DOWN, new TextureRegion(texture,0,100,WIDTH,HEIGHT));
    spriteSheet.put(LEFT + UP, new TextureRegion(texture,52,100,WIDTH,HEIGHT));
    spriteSheet.put(LEFT + SHOOTING, new TextureRegion(texture,108,100,WIDTH,HEIGHT));
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
