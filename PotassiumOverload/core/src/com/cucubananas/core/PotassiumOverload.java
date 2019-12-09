package com.cucubananas.core;

import com.badlogic.gdx.Game;

public class PotassiumOverload extends Game {

    @Override
    public void create() {
        MainMenuScreen menu = new MainMenuScreen(this);
        this.setScreen(menu);
    }

    public enum GameState {
        NEW_GAME, GAME_OVER, SAVE,  EXIT
    }

    public void changeScreen(GameState state){
        switch(state){
            case NEW_GAME:
                this.setScreen(new GameScreen(this));
                break;
            case GAME_OVER:
                this.setScreen(new GameOverScreen(this));
                break;
            case EXIT:
                this.setScreen(new MainMenuScreen(this));
                break;
            case SAVE:
                this.setScreen(new MainMenuScreen(this));
                break;
        }

    }

}
