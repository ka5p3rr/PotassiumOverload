package com.cucubananas.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.cucubananas.core.actor.MoveableObject;
import com.cucubananas.core.actor.Player;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GameScreen extends AbstractScreen {

    private Logger logger = Logger.getLogger(PotassiumOverload.class.getName());
    private Stage stage;
    private Player player;

    public GameScreen(PotassiumOverload game) {
        super(game);
        stage = new Stage();
        player = new Player("bird.png", Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
        stage.addActor(player);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            // MOVE UP ON Y AXIS
            logger.log(Level.INFO, "Player moving up");
            if (player.getY() <= Gdx.graphics.getHeight() - player.getHeight() - 4) player.setY(player.getY() + 4);
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            // MOVE DOWN ON Y AXIS
            logger.log(Level.INFO, "Player moving down");
            if (player.getY() >= 4) player.setY(player.getY() - 4);
        } else {
            // MOVE DOWN ON Y AXIS BY DEFAULT
            logger.log(Level.INFO, "Gravity down");
            if (player.getY() >= 2) player.setY(player.getY() - 2);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            // CHANGE ORIENTATION TO LEFT
            logger.log(Level.INFO, "Facing left");
            player.setDirection(MoveableObject.facingDirections.left);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            // CHANGE ORIENTATION TO RIGHT
            logger.log(Level.INFO, "Facing right");
            player.setDirection(MoveableObject.facingDirections.right);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            // FIRE BULLET
            logger.log(Level.INFO, "SPACE");
        }
    }

}
