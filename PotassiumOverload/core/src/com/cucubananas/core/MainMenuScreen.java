package com.cucubananas.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MainMenuScreen implements Screen {

  final PotassiumOverload game;
  OrthographicCamera camera;
  int width = 800;
  int height = 480;
  boolean blink;
  int blinkConstant;
  int blinkCounter;

  public MainMenuScreen(PotassiumOverload game) {
    this.game = game;
    this.blink = false;
    this.blinkConstant = 40;
    this.blinkCounter = blinkConstant;
    camera = new OrthographicCamera();
    camera.setToOrtho(false, width, height);
  }

  @Override
  public void show() {

  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 0, 0.2f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    camera.update();
    game.batch.setProjectionMatrix(camera.combined);

    game.batch.begin();
    game.font.draw(game.batch, "Potassium Overload", width / 2 - 70, height / 2 + 100);
    blinkCounter--;
    if (blinkCounter <= 0) {
      blinkCounter = blinkConstant;
      blink = !blink;
    }
    if (!blink) {
      game.font.draw(game.batch, "Press space to begin!", width / 2 - 75, height / 2);
    }

    game.batch.end();

    if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
      System.out.println("DEBUG");
      //game.setScreen(new GameScreen(game)); this will be the game screen
      //dispose();
    }
  }

  @Override
  public void resize(int width, int height) {

  }

  @Override
  public void pause() {

  }

  @Override
  public void resume() {

  }

  @Override
  public void hide() {

  }

  @Override
  public void dispose() {

  }
}
