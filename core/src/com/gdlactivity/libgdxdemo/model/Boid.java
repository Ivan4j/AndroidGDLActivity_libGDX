package com.gdlactivity.libgdxdemo.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.gdlactivity.libgdxdemo.MathUtils;

import java.util.ArrayList;

/**
 * Created by Ivan_Hernandez on 07/11/2016.
 */

public class Boid extends Sprite {

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;


    private Vector2 alignment;
    private Vector2 cohesion;
    private Vector2 separation;

    private Vector2 wallAvoidance;

    float alignmentWeight = 0.5f;
    float cohesionWeight = 0.3f;
    float separationWeight = 0.55f;

    static final float MAX_SPEED = 3f;
    static final float MAX_FORCE = 1.5f;
    static final float MIN_DISTANCE = 40;

    public Boid(Texture texture) {
        super(texture);

        this.position = new Vector2(0, 0);
        velocity = new Vector2((float)(Math.random() * 3)-1, (float)(Math.random() * 3)-1);
        acceleration = new Vector2(0, 0);

    }

    @Override
    public void setBounds(float x, float y, float width, float height) {
        super.setBounds(x, y, width, height);
        this.position.x = x;
        this.position.y = y;
        this.setOrigin(width/2, height/2);
    }

    public void update(ArrayList<Boid> boids) {

        flock(boids);

        wallAvoidance = wallAvoidance();

        this.velocity.x += wallAvoidance.x * 0.1;
        this.velocity.y += wallAvoidance.y * 0.1;

        this.position.x += velocity.x;
        this.position.y += velocity.y;

        this.setPosition(this.position.x, this.position.y);

        this.setRotation(MathUtils.getAngle(velocity));

    }

    public void flock(ArrayList<Boid> boids) {

        alignment = processAlignment(boids);
        cohesion = processCohesion(boids);
        separation = processSeparation(boids);

        this.velocity.x += alignment.x * alignmentWeight + cohesion.x * cohesionWeight + separation.x * separationWeight;
        this.velocity.y += alignment.y * alignmentWeight + cohesion.y * cohesionWeight + separation.y * separationWeight;

        velocity.limit(MAX_SPEED);
    }

    public Vector2 processAlignment(ArrayList<Boid> boids) {

        Vector2 force = new Vector2();
        int neighborCount = 0;

        for (Boid b : boids)
        {
            if (this != b)
            {
                double dist = MathUtils.distance(this.position, b.position);

                if (dist > 0 && dist < MIN_DISTANCE)
                {
                    force.x += b.velocity.x;
                    force.y += b.velocity.y;
                    neighborCount++;
                }
            }
        }

        if (neighborCount == 0) {
            return force;
        }

        force.x /= neighborCount;
        force.y /= neighborCount;

        force.nor();

        return force;
    }

    public Vector2 processCohesion(ArrayList<Boid> boids) {

        Vector2 force = new Vector2();

        int neighborCount = 0;

        for (Boid b : boids) {
            if (this != b) {
                double dist = MathUtils.distance(this.position, b.position);

                if (dist > 0 && dist < MIN_DISTANCE) {
                    //force.add(b.position);
                    force.x += b.position.x;
                    force.y += b.position.y;

                    neighborCount++;
                }
            }
        }

        if (neighborCount == 0) {
            return force;
        }

        force.x /= neighborCount;
        force.y /= neighborCount;

        Vector2 force2 = new Vector2(force.x - this.position.x, force.y - this.position.y);
        force2.nor();

        return force2;
    }

    public Vector2 processSeparation(ArrayList<Boid> boids) {

        Vector2 force = new Vector2();

        int neighborCount = 0;

        for (Boid b : boids) {

            if (this != b) {

                float dist = MathUtils.distance(this.position, b.position);

                if (dist > 0 && dist <= MIN_DISTANCE) {

                    force.x += b.position.x - this.position.x;
                    force.y += b.position.y - this.position.y;
                    neighborCount++;
                }
            }
        }

        if (neighborCount == 0) {
            return force;
        }

        force.x /= neighborCount;
        force.y /= neighborCount;

        force.x *= -1;
        force.y *= -1;

        force.nor();

        return force;
    }

    public Vector2 seek(Vector2 target) {
        Vector2 steer = target.sub(position);
        steer.nor();
        steer.scl(MAX_SPEED);
        steer.sub(velocity);
        steer.limit(MAX_FORCE);
        return steer;
    }


    public Vector2 wallAvoidance() {

        Vector2 force = new Vector2();

        if(this.position.x < 20)
            force.x = 1;
        else if(this.position.x > 480 - 40)
            force.x = -1;

        if(this.position.y < 20)
            force.y = 1;
        else if(this.position.y > 800 - 40)
            force.y = -1;

        return force;

    }

}
