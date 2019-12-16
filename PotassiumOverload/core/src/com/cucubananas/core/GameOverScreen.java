package com.cucubananas.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class GameOverScreen extends AbstractScreen {
    int score;

    public GameOverScreen(PotassiumOverload game) {
        super(game);
    }

    public GameOverScreen(PotassiumOverload game, int score) {
        this(game);
        this.score = score;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        font.setColor(Color.GOLD);
        GlyphLayout glyph = new GlyphLayout();
        glyph.setText(font, Integer.toString(score));
        font.draw(batch, Integer.toString(score), (float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2);
        batch.end();
    }

}
