package com.cucubananas.core;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PotassiumOverload extends ApplicationAdapter {
  Logger logger = Logger.getLogger(PotassiumOverload.class.getName());
  SpriteBatch batch;
  Texture img;

  @Override
  public void create() {
    batch = new SpriteBatch();
    img = new Texture("badlogic.jpg");
  }

  @Override
  public void render() {
    Gdx.gl.glClearColor(1, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
      // MOVE UP ON Y AXIS
      logger.log(Level.INFO, "UP");
    } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
      logger.log(Level.INFO, "DOWN");
      // MOVE DOWN ON Y AXIS
    } else {
      // MOVE DOWN ON Y AXIS BY DEFAULT
      logger.log(Level.INFO, "GRAVITY DOWN");
    }

    if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
      // CHANGE ORIENTATION TO LEFT
      logger.log(Level.INFO, "LEFT");
    }

    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
      // CHANGE ORIENTATION TO RIGHT
      logger.log(Level.INFO, "RIGHT");
    }

    if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
      // FIRE BULLET
      logger.log(Level.INFO, "SPACE");
    }

    batch.begin();
    batch.draw(img, 0, 0);
    batch.end();
  }

  @Override
  public void dispose() {
    batch.dispose();
    img.dispose();
  }
}
