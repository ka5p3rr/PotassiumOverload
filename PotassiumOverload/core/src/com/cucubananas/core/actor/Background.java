package com.cucubananas.core.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Background extends Actor {

  Texture background;
  int width;
  int height;
  float sourceX;


  public Background(){
    background = new Texture("cyberv1.png");
    background.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);

    width = Gdx.graphics.getWidth();
    height = Gdx.graphics.getHeight();
    int sourceX = 0;
  }

  @Override
  public void draw(Batch batch, float alpha) {
    batch.draw(background, 0, 0, (int) sourceX, 0, width, height);
  }

  @Override
  public void act(float delta) {
    sourceX += 0.2;
    if(sourceX > width)
      sourceX -= width;

  }

}
