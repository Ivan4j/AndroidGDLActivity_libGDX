package com.gdlactivity.libgdxdemo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gdlactivity.libgdxdemo.screen.AbstractScreen;
import com.gdlactivity.libgdxdemo.screen.BoidsDemo;

public class GDLActivity extends ApplicationAdapter {

	private static SpriteBatch batch;
	Texture img;

	private Viewport viewport;
	private Camera camera;

	AbstractScreen screen;

	@Override
	public void create () {

		batch = new SpriteBatch();
		img = new Texture("imgs/Android_Logo.png");

		camera = new OrthographicCamera();
		viewport = new FitViewport(480, 800, camera);

		screen = new BoidsDemo();


	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		/*
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
		*/

		batch.setProjectionMatrix(camera.combined);

		screen.render(Gdx.graphics.getDeltaTime());


	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	public void resize(int width, int height) {
		viewport.update(width, height, true);
	}

	public static SpriteBatch getSpriteBatch() {

		if(batch == null)
			batch = new SpriteBatch();

		return batch;
	}
}
