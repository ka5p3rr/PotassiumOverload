package com.cucubananas.core.actor;

public class Missile extends Projectile {

    public int weight;
    public Missile(int x, int y, int weight) {
        super("bird.png", x, y, 100);
        this.weight = weight;

    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

}
