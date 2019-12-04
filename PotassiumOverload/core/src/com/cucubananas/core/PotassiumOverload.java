package com.cucubananas.core;

import com.badlogic.gdx.Game;

public class PotassiumOverload extends Game {

    @Override
    public void create() {
        MainMenuScreen menu = new MainMenuScreen(this);
        this.setScreen(menu);
    }

}