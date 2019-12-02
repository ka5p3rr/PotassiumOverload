package com.cucubananas.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.cucubananas.core.actor.Player;

public class PotassiumOverload extends Game {
    Logger logger = Logger.getLogger(PotassiumOverload.class.getName());
    private Stage stage;

    @Override
    public void create() {
        //stage = new Stage();
        //Player player = new Player(0, 0);
        //stage.addActor(player);
        MainMenuScreen menu = new MainMenuScreen(this);
        this.setScreen(menu);
    }

//    @Override
//    public void render() {
//        Gdx.gl.glClearColor(1, 0, 0, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        float delta = Gdx.graphics.getDeltaTime();
//        stage.act(delta);
//        stage.draw();
//
//        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
//            // MOVE UP ON Y AXIS
//            logger.log(Level.INFO, "UP");
//        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
//            logger.log(Level.INFO, "DOWN");
//            // MOVE DOWN ON Y AXIS
//        } else {
//            // MOVE DOWN ON Y AXIS BY DEFAULT
//            logger.log(Level.INFO, "GRAVITY DOWN");
//        }
//
//        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
//            // CHANGE ORIENTATION TO LEFT
//            logger.log(Level.INFO, "LEFT");
//        }
//
//        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
//            // CHANGE ORIENTATION TO RIGHT
//            logger.log(Level.INFO, "RIGHT");
//        }
//
//        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
//            // FIRE BULLET
//            logger.log(Level.INFO, "SPACE");
//        }
//    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
