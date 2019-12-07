package com.cucubananas.core.actor;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.HashMap;

public class AnimatedMoveableObject extends MoveableObject {
  HashMap<String, TextureRegion> spriteSheet;

  public AnimatedMoveableObject(String texturePath, float xPos, float yPos) {
    super(texturePath, xPos, yPos);
    spriteSheet = new HashMap<>();
  }




}
