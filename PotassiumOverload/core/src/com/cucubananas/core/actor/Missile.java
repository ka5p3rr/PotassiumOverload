package com.cucubananas.core.actor;

public class Missile extends Projectile {

    private int weight;

    public Missile(int x, int y, int weight, float health) {
        super("bird.png", x, y, health);
        this.weight = weight;

    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

}
