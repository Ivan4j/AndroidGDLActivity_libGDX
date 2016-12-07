package com.gdlactivity.libgdxdemo.screen;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.gdlactivity.libgdxdemo.screen.transitions.AbstractTransition;
import com.gdlactivity.libgdxdemo.utils.Constants;

/**
 * Created by Ivan_Hernandez on 08/11/2016.
 *
 * This is a modified and simplified version of this class: https://github.com/digital-thinking/libgdx-transitions/blob/master/src/main/com/ixeption/libgdx/transitions/FadingGame.java
 * I'm using Texture to perform transition effects (instead of FrameBuffer).
 * I had problems with frameBuffer class, it doesn't work properly with Viewport (FitViewport), that's the reason why I removed them and use Texture instead
 *
 */

public class ScreenManager extends Game {

    private static ScreenManager instance;

    private AbstractTransition transitionEffect;

    private Screen nextScreen;
    private float time;
    private boolean transitionRunning;
    private float transitionDuration;

    private ScreenManager() {

    }

    public static ScreenManager getInstance() {

        if(instance == null)
            instance = new ScreenManager();

        return instance;
    }

    @Override
    public void create() {

    }

    public void render() {

        float deltaTime = Math.min(Gdx.graphics.getDeltaTime(), 1.0f / 60.0f);

        if (screen != null)
            screen.render(deltaTime);

        if (transitionRunning && time >= transitionDuration / 2) {

            if(nextScreen != null) {
                this.screen.hide();
                this.screen = this.nextScreen;
                this.screen.resume();
                this.nextScreen = null;
            }
            if(time >= transitionDuration)
                transitionRunning = false;
        }

        if (transitionEffect != null && time <= transitionDuration) {

            float percent = time / transitionDuration;
            transitionEffect.render(percent);
            time += deltaTime;
        }
    }

    @Override
    public void dispose() {
        if (screen != null) screen.hide();
        if (nextScreen != null) nextScreen.hide();

    }

    public boolean setTransition(AbstractTransition screenTransition, float duration) {
        if (transitionRunning) return false;
        this.transitionEffect = screenTransition;
        this.transitionDuration = duration;
        return true;

    }

    @Override
    public void setScreen(Screen screen) {

        screen.show();

        if (this.screen == null) {
            this.screen = screen;
        } else {
            if (transitionEffect == null) {
                this.screen.hide();
                this.screen = screen;
            } else {
                this.nextScreen = screen;
                this.screen.pause();
                this.nextScreen.pause();
                time = 0;
                transitionRunning = true;
            }
        }
        this.screen.resize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
    }

}
