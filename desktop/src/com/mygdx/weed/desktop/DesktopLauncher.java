package com.mygdx.weed.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.weed.Tetris;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Tetris(), config);
		//config.fullscreen = true; HOLY FUCK
		//config.foregroundFPS = 60; 60 FPS podemos dar a opção de frames por segundo no menu inicial alterando este val
	}
}
