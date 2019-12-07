package com.cucubananas.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.cucubananas.core.actor.Background;
import com.cucubananas.core.actor.MoveableObject;
import com.cucubananas.core.actor.Player;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GameScreen implements Screen {

  final PotassiumOverload game;
  Logger logger = Logger.getLogger(GameScreen.class.getName());
  private Stage stage;
  private Player player;
  private OrthographicCamera camera;
  int padding;
  int state;

  public GameScreen(PotassiumOverload game) {
    this.game = game;
    stage = new Stage();
    player = new Player(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
    stage.addActor(new Background());
    stage.addActor(player);
    camera = new OrthographicCamera();
    camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    padding = 50;
    state = 0;
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(1, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    stage.act(delta);
    stage.draw();

    if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
      player.moveUp();
    } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
      player.moveDown();
    } else {
      player.drop();
    }

    if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
      player.moveLeft();
    }

    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
      player.moveRight();
    }

    if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {

      logger.log(Level.INFO, "SPACE");
    }
  }

  @Override
  public void resize(int width, int height) {
    // Must override to implement Screen
  }

  @Override
  public void show() {
    // Must override to implement Screen
  }

  @Override
  public void pause() {
    // Must override to implement Screen
  }

  @Override
  public void resume() {
    // Must override to implement Screen
  }

  @Override
  public void hide() {
    // Must override to implement Screen
  }

  @Override
  public void dispose() {
    // Must override to implement Screen
  }
}
