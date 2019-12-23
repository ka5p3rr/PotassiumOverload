package com.cucubananas.core.actor;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.HashMap;

public class AnimatedMoveableObject extends MoveableObject {
  HashMap<String, TextureRegion> spriteSheet;

  public AnimatedMoveableObject(String texturePath, float xPos, float yPos, float health) {
    super(texturePath, xPos, yPos, health);
    spriteSheet = new HashMap<>();
  }

  public AnimatedMoveableObject(String texturePath, float xPos, float yPos, float health, int width, int height) {
    super(texturePath, xPos, yPos, health, width, height);
    spriteSheet = new HashMap<>();
  }




}
