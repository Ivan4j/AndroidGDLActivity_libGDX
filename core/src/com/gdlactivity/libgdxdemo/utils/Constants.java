package com.gdlactivity.libgdxdemo.utils;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Ivan_Hernandez on 08/11/2016.
 */

public interface Constants {

    int SCREEN_WIDTH = 480;
    int SCREEN_HEIGHT = 800;

    //Update this value to change the game speed. Default = 1f
    float GLOBAL_SPEED_FACTOR = 1f;

    //Another speed factor, for the UI animation speed. Default = 1f
    float ANIMATION_SPEED_FACTOR_UI = 1f;


    //Box2D constants
    Vector2 GRAVITY = new Vector2(0, -9.8f);
}
