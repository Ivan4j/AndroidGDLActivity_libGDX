package com.gdlactivity.libgdxdemo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gdlactivity.libgdxdemo.controller.SpriteAccessor;
import com.gdlactivity.libgdxdemo.screen.Menu;
import com.gdlactivity.libgdxdemo.screen.Splash;
import com.gdlactivity.libgdxdemo.utils.Constants;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

public class GDLActivity extends Game {

	private Viewport viewport;

	//Singleton
	private static Camera camera;
	private static SpriteBatch batch;
	private static TweenManager tweenManager;

	@Override
	public void create () {

		batch = new SpriteBatch();

		camera = new OrthographicCamera();
		viewport = new FitViewport(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, camera);

		tweenManager = new TweenManager();

		Tween.registerAccessor(Sprite.class, new SpriteAccessor());

		this.setScreen(new Splash());
		//this.setScreen(new Menu());

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);

		getScreen().render(Gdx.graphics.getDeltaTime() * Constants.GLOBAL_SPEED_FACTOR);

		tweenManager.update(Gdx.graphics.getDeltaTime() * Constants.ANIMATION_SPEED_FACTOR_UI * Constants.GLOBAL_SPEED_FACTOR);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public void resize(int width, int height) {
		viewport.update(width, height, true);
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
}
