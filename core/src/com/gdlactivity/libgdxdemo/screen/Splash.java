package com.gdlactivity.libgdxdemo.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gdlactivity.libgdxdemo.controller.UIController;
import com.gdlactivity.libgdxdemo.ui.Button;
import com.gdlactivity.libgdxdemo.ui.ButtonType;
import com.gdlactivity.libgdxdemo.ui.UIComponent;
import com.gdlactivity.libgdxdemo.utils.Constants;

import java.util.ArrayList;

/**
 * Created by Ivan_Hernandez on 07/11/2016.
 */

public class Splash extends AbstractScreen {

    //Splash Icon/Logo
    private Sprite splashSprite;
    private Texture splashTexture;

    //Loading indicator
    private Sprite loadingSprite;
    private Texture loadingTexture;

    //Optional Text/Logo
    private Texture splashTextTexture;

    private Button continueButton;


    public Splash() {
        splashTexture = new Texture(Gdx.files.internal("imgs/gamecontroller.png"));
        splashTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        loadingTexture = new Texture(Gdx.files.internal("imgs/gear.png"));
        loadingTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        splashTextTexture = new Texture(Gdx.files.internal("imgs/splash_text.png"));
        splashTextTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        splashSprite = new Sprite(splashTexture);
        splashSprite.setBounds(Constants.SCREEN_WIDTH / 2 - 70, Constants.SCREEN_HEIGHT / 2 - 70, 140, 140);
        splashSprite.setOrigin(splashSprite.getWidth() / 2, splashSprite.getHeight() / 2);

        loadingSprite = new Sprite(loadingTexture);
        loadingSprite.setBounds(Constants.SCREEN_WIDTH / 2 - 40, Constants.SCREEN_HEIGHT / 2 - 220, 80, 80);
        loadingSprite.setOrigin(loadingSprite.getWidth() / 2, loadingSprite.getHeight() / 2);

        continueButton = new Button(ButtonType.CONTINUE);
        continueButton.setBounds(Constants.SCREEN_WIDTH - 70, 20, 50, 50);

        uiComponents.add(continueButton);

    }

    @Override
    public void render(float delta) {
        super.render(delta);

        // Clear screen
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();

        spriteBatch.draw(splashTextTexture, Constants.SCREEN_WIDTH / 2 - 480 * 0.7f / 2, Constants.SCREEN_HEIGHT - Constants.SCREEN_HEIGHT / 3, 480 * 0.7f, 100 * 0.7f);

        splashSprite.draw(spriteBatch);

        //Assets loading simulation
        loadingSprite.draw(spriteBatch);
        loadingSprite.rotate(-40 * delta);

        continueButton.draw(spriteBatch);

        spriteBatch.end();

        splashSprite.setRotation(5 * delta);

    }
}
