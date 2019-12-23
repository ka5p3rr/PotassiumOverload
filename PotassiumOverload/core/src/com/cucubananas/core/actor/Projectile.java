package com.cucubananas.core.actor;

public abstract class Projectile extends MoveableObject{
    public Projectile(String texturePath, float x, float y, float health) {
        super(texturePath, x, y, health);
    }
}
