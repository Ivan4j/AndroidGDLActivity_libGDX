package com.gdlactivity.libgdxdemo.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdlactivity.libgdxdemo.GDLActivity;

/**
 * Created by Ivan_Hernandez on 07/11/2016.
 */

public abstract class AbstractScreen implements Screen {

    SpriteBatch spriteBatch;

    public AbstractScreen() {

        spriteBatch = GDLActivity.getSpriteBatch();

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        // Clear screen
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
