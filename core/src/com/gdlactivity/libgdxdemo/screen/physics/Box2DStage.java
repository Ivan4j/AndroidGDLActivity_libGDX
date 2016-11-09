package com.gdlactivity.libgdxdemo.screen.physics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.gdlactivity.libgdxdemo.utils.Constants;

/**
 * Created by Ivan_Hernandez on 08/11/2016.
 */

public class Box2DStage extends Stage {

    private World physicsWorld;
    private Body wallBottom;
    private Body wallTop;
    private Body wallLeft;
    private Body wallRight;

    protected MouseJoint mouseJoint = null;

    private Box2DDebugRenderer physicsRenderer;

    private boolean drawPhysicsDebug = true;
    private boolean drawTextures = false;
    private boolean environmentCollision = false;

    private float accumulator = 0;
    private final float step = 1 / 60f;

    boolean accelerometerAvailable = Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer);

    private Array<Body> dynamicBodies;
    private Array<Body> staticBodies;
    private Array<Sprite> bodySprites;

    private int bodiesTotal = 25;
    private Texture[] boidTexture = new Texture[5];
    private String[] imageNames = {"imgs/redbird.png", "imgs/blackbird.png", "imgs/bluebird.png", "imgs/greenbird.png", "imgs/yellowbird.png"};


    private SpriteBatch batch;
    private Camera cam;


    public Box2DStage(Camera cam) {

        this.cam = cam;

        physicsRenderer = new Box2DDebugRenderer();

        physicsWorld = new World(Constants.GRAVITY_NONE, true);

        dynamicBodies = new Array<Body>();
        bodySprites = new Array<Sprite>();
        staticBodies  = new Array<Body>();

        for(int i=0; i<imageNames.length; i++) {
            boidTexture[i] = new Texture(Gdx.files.internal(imageNames[i]));
            boidTexture[i].setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }

        buildWalls();
        buildDynamicBodies();
        //buildStaticEnvironment();

        batch = new SpriteBatch();

    }

    private void buildWalls() {

        BodyDef def = new BodyDef();
        def.position.set(0, 0);
        wallBottom = physicsWorld.createBody(def);
        PolygonShape bottomShape = new PolygonShape();
        bottomShape.setAsBox(Constants.SCREEN_WIDTH / Constants.PIXELS_PER_UNIT, 0.5f);
        wallBottom.createFixture(bottomShape, 0);
        bottomShape.dispose();

        def.position.set(Constants.SCREEN_WIDTH / Constants.PIXELS_PER_UNIT, Constants.SCREEN_HEIGHT / Constants.PIXELS_PER_UNIT);
        wallTop = physicsWorld.createBody(def);
        PolygonShape topShape = new PolygonShape();
        topShape.setAsBox(Constants.SCREEN_WIDTH / Constants.PIXELS_PER_UNIT, 4);
        wallTop.createFixture(topShape, 0);
        topShape.dispose();

        def.position.set(0, Constants.SCREEN_HEIGHT / Constants.PIXELS_PER_UNIT);
        wallLeft = physicsWorld.createBody(def);
        PolygonShape leftShape = new PolygonShape();
        leftShape.setAsBox(0.5f, Constants.SCREEN_HEIGHT / Constants.PIXELS_PER_UNIT);
        wallLeft.createFixture(leftShape, 0);
        leftShape.dispose();

        def.position.set(Constants.SCREEN_WIDTH / Constants.PIXELS_PER_UNIT, Constants.SCREEN_HEIGHT / Constants.PIXELS_PER_UNIT);
        wallRight = physicsWorld.createBody(def);
        PolygonShape rightShape = new PolygonShape();
        rightShape.setAsBox(0.5f, Constants.SCREEN_HEIGHT / Constants.PIXELS_PER_UNIT);
        wallRight.createFixture(rightShape, 0);
        rightShape.dispose();

    }

    private void buildStaticEnvironment() {

        BodyDef def = new BodyDef();
        def.position.set(0, 0);
        Body bodyA = physicsWorld.createBody(def);
        PolygonShape bottomShape = new PolygonShape();
        bottomShape.setAsBox(Constants.SCREEN_WIDTH / Constants.PIXELS_PER_UNIT * 0.71f, 8.1f);
        bodyA.createFixture(bottomShape, 0);
        bottomShape.dispose();

        staticBodies.add(bodyA);

        def.position.set(Constants.SCREEN_WIDTH / Constants.PIXELS_PER_UNIT * 0.4f, 0);
        Body bodyB = physicsWorld.createBody(def);
        PolygonShape topShape = new PolygonShape();
        topShape.setAsBox(Constants.SCREEN_WIDTH / Constants.PIXELS_PER_UNIT * 0.082f, 17f);
        bodyB.createFixture(topShape, 0);
        topShape.dispose();

        staticBodies.add(bodyB);
    }

    public void buildDynamicBodies() {

        CircleShape circle = new CircleShape();
        //circle.setRadius(1f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 2.5f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.5f;

        for(int i=0; i<bodiesTotal; i++) {

            BodyDef bodyDef = new BodyDef();
            bodyDef.type = BodyDef.BodyType.DynamicBody;
            bodyDef.position.set((float)Math.random() * (Constants.SCREEN_WIDTH / Constants.PIXELS_PER_UNIT - 3) + 2, (float)Math.random() * (Constants.SCREEN_HEIGHT / Constants.PIXELS_PER_UNIT - 4) + 2);

            Body dynamicBody = physicsWorld.createBody(bodyDef);

            float radius = (float)Math.random() * 1f + 0.5f;
            circle.setRadius(radius);

            int textureIndex = (int)(Math.random() * 5);

            Sprite bodySprite = new Sprite(boidTexture[textureIndex]);
            float width = radius / boidTexture[textureIndex].getWidth() * boidTexture[textureIndex].getWidth() * 2.1f;
            float height = radius / boidTexture[textureIndex].getWidth() * boidTexture[textureIndex].getHeight() * 2.1f;
            bodySprite.setBounds(0, 0, width, height);
            bodySprite.setOrigin(width / 2, height / 2);

            dynamicBody.createFixture(fixtureDef);

            dynamicBodies.add(dynamicBody);
            bodySprites.add(bodySprite);
        }

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

        if(accelerometerAvailable) {

            float accelX = Gdx.input.getAccelerometerX();
            float accelY = Gdx.input.getAccelerometerY();
            float accelZ = Gdx.input.getAccelerometerZ();

            for(Body body : dynamicBodies)
                body.applyForceToCenter(accelX * 25 * -1, accelY * 25 * - 1, true);

        }
    }

    @Override
    public void draw() {
        super.draw();

        if(drawPhysicsDebug && !drawTextures) {
            physicsRenderer.render(physicsWorld, cam.combined);
        }

        batch.setProjectionMatrix(cam.combined);

        if(drawTextures) {
            batch.begin();
            for(int i=0; i < dynamicBodies.size; i++) {
                bodySprites.get(i).setPosition(dynamicBodies.get(i).getPosition().x - bodySprites.get(i).getWidth() / 2, dynamicBodies.get(i).getPosition().y- bodySprites.get(i).getHeight() / 2);
                bodySprites.get(i).setRotation(dynamicBodies.get(i).getAngle() * MathUtils.radDeg);
                bodySprites.get(i).draw(batch);
            }
            batch.end();
        }
    }

    public boolean isDrawTextures() {
        return drawTextures;
    }
/*
    public boolean isDrawPhysicsDebug() {
        return drawPhysicsDebug;
    }

    public void setDrawPhysicsDebug(boolean drawPhysicsDebug) {
        this.drawPhysicsDebug = drawPhysicsDebug;
    }
*/
    public void setDrawTextures(boolean drawTextures) {
        this.drawTextures = drawTextures;
    }

    public void setEnvironmentCollisions(boolean environmentCollisions) {

        this.environmentCollision = environmentCollisions;

        if(this.environmentCollision) {
            buildStaticEnvironment();
            System.out.println("created");
        } else {
            System.out.println("removed");

            for(Body body : staticBodies) {
                physicsWorld.destroyBody(body);
                staticBodies.removeValue(body, true);
                body.setUserData(null);
                body = null;
            }
        }

    }

    public boolean isEnvironmentCollisions() {
        return environmentCollision;
    }
}
