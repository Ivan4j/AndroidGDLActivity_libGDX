package com.gdlactivity.libgdxdemo.action;

import com.gdlactivity.libgdxdemo.screen.AbstractScreen;
import com.gdlactivity.libgdxdemo.screen.AvailableScreens;
import com.gdlactivity.libgdxdemo.screen.ScreenManager;
import com.gdlactivity.libgdxdemo.screen.boids.BoidsDemoScreen;
import com.gdlactivity.libgdxdemo.screen.LevelSelect;
import com.gdlactivity.libgdxdemo.screen.Menu;
import com.gdlactivity.libgdxdemo.screen.Splash;
import com.gdlactivity.libgdxdemo.screen.physics.Box2DDemoScreen;
import com.gdlactivity.libgdxdemo.screen.transitions.AbstractTransition;
import com.gdlactivity.libgdxdemo.screen.transitions.GateTransition;

/**
 * Created by Ivan_Hernandez on 08/11/2016.
 */

public class LoadScreenAction implements IAction {

    private AvailableScreens screen;

    public LoadScreenAction(AvailableScreens screen) {

        this.screen = screen;
    }

    @Override
    public void execute() {

        AbstractScreen screenToLoad = null;

        switch(screen) {
            case SPLASH:
                screenToLoad = new Splash();
                break;
            case MENU:
                screenToLoad = new Menu();
                break;
            case BOIDS:
                screenToLoad = new BoidsDemoScreen();
                break;
            case LEVEL_SELECT:
                screenToLoad = new LevelSelect();
                break;
            case Box2D:SPLASH:
                screenToLoad = new Box2DDemoScreen();
                break;
        }

        AbstractTransition transition = new GateTransition(true);
        ScreenManager.getInstance().setTransition(transition, 1);
        ScreenManager.getInstance().setScreen(screenToLoad);

        System.out.println("EXECUTE!");
    }
}
