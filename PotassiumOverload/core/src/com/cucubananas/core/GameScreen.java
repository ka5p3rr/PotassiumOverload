package com.cucubananas.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.cucubananas.core.PotassiumOverload.GameState;
import com.cucubananas.core.actor.Background;
import com.cucubananas.core.actor.Bullet;
import com.cucubananas.core.actor.Missile;
import com.cucubananas.core.actor.MoveableObject;
import com.cucubananas.core.actor.Player;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class GameScreen extends AbstractScreen {

    protected static Integer randomisationCounter = 0;
    protected static float missileHealth = 10f;
    protected static int numberOfEnemies = 2;

    private CustomStage stage;
    private Player player;
    private List<Missile> missiles;
    private List<Bullet> bullets;
    private HealthBar hBar;
    private int range;

    public GameScreen(PotassiumOverload game) {
        super(game);
        stage = new CustomStage(missiles, bullets);
        range = 10;
        missiles = new ArrayList<>();
        bullets = new ArrayList<>();
        player = new Player(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
        stage.addActor(new Background());
        stage.addActor(player);
        Missile m1 = createMissile();
        Missile m2 = createMissile();
        missiles.add(m1);
        missiles.add(m2);
        stage.addActor(m1);
        stage.addActor(m2);
        hBar = new HealthBar(player);
        stage.addActor(hBar);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
        hBar.draw();
        renderScore();

        while (missiles.size() < numberOfEnemies) {
            Missile m = createMissile();
            missiles.add(m);
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

        // Updates game state and returns false if player health <= 0
        if (!stage.updateGameState(randomisationCounter, range, bullets, missiles))
            game.setScreen(new GameOverScreen(game, player.getScore()));

    }

    private void renderScore() {
        batch.begin();
        font.setColor(Color.GOLD);
        GlyphLayout glyph = new GlyphLayout();
        glyph.setText(font, Integer.toString(player.getScore()));
        font.draw(batch, Integer.toString(player.getScore()), (float) 50 - glyph.width / 2, (float) Gdx.graphics.getHeight() - 40);
        batch.end();
    }

    public void resetGame() {
        game.changeScreen(GameState.GAME_OVER);
        dispose();
    }

    private Missile createMissile() {
        Missile missile;
        double dir = new SecureRandom().nextDouble();

        if (dir < 0.5) {
            missile = new Missile(0, getRandomYPos(), range, missileHealth);
            missile.setDirection(MoveableObject.FACING_DIRECTIONS_RIGHT);
        } else {
            missile = new Missile(Gdx.graphics.getWidth(), getRandomYPos(), range, missileHealth);
            missile.setDirection(MoveableObject.FACING_DIRECTIONS_LEFT);
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

    private static class HealthBar extends Actor {
        private Player player;
        private float width, height, greenWidth;
        private ShapeRenderer hBarRenderer;

        public HealthBar(Player player) {
            hBarRenderer = new ShapeRenderer();
            this.player = player;
            // Left-most X coordinate
            setX(Gdx.graphics.getWidth() / (float) 10);
            // Y bottom coordinate
            setY(Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() / (float) 10));
            // Overall width of bar when at 100%
            width = Gdx.graphics.getWidth() - getX() * 2;
            // Y top coordinate
            height = 40f;
            // Portion of bar to be rendered green and red
            greenWidth = 100f;
        }

        public void draw() {
            float boundary = width / 100 * greenWidth;
            Gdx.gl.glEnable(GL20.GL_BLEND);
            hBarRenderer.setProjectionMatrix(getStage().getCamera().combined);
            hBarRenderer.begin(ShapeRenderer.ShapeType.Filled);
            hBarRenderer.setColor(new Color(0, 1, 0, 0.2f));
            hBarRenderer.rect(getX(), getY(), boundary, height);
            hBarRenderer.setColor(new Color(1, 0, 0, 0.2f));
            hBarRenderer.rect(Gdx.graphics.getWidth() - getX() - (width - boundary), getY(), width - boundary, height);
            hBarRenderer.end();
            Gdx.gl.glDisable(GL20.GL_BLEND);
        }

        public void updateHealth() {
            greenWidth = player.getHealth();
        }
    }

}
