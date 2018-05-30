package com.mygdx.mainpackage.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.mainpackage.Tetris;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		Tetris t = new Tetris();
		t.mobile = false;
		new LwjglApplication(t, config);
		config.resizable = true;
		//TODO: Tentar por isto adaptável (se tivermos tempo :P )
		config.width = 1600;
		config.height = 900;
		//config.fullscreen = true;
		//config.foregroundFPS = 60; 60 FPS podemos dar a opção de frames por segundo no menu inicial alterando este val
	}
}
