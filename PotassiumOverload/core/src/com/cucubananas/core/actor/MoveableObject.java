package com.cucubananas.core.actor;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.FileTextureData;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents the base object of all {@link MoveableObject} in the game. All {@link MoveableObject}
 * objects must have a texture, x and y coordinates.
 *
 * @author Aidan Reed
 * @author Alexis Araouzous
 */
public abstract class MoveableObject extends Actor {

    protected Texture texture;
    protected Rectangle hitbox;
    protected String xState;
    protected String yState;
    private float health;

    protected static final Map<String, Boolean> DIR_TO_ROTATION = new HashMap<>();
    public static final String FACING_DIRECTIONS_LEFT = "LEFT";
    public static final String FACING_DIRECTIONS_RIGHT = "FACING_DIRECTIONS_RIGHT";

    static {
        DIR_TO_ROTATION.put(FACING_DIRECTIONS_LEFT, false);
        DIR_TO_ROTATION.put(FACING_DIRECTIONS_RIGHT, true);
    }

    public MoveableObject(String texturePath, float xPos, float yPos, float health, int width, int height) {
        this(texturePath, xPos, yPos, health);
        hitbox.width = width;
        hitbox.height = height;
    }

    public MoveableObject(String texturePath, float xPos, float yPos, float health) {
        texture = new Texture(texturePath);
        xState = FACING_DIRECTIONS_RIGHT;
        this.setX(xPos);
        this.setY(yPos);
        hitbox = new Rectangle();
        hitbox.x = xPos;
        hitbox.y = yPos;
        hitbox.width = texture.getWidth();
        hitbox.height = texture.getHeight();
        setBounds(getX(), getY(), texture.getWidth(), texture.getHeight());
        this.setHealth(health);
    }

    @Override
    public void draw(Batch batch, float alpha){
        batch.draw(texture, this.getX(), this.getY(), (float) texture.getWidth(), (float) texture.getHeight(), (int) this.getOriginX(), (int) this.getOriginY(),
                (int) this.getWidth(), (int) this.getHeight(), DIR_TO_ROTATION.get(xState), false);
    }

    public void updateHitbox() {
        hitbox.x = this.getX();
        hitbox.y = this.getY();
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public String getXState() {
        return xState;
    }

    public String getYState() { 
        return yState; 
    }

    public void setDirection(String direction) {
        this.xState = direction;
    }

    public String getDirection() {
        return xState;
    }

    public void setHealth(float health){
        this.health = health;
    }

    public float getHealth(){
        return health;
    }

    public int getTextureHeight() {
        return texture.getHeight();
    }

    public String getTextureName() {
        return ((FileTextureData) texture.getTextureData()).getFileHandle().path();
    }

    public boolean checkCollision(MoveableObject mo) {
        return this.getHitbox().overlaps(mo.getHitbox());
    }

    public void setHitboxWidth() {

    }

    public void setHitboxHeight() {

    }
}