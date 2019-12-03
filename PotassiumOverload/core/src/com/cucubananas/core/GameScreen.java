package com.cucubananas.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.cucubananas.core.actor.MoveableObject;
import com.cucubananas.core.actor.Player;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GameScreen implements Screen {

    final PotassiumOverload game;
    Logger logger = Logger.getLogger(PotassiumOverload.class.getName());
    private Stage stage;
    private Player player;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;
    int padding;
    int state;

    public GameScreen(PotassiumOverload game) {
        this.game = game;
        stage = new Stage();
        player = new Player(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        stage.addActor(player);
        font = new BitmapFont();
        batch = new SpriteBatch();
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

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

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
