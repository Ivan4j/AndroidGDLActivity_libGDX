package com.gdlactivity.libgdxdemo.screen;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

/**
 * Created by Ivan_Hernandez on 08/11/2016.
 */

public class ScreenManager {

    private static ScreenManager instance;
    private Game game;

    public static ScreenManager getInstance() {
        if(instance == null)
            instance = new ScreenManager();

        return instance;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return this.game;
    }

    public void loadScreen(Screen screenToLoad) {
        this.game.setScreen(screenToLoad);
    }

}
