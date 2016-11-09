package com.gdlactivity.libgdxdemo.controller;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.gdlactivity.libgdxdemo.GDLActivity;
import com.gdlactivity.libgdxdemo.ui.Button;
import com.gdlactivity.libgdxdemo.ui.UIComponent;
import com.gdlactivity.libgdxdemo.utils.MathUtils;

import java.util.ArrayList;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Back;
import aurelienribon.tweenengine.equations.Cubic;
import aurelienribon.tweenengine.equations.Quad;
import aurelienribon.tweenengine.equations.Sine;

/**
 * Created by Ivan_Hernandez on 08/11/2016.
 */

public class UIController implements InputProcessor {


    Vector3 touchPoint = new Vector3();
    boolean dragging;

    private ArrayList<UIComponent> uiCopmponents;

    private UIComponent uiSelected = null;

    public UIController(ArrayList<UIComponent> screenUIComponents) {

        this.uiCopmponents = screenUIComponents;
    }


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        //GDLActivity.getCamera().unproject(touchPoint.set(screenX, screenY, 0));
        touchPoint = GDLActivity.getViewport().unproject(new Vector3(screenX,screenY,0));

        dragging = true;

        uiSelected = null;

        for(UIComponent ui : this.uiCopmponents) {
            if (MathUtils.checkTouchCollision(touchPoint, ui.getBoundingRectangle())) {

                uiSelected = ui;

                System.out.println("Touched");
                animateComponentIN(ui);

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        //GDLActivity.getCamera().unproject(touchPoint.set(screenX, screenY, 0));
        touchPoint = GDLActivity.getViewport().unproject(new Vector3(screenX,screenY,0));
        if (dragging) {
            for (UIComponent ui : this.uiCopmponents) {
                if (MathUtils.checkTouchCollision(touchPoint, ui.getBoundingRectangle())) {

                    System.out.println("Touched");
                    if(ui == uiSelected) {
                        animateComponentOUT(ui);
                        return true;
                    }
                }
            }
        }

        if(uiSelected != null)
            animateComponentOUT(uiSelected);

        dragging = false;

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        if (!dragging)
            return false;

        //GDLActivity.getCamera().unproject(touchPoint.set(screenX, screenY, 0));
        touchPoint = GDLActivity.getViewport().unproject(new Vector3(screenX,screenY,0));

        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }


    public void animateComponentIN(UIComponent component) {

        component.setScale(1);

        Tween.to(component, SpriteAccessor.SCALE_XY, 0.15f)
            .target(1.15f, 1.15f)
            .ease(Back.OUT)
            .start(GDLActivity.getTweenManager());

        System.out.println("Animate IN");

    }

    public void animateComponentOUT(final UIComponent component) {

        Tween.to(component, SpriteAccessor.SCALE_XY, 0.2f)
            .target(1f, 1f)
            .ease(Back.IN)
            .setCallback(new TweenCallback() {
                @Override
                public void onEvent(int i, BaseTween<?> baseTween) {
                    if(component instanceof Button)
                        ((Button) component).executeAction();
                }
            })
            .start(GDLActivity.getTweenManager());

        System.out.println("Animate OUT");

    }

}
