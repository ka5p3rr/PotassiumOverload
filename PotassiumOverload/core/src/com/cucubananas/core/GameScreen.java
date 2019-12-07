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

import java.util.logging.Level;
import java.util.logging.Logger;

public class GameScreen extends AbstractScreen {

    private Logger logger = Logger.getLogger(PotassiumOverload.class.getName());
    private CustomStage stage;
    private Player player, p2;

    public GameScreen(PotassiumOverload game) {
        super(game);
        stage = new CustomStage();
        player = new Player("bird.png", Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
        p2 = new Player("bird.png", Gdx.graphics.getWidth() / 2f, 0);
        stage.addActor(p2);
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

        System.out.println(player.checkCollision(p2));
        //TODO uncomment once Projectile and Collectibles are implemented
        /*
        stage.checkProjectilesCollision();
        stage.checkCollectiblesCollision();
         */

    }

    private class CustomStage extends Stage {
        Player player;

        public void updateHitboxes() {
            for (Actor a : this.getActors()) {
                if (a instanceof MoveableObject) {
                    ((MoveableObject) a).updateHitbox();
                }
            }
        }

        /*
        //TODO uncomment once Projectile and Collectibles are implemented
        public void checkProjectilesCollision() {

            for (Actor a : this.getActors()) {
                if (a instanceof Projectile) {
                   player.checkCollision((MoveableObject) a);
                }
            }
        }

        public void checkCollectiblesCollision() {
            for (Actor a : this.getActors()) {
                if (a instanceof Collectible) {
                    player.checkCollision((MoveableObject) a);
                }
            }
        }
          */

        @Override
        public void addActor(Actor actor) {
            if (actor instanceof Player) player = (Player) actor;
            super.addActor(actor);
        }
    }

    public void resetGame(){
        logger.log(Level.INFO, "Reset Game");
        game.changeScreen(GameState.GAME_OVER);
        dispose();
    }

    private Missile createLeftMissile() {
        Missile missile = new Missile(0, getRandomYPos());
    }

    private Missile createRightMissile() {
        Missile missile = new Missile(Gdx.graphics.getWidth(), getRandomYPos());
    }
    
    private int getRandomYPos() {
        return (int)(Math.random() * Gdx.graphics.getWidth() + 1);
    }
}
