package com.gdlactivity.libgdxdemo.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gdlactivity.libgdxdemo.GDLActivity;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = (int)(1080 * .45f);
		config.height = (int)(1920 * .45f);
		new LwjglApplication(new GDLActivity(), config);
	}
}
