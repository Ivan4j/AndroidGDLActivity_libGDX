package com.gdlactivity.libgdxdemo.screen.physics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gdlactivity.libgdxdemo.GDLActivity;
import com.gdlactivity.libgdxdemo.action.IAction;
import com.gdlactivity.libgdxdemo.action.LoadScreenAction;
import com.gdlactivity.libgdxdemo.model.Boid;
import com.gdlactivity.libgdxdemo.screen.AbstractScreen;
import com.gdlactivity.libgdxdemo.screen.AvailableScreens;
import com.gdlactivity.libgdxdemo.ui.Button;
import com.gdlactivity.libgdxdemo.ui.ButtonType;
import com.gdlactivity.libgdxdemo.ui.UIComponent;
import com.gdlactivity.libgdxdemo.utils.Constants;

import java.util.ArrayList;

/**
 * Created by Ivan_Hernandez on 07/11/2016.
 */

public class Box2DDemoScreen extends AbstractScreen {

    Box2DStage stage;

    private Viewport viewport;
    private Camera physicsWorldCamera;

    private Button backButton;
    private Button enableTextureButton;
    private Button enableEnvironmentCollision;

    public Box2DDemoScreen() {

        physicsWorldCamera = new OrthographicCamera(Constants.SCREEN_WIDTH / Constants.PIXELS_PER_UNIT, Constants.SCREEN_HEIGHT / Constants.PIXELS_PER_UNIT);
        viewport = new FitViewport(Constants.SCREEN_WIDTH / 20, Constants.SCREEN_HEIGHT / 20, physicsWorldCamera);

        stage = new Box2DStage(physicsWorldCamera);

        backButton = new Button(ButtonType.BACK);
        backButton.setBounds(Constants.BUTTON_SIZE_MED / 2, Constants.SCREEN_HEIGHT - Constants.BUTTON_SIZE_MED * 1.2f, Constants.BUTTON_SIZE_MED, Constants.BUTTON_SIZE_MED);

        enableTextureButton = new Button(ButtonType.OK);
        enableTextureButton.setBounds(Constants.BUTTON_SIZE_MED * 2f, Constants.SCREEN_HEIGHT - Constants.BUTTON_SIZE_MED * 1.2f, Constants.BUTTON_SIZE_MED, Constants.BUTTON_SIZE_MED);

        enableEnvironmentCollision = new Button(ButtonType.RESTART);
        enableEnvironmentCollision.setBounds(Constants.BUTTON_SIZE_MED * 3.5f, Constants.SCREEN_HEIGHT - Constants.BUTTON_SIZE_MED * 1.2f, Constants.BUTTON_SIZE_MED, Constants.BUTTON_SIZE_MED);

        uiComponents.add(backButton);
        uiComponents.add(enableTextureButton);
        uiComponents.add(enableEnvironmentCollision);

        backButton.setAction(new LoadScreenAction(AvailableScreens.MENU));

        enableTextureButton.setAction(new IAction() {
            @Override
            public void execute() {

                stage.setDrawTextures(!stage.isDrawTextures());

                try {

                    if(stage.isDrawTextures())
                        backgroundTexture = new Texture(Gdx.files.internal("imgs/ab_background02.png"));
                    else
                        backgroundTexture = new Texture(Gdx.files.internal("imgs/background_white.png"));

                    backgroundTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                    backgroundSprite.setTexture(backgroundTexture);

                } catch(Exception e) {
                    System.err.println(e.getMessage());
                    System.err.println("Background not found, default one applied.");
                }

            }
        });

        enableEnvironmentCollision.setAction(new IAction() {
            @Override
            public void execute() {
                stage.setEnvironmentCollisions(!stage.isEnvironmentCollisions());
            }
        });



    }

    @Override
    public void render(float delta) {
        super.render(delta);

        spriteBatch.begin();
        backButton.draw(spriteBatch);
        enableTextureButton.draw(spriteBatch);
        enableEnvironmentCollision.draw(spriteBatch);
        spriteBatch.end();

        stage.draw();
        stage.act(delta);

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        stage.getViewport().update(width, height, true);
    }

}
