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


    //UI constants
    int BUTTON_SIZE_SMALL = 40;
    int BUTTON_SIZE_MED = 60;
    int BUTTON_SIZE_BIG = 100;


    //Box2D constants
    Vector2 GRAVITY_EARTH = new Vector2(0, -9.8f);
    Vector2 GRAVITY_NONE = new Vector2(0, 0);

    int PIXELS_PER_UNIT = 20;

}
