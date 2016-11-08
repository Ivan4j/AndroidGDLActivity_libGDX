package com.gdlactivity.libgdxdemo.screen.physics;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.gdlactivity.libgdxdemo.GDLActivity;
import com.gdlactivity.libgdxdemo.utils.Constants;

/**
 * Created by Ivan_Hernandez on 08/11/2016.
 */

public class Box2DStage extends Stage {

    private World physicsWorld;
    private Body physicsGround;

    private Body dynamicBody;

    protected MouseJoint mouseJoint = null;

    private Box2DDebugRenderer physicsRenderer;

    private float accumulator = 0;
    private final float step = 1 / 60f;

    public Box2DStage() {

        physicsRenderer = new Box2DDebugRenderer();

        physicsWorld = new World(Constants.GRAVITY, true);

        BodyDef def = new BodyDef();
        def.position.set(100, 0);
        physicsGround = physicsWorld.createBody(def);
        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsBox(Constants.SCREEN_WIDTH, 10);
        physicsGround.createFixture(groundShape, 0);
        groundShape.dispose();


        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(100, 300);

        dynamicBody = physicsWorld.createBody(bodyDef);

        CircleShape circle = new CircleShape();
        circle.setRadius(30f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 2.5f;
        fixtureDef.friction = 0.2f;
        fixtureDef.restitution = 0.5f;
        Fixture fixture = dynamicBody.createFixture(fixtureDef);
        circle.dispose();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        float frameTime = Math.min(delta, 0.25f);
        accumulator += frameTime;

        while (accumulator >= step) {
            physicsWorld.step(step, 6, 2);
            accumulator -= step;
        }
    }

    @Override
    public void draw() {
        super.draw();
        physicsRenderer.render(physicsWorld, GDLActivity.getCamera().combined);
    }

}
