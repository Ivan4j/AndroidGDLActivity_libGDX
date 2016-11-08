package com.gdlactivity.libgdxdemo;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Ivan_Hernandez on 07/11/2016.
 */

public class MathUtils {

    public static float distance(Vector2 posA, Vector2 posB) {

        double xSub = posB.x - posA.x;
        double ySub = posB.y - posA.y;

        return (float)Math.sqrt((xSub * xSub) + (ySub * ySub));
    }

    public static float getAngle(Vector2 velocityVector) {

        return (float)Math.atan2(velocityVector.y, velocityVector.x) * 180 / (float)Math.PI;

    }

}
