package com.gdlactivity.libgdxdemo.screen.transitions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.gdlactivity.libgdxdemo.GDLActivity;
import com.gdlactivity.libgdxdemo.utils.Constants;

/**
 * Created by Ivan_Hernandez on 05/12/2016.
 */

public class GateTransition extends AbstractTransition {

    private Texture fadeTwoTexture;

    private boolean isHorizontal = false;

    private float amount = 0;
    private float mid = 0;

    public GateTransition(boolean horizontalAlignment) {
        fadeTexture = new Texture(Gdx.files.internal("imgs/black.png"));
        fadeTwoTexture = new Texture(Gdx.files.internal("imgs/black.png"));

        this.isHorizontal = horizontalAlignment;

        if(this.isHorizontal)
            mid = Constants.SCREEN_WIDTH / 2;
        else
            mid = Constants.SCREEN_HEIGHT / 2;

    }

    public void render(float alpha) {

        alpha = Interpolation.fade.apply(alpha);
        amount = mid * alpha;

        GDLActivity.getSpriteBatch().begin();

        if(alpha < 0.5f) {

            if (isHorizontal) {
                GDLActivity.getSpriteBatch().draw(fadeTexture, 0, 0, amount * 2, Constants.SCREEN_HEIGHT);
                GDLActivity.getSpriteBatch().draw(fadeTwoTexture, Constants.SCREEN_WIDTH - amount * 2, 0, Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT);
            } else {
                GDLActivity.getSpriteBatch().draw(fadeTexture, 0, Constants.SCREEN_HEIGHT - amount * 2, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT / 2);
                GDLActivity.getSpriteBatch().draw(fadeTwoTexture, 0, 0, Constants.SCREEN_WIDTH, amount * 2);
            }

        } else {

            if (isHorizontal) {
                GDLActivity.getSpriteBatch().draw(fadeTexture, 0, 0, Constants.SCREEN_WIDTH - amount * 2, Constants.SCREEN_HEIGHT);
                GDLActivity.getSpriteBatch().draw(fadeTwoTexture, Constants.SCREEN_WIDTH / 2 + amount * 2 - Constants.SCREEN_WIDTH / 2, 0, Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT);
            } else {
                GDLActivity.getSpriteBatch().draw(fadeTexture, 0, Constants.SCREEN_HEIGHT / 2 - Constants.SCREEN_HEIGHT / 2 + amount * 2, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT / 2);
                GDLActivity.getSpriteBatch().draw(fadeTwoTexture, 0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT - amount * 2);
            }
        }

        GDLActivity.getSpriteBatch().end();

    }

}
