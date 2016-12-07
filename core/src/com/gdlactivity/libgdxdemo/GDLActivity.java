package com.gdlactivity.libgdxdemo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gdlactivity.libgdxdemo.controller.SpriteAccessor;
import com.gdlactivity.libgdxdemo.screen.Menu;
import com.gdlactivity.libgdxdemo.screen.ScreenManager;
import com.gdlactivity.libgdxdemo.screen.Splash;
import com.gdlactivity.libgdxdemo.screen.boids.BoidsDemoScreen;
import com.gdlactivity.libgdxdemo.screen.physics.Box2DDemoScreen;
import com.gdlactivity.libgdxdemo.utils.Constants;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

public class GDLActivity extends Game {



	//Singleton
	private static Camera camera;
	private static SpriteBatch batch;
	private static TweenManager tweenManager;
	private static Viewport viewport;
	private int initScreen = 0;

	public GDLActivity() {

	}

	public GDLActivity(int screenId) {


		this.initScreen = screenId;
	}

	@Override
	public void create () {

		//ScreenManager.getInstance().setGame(this);

		batch = new SpriteBatch();

		camera = new OrthographicCamera();
		viewport = new FitViewport(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, camera);
		viewport.apply();

		//camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);

		tweenManager = new TweenManager();

		Tween.registerAccessor(Sprite.class, new SpriteAccessor());

		//this.setScreen(new Splash());
		//this.setScreen(new Menu());

		Screen screen = null;

		switch(initScreen) {
			case 0:
				screen = new Splash();
				break;
			case 1:
				screen = new Menu();
				break;
			case 2:
				screen = new BoidsDemoScreen();
				break;
			case 3:
				screen = new Box2DDemoScreen();
				break;
		}

		ScreenManager.getInstance().setScreen(screen);

		//this.setScreen(screen);

	}


	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);

		ScreenManager.getInstance().render(); //getGame().getScreen().render(Gdx.graphics.getDeltaTime() * Constants.GLOBAL_SPEED_FACTOR);

		tweenManager.update(Gdx.graphics.getDeltaTime() * Constants.ANIMATION_SPEED_FACTOR_UI * Constants.GLOBAL_SPEED_FACTOR);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public void resize(int width, int height) {
		viewport.update(width, height, true);
		//camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
		camera.update();
	}

	public static SpriteBatch getSpriteBatch() {
		if(batch == null)
			batch = new SpriteBatch();
		return batch;
	}

	public static Camera getCamera() {
		if(camera == null)
			camera = new OrthographicCamera();
		return camera;
	}

	public static TweenManager getTweenManager() {
		if(tweenManager == null)
			tweenManager = new TweenManager();
		return tweenManager;
	}

	public static Viewport getViewport() {
		if(viewport == null) {
			viewport = new FitViewport(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, camera);
			viewport.apply();
		}
		return viewport;
	}

}
