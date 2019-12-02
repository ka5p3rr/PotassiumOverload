package com.cucubananas.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Arrays;
import java.util.List;

public class MainMenuScreen implements Screen {

  final PotassiumOverload game;
  private OrthographicCamera camera;
  private SpriteBatch batch;
  private BitmapFont font;
  static final int width = 1280;
  static final int height = 720;
  int padding;
  int state;

  public MainMenuScreen(PotassiumOverload game) {
    this.game = game;
    font = new BitmapFont();
    batch = new SpriteBatch();
    camera = new OrthographicCamera();
    camera.setToOrtho(false, width, height);
    padding = 50;
    state = 0;
  }

  @Override
  public void show() {

  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 0, 0.2f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    camera.update();
    batch.begin();

    List<Color> selections = Arrays.asList(Color.WHITE, Color.WHITE, Color.WHITE);
    selections.set(state, Color.GOLD);

    //Title
    font.setColor(Color.GOLD);
    drawCenteredText(font, "Potassium Overload", width / 2, height / 2 + height / 3);

    //Start
    font.setColor(selections.get(0));
    drawCenteredText(font, "Start", width / 2, height / 2);

    //Settings
    font.setColor(selections.get(1));
    drawCenteredText(font, "Settings", width / 2, height / 2 - padding);

    //Exit
    font.setColor(selections.get(2));
    drawCenteredText(font, "Exit", width / 2, height / 2 - 2 * padding);

    if (Gdx.input.isKeyJustPressed(Keys.UP)) {
      state -= 1;
      if (state < 0) {
        state = 2;
      }
    }

    if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
      state = (state + 1) % 3;
    }

    if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
      if (state == 0) {
        //game.setScreen(new GameScreen(game)); this will be the game screen
      } else if (state == 1) {
        //game.setScreen(new SettingsScreen(game)); this will be the game screen
        //game.setScreen(new SettingsScreen(game)); this will be the game screen
      } else {
        Gdx.app.exit();
      }
      dispose();
    }

    batch.end();
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

  private void drawCenteredText(BitmapFont font, String string, int x, int y) {
    GlyphLayout glyph = new GlyphLayout();
    glyph.setText(font, string);
    font.draw(batch, string, x - glyph.width / 2, y);
  }
}
