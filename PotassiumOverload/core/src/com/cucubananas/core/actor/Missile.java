package com.cucubananas.core.actor;

public class Missile extends Projectile {

    private int weight;
    private float randomisationTime;

    public Missile(int x, int y, int weight, float health, float randomisationTime) {
        super("bird.png", x, y, health);
        this.weight = weight;
        this.randomisationTime = randomisationTime;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public float getRandomisationTime() {
        return randomisationTime;
    }

}
