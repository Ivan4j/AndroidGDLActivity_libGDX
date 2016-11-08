package com.gdlactivity.libgdxdemo.screen.physics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.gdlactivity.libgdxdemo.model.Boid;
import com.gdlactivity.libgdxdemo.screen.AbstractScreen;

import java.util.ArrayList;

/**
 * Created by Ivan_Hernandez on 07/11/2016.
 */

public class Box2DDemoScreen extends AbstractScreen {

    Box2DStage stage;

    public Box2DDemoScreen() {

        stage = new Box2DStage();
    }


    @Override
    public void render(float delta) {
        super.render(delta);

        // Clear screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();;
        stage.act(delta);

    }

}
