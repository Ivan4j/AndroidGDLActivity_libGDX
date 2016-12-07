package com.gdlactivity.libgdxdemo.screen.transitions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Ivan_Hernandez on 11/11/2016.
 */

public abstract class AbstractTransition {

    protected Texture fadeTexture;

    public AbstractTransition() {
        fadeTexture = new Texture(Gdx.files.internal("imgs/black.png"));
    }

    //public abstract void render (Texture currScreen, Texture nextScreen, float alpha);
    public abstract void render (float alpha);

}
