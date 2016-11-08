package com.gdlactivity.libgdxdemo.screen.boids;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.gdlactivity.libgdxdemo.model.Boid;
import com.gdlactivity.libgdxdemo.screen.AbstractScreen;

import java.util.ArrayList;

/**
 * Created by Ivan_Hernandez on 07/11/2016.
 */

public class BoidsDemoScreen extends AbstractScreen {

    private ArrayList<Boid> boidsList = new ArrayList<Boid>();
    private Texture[] boidTexture = new Texture[5];
    private int boidsTotal = 80;
    private String[] imageNames = {"imgs/redbird.png", "imgs/blackbird.png", "imgs/bluebird.png", "imgs/greenbird.png", "imgs/yellowbird.png"};

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
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        super.render(delta);

        // Clear screen
        Gdx.gl.glClearColor(1, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();

        for(Boid boid : boidsList) {
            boid.draw(spriteBatch);
            boid.update(boidsList);
        }

        spriteBatch.end();

    }

}
