package com.cucubananas.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.cucubananas.core.PotassiumOverload.GameState;
import com.cucubananas.core.actor.Missile;
import com.cucubananas.core.actor.MoveableObject;
import com.cucubananas.core.actor.Player;
import com.cucubananas.core.actor.Projectile;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameScreen extends AbstractScreen {

    private Logger logger = Logger.getLogger(PotassiumOverload.class.getName());
    private CustomStage stage;
    private Player player, p2;
    private ArrayList<Missile> missiles;
    private int range, numberOfEnemies, score;
    private static Integer counter = 0;

    public GameScreen(PotassiumOverload game) {
        super(game);
        stage = new CustomStage();
        range = 10;
        score = 100;
        missiles = new ArrayList<>();
        player = new Player("bird.png", Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
        p2 = new Player("bird.png", Gdx.graphics.getWidth() / 2f, 0);
        stage.addActor(p2);
        stage.addActor(player);
        Missile m1 = createMissile();
        Missile m2 = createMissile();
        missiles.add(m1);
        missiles.add(m2);
        stage.addActor(m1);
        stage.addActor(m2);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();

        calculateEnemies();
        while (missiles.size() < numberOfEnemies) {
            Missile m = createMissile();
            missiles.add(m);
            stage.addActor(m);
        }

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
            player.setDirection(MoveableObject.FACING_DIRECTIONS.left);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            // CHANGE ORIENTATION TO RIGHT
            logger.log(Level.INFO, "Facing right");
            player.setDirection(MoveableObject.FACING_DIRECTIONS.right);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            // FIRE BULLET
            logger.log(Level.INFO, "SPACE");
            resetGame();
        }

        stage.updateHitboxes();
        // stage.checkProjectilesCollision();
        stage.moveProjectiles(counter, range, missiles);

        counter++;
        score++;


        //TODO uncomment once Projectile and Collectibles are implemented
        /*
        stage.checkCollectiblesCollision();
         */

    }

    public void resetGame() {
        logger.log(Level.INFO, "Reset Game");
        game.changeScreen(GameState.GAME_OVER);
        dispose();
    }

    private Missile createMissile() {
        Missile missile;
        double dir = Math.random();

        if (dir < 0.5) {
            missile = new Missile(0, getRandomYPos(), range);
            missile.setDirection(MoveableObject.FACING_DIRECTIONS.right);
        } else {
            missile = new Missile(Gdx.graphics.getWidth(), getRandomYPos(), range);
            missile.setDirection(MoveableObject.FACING_DIRECTIONS.left);
        }

        return missile;
    }


    private int getRandomYPos() {
        return (int) (Math.random() * Gdx.graphics.getWidth() + 1);
    }

    public static int calculateWeight(int range) {
        return (int) (Math.random() * range);
    }

    private void calculateEnemies() {
        numberOfEnemies = score / 50;
    }
}
