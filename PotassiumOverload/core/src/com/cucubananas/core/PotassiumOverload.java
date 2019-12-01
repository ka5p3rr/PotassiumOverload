package com.cucubananas.core;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.cucubananas.core.actor.Player;

public class PotassiumOverload extends ApplicationAdapter {
  private Stage stage;

  @Override
  public void create() {
    stage = new Stage();
    Player player = new Player(0,0);
    stage.addActor(player);
  }

  @Override
  public void render() {
    Gdx.gl.glClearColor(1, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    float delta = Gdx.graphics.getDeltaTime();
    stage.act(delta);
    stage.draw();
  }

  @Override
  public void dispose() {
    stage.dispose();
  }
}
