package com.gdlactivity.libgdxdemo.screen.boids;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.gdlactivity.libgdxdemo.action.LoadScreenAction;
import com.gdlactivity.libgdxdemo.model.Boid;
import com.gdlactivity.libgdxdemo.screen.AbstractScreen;
import com.gdlactivity.libgdxdemo.screen.AvailableScreens;
import com.gdlactivity.libgdxdemo.ui.Button;
import com.gdlactivity.libgdxdemo.ui.ButtonType;
import com.gdlactivity.libgdxdemo.utils.Constants;

import java.util.ArrayList;

/**
 * Created by Ivan_Hernandez on 07/11/2016.
 */

public class BoidsDemoScreen extends AbstractScreen {

    private ArrayList<Boid> boidsList = new ArrayList<Boid>();

    private int boidsTotal = 80;
    private Texture[] boidTexture = new Texture[5];
    private String[] imageNames = {"imgs/redbird.png", "imgs/blackbird.png", "imgs/bluebird.png", "imgs/greenbird.png", "imgs/yellowbird.png"};

    private Button backButton;

    public BoidsDemoScreen() {

        for(int i=0; i<imageNames.length; i++) {
            boidTexture[i] = new Texture(Gdx.files.internal(imageNames[i]));
            boidTexture[i].setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }

        for(int i=0; i<boidsTotal; i++) {
            int rndIndex = (int)(Math.random() * 5);
            Boid boid = new Boid(boidTexture[rndIndex]);
            boid.setBounds((float)(Math.random() * 480), (float)(Math.random() * 800), boidTexture[rndIndex].getWidth() / 4, boidTexture[rndIndex].getHeight() / 4);
            boidsList.add(boid);
        }

        backButton = new Button(ButtonType.CONTINUE);
        backButton.setBounds(Constants.BUTTON_SIZE_MED / 2, Constants.SCREEN_HEIGHT - Constants.BUTTON_SIZE_MED * 1.2f, Constants.BUTTON_SIZE_MED, Constants.BUTTON_SIZE_MED);

        uiComponents.add(backButton);

        backButton.setAction(new LoadScreenAction(AvailableScreens.MENU));


        try {
            backgroundTexture = new Texture(Gdx.files.internal("imgs/ab_background.png"));
            backgroundTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

            backgroundSprite.setTexture(backgroundTexture);

        } catch(Exception e) {
            System.err.println(e.getMessage());
            System.err.println("Background not found, default one applied.");
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        super.render(delta);

        spriteBatch.begin();

        for(Boid boid : boidsList) {
            boid.draw(spriteBatch);
            boid.update(boidsList);
        }

        backButton.draw(spriteBatch);

        spriteBatch.end();

    }

}
