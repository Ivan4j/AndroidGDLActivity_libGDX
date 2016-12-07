package com.gdlactivity.libgdxdemo.screen.transitions;

import com.badlogic.gdx.math.Interpolation;
import com.gdlactivity.libgdxdemo.GDLActivity;
import com.gdlactivity.libgdxdemo.utils.Constants;

/**
 * Created by Ivan_Hernandez on 05/12/2016.
 */

public class FadeTransition extends AbstractTransition {

    public void render(float alpha) {

        alpha = Interpolation.fade.apply(alpha);

        GDLActivity.getSpriteBatch().begin();

        if(alpha < 0.5f) {
            GDLActivity.getSpriteBatch().setColor(1, 1, 1, alpha * 2);
        } else {
            GDLActivity.getSpriteBatch().setColor(1, 1, 1, 1 - alpha * 2);
        }

        GDLActivity.getSpriteBatch().draw(fadeTexture, 0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        GDLActivity.getSpriteBatch().end();

    }

}
