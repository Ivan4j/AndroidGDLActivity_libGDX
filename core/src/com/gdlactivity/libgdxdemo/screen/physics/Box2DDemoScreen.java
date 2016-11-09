package com.gdlactivity.libgdxdemo.screen.physics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
    private Button enablePhysicsDebugButton;
    private Button enableTextureButton;

    public Box2DDemoScreen() {

        physicsWorldCamera = new OrthographicCamera(Constants.SCREEN_WIDTH / Constants.PIXELS_PER_UNIT, Constants.SCREEN_HEIGHT / Constants.PIXELS_PER_UNIT);
        viewport = new FitViewport(Constants.SCREEN_WIDTH / 20, Constants.SCREEN_HEIGHT / 20, physicsWorldCamera);

        stage = new Box2DStage(physicsWorldCamera);

        backButton = new Button(ButtonType.CONTINUE);
        backButton.setBounds(Constants.BUTTON_SIZE_MED / 2, Constants.SCREEN_HEIGHT - Constants.BUTTON_SIZE_MED * 1.2f, Constants.BUTTON_SIZE_MED, Constants.BUTTON_SIZE_MED);

        enableTextureButton = new Button(ButtonType.CONTINUE);
        enableTextureButton.setBounds(Constants.BUTTON_SIZE_MED * 2f, Constants.SCREEN_HEIGHT - Constants.BUTTON_SIZE_MED * 1.2f, Constants.BUTTON_SIZE_MED, Constants.BUTTON_SIZE_MED);

        enablePhysicsDebugButton = new Button(ButtonType.CONTINUE);
        enablePhysicsDebugButton.setBounds(Constants.BUTTON_SIZE_MED * 3.5f, Constants.SCREEN_HEIGHT - Constants.BUTTON_SIZE_MED * 1.2f, Constants.BUTTON_SIZE_MED, Constants.BUTTON_SIZE_MED);

        uiComponents.add(backButton);
        uiComponents.add(enableTextureButton);
        uiComponents.add(enablePhysicsDebugButton);

        backButton.setAction(new LoadScreenAction(AvailableScreens.MENU));

        enableTextureButton.setAction(new IAction() {
            @Override
            public void execute() {
                stage.setDrawTextures(!stage.isDrawTextures());
            }
        });

        enablePhysicsDebugButton.setAction(new IAction() {
            @Override
            public void execute() {
                stage.setDrawPhysicsDebug(!stage.isDrawPhysicsDebug());
            }
        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        // Clear screen
        if(stage.isDrawPhysicsDebug())
            Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1);
        else
            Gdx.gl.glClearColor(1f, 1f, 1f, 1);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        backButton.draw(spriteBatch);
        enableTextureButton.draw(spriteBatch);
        enablePhysicsDebugButton.draw(spriteBatch);
        spriteBatch.end();

        stage.draw();
        stage.act(delta);

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

}
