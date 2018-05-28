package com.mygdx.mainpackage;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.mainpackage.Screens.MainGameScreen;
import com.mygdx.mainpackage.Screens.MainMenuScreen;

public class Tetris extends Game {

	public SpriteBatch batch;
	public boolean sound;

	/*
	* public static final float SPEED = pixels/segundo que queremos que a imagem ande, assim fica independente da fps
	* */

	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new MainGameScreen(this));

	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
