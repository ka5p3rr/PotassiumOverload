package com.cucubananas.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.cucubananas.core.actor.Background;
import com.cucubananas.core.actor.Bullet;
import com.cucubananas.core.actor.MoveableObject;
import com.cucubananas.core.actor.Player;

public class GameScreen extends AbstractScreen {

  private CustomStage stage;
  private Player player;

  public GameScreen(PotassiumOverload game) {
    super(game);
    stage = new CustomStage();
    player = new Player(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
    stage.addActor(new Background());
    stage.addActor(player);

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
    stage.updateHitboxes();
    if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
      player.moveUp();
    } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
      player.moveDown();
    } else {
      player.drop();
    }

    if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
      player.moveLeft();
    }

    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
      player.moveRight();
    }

    if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
      player.shoot();
      stage.addActor(
          new Bullet(player.getShootingPositionX(), player.getShootingPositionY(), player.getXState()));
    }

    // TODO uncomment once Projectile and Collectibles are implemented
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
}
