package com.gdlactivity.libgdxdemo.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdlactivity.libgdxdemo.GDLActivity;
import com.gdlactivity.libgdxdemo.controller.UIController;
import com.gdlactivity.libgdxdemo.ui.UIComponent;
import com.gdlactivity.libgdxdemo.utils.Constants;

import java.util.ArrayList;

/**
 * Created by Ivan_Hernandez on 07/11/2016.
 */

public abstract class AbstractScreen implements Screen {

    protected SpriteBatch spriteBatch;
    protected ArrayList<UIComponent> uiComponents;
    protected UIController uiController;

    protected Sprite backgroundSprite;
    protected Texture backgroundTexture;

    public AbstractScreen() {

        spriteBatch = GDLActivity.getSpriteBatch();
        uiComponents = new ArrayList<UIComponent>();
        uiController = new UIController(uiComponents);

        backgroundTexture = new Texture(Gdx.files.internal("imgs/background_white.png"));
        backgroundTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        backgroundSprite = new Sprite(backgroundTexture);
        backgroundSprite.setBounds(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        Gdx.input.setInputProcessor(uiController);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        // Clear screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        backgroundSprite.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        //GDLActivity.getViewport().update(width, height, true);
        //GDLActivity.getCamera().position.set(GDLActivity.getCamera().viewportWidth / 2, GDLActivity.getCamera().viewportHeight / 2, 0);
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
