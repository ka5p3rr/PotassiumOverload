package com.cucubananas.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.cucubananas.core.PotassiumOverload.GameState;
import com.cucubananas.core.actor.Background;
import com.cucubananas.core.actor.Bullet;
import com.cucubananas.core.actor.Missile;
import com.cucubananas.core.actor.MoveableObject;
import com.cucubananas.core.actor.Player;
import com.cucubananas.core.actor.Projectile;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class GameScreen extends AbstractScreen {

  private CustomStage stage;
  private Player player;
  private List<Projectile> projectiles;
  private int range, numberOfEnemies, score;
  private static Integer counter = 0;

  public GameScreen(PotassiumOverload game) {
    super(game);
    stage = new CustomStage();
    range = 10;
    score = 200;
    projectiles = new ArrayList<>();
    player = new Player(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
    stage.addActor(new Background());
    stage.addActor(player);
    Missile m1 = createMissile();
    Missile m2 = createMissile();
    projectiles.add(m1);
    projectiles.add(m2);
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

    while(projectiles.size() < numberOfEnemies) {
      Missile m = createMissile();
      projectiles.add(m);
      stage.addActor(m);
    }

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
      stage.addActor(
          new Bullet(player.getShootingPositionX(), player.getShootingPositionY(), player.getXState()));
      player.shoot();
    }

    stage.updateHitboxes();
    stage.checkMissileCollision(projectiles);
    stage.moveProjectiles(counter, range, projectiles);
    counter++;
    score++;

    // TODO uncomment once Projectile and Collectibles are implemented
    /*
    stage.checkProjectilesCollision();
    stage.checkCollectiblesCollision();
     */

  }

    public void resetGame() {
        game.changeScreen(GameState.GAME_OVER);
        dispose();
    }

    private Missile createMissile() {
        Missile missile;
        double dir = Math.random();

        if (dir < 0.5) {
            missile = new Missile(0, getRandomYPos(), range);
            missile.setDirection(MoveableObject.FACING_DIRECTIONS_LEFT);
        } else {
            missile = new Missile(Gdx.graphics.getWidth(), getRandomYPos(), range);
            missile.setDirection(MoveableObject.FACING_DIRECTIONS_RIGHT);
        }
        return missile;
    }

    private int getRandomYPos() {
        double random = new SecureRandom().nextDouble();
        return (int) (random * Gdx.graphics.getWidth() + 1);
    }

    public static int calculateWeight(int range) {
        double random = new SecureRandom().nextDouble();
        return (int) (random * range);
    }

    private void calculateEnemies() {
        numberOfEnemies = score / 400;
    }
}
