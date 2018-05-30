package com.mygdx.mainpackage;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.mainpackage.Screens.MainGameScreen;
import com.mygdx.mainpackage.Screens.MainMenuScreen;

public class Tetris extends Game {

	public SpriteBatch batch;
	public boolean sound;
	public Music music;
	public GameState g;

	public boolean classic;
	public boolean krayZBlox;
	public boolean escapeTheMatrix;
	public boolean sprint;
	public boolean marathon;
	public boolean time;
	public boolean singlePlayer;
	public boolean createMultiplayer;
	public boolean joinMultiplayer;


	/*
	* public static final float SPEED = pixels/segundo que queremos que a imagem ande, assim fica independente da fps
	* */

	@Override
	public void create () {
		batch = new SpriteBatch();
		g = new GameState('N');
		this.setScreen(new MainGameScreen(this));

		sound = true;
		music = Gdx.audio.newMusic(Gdx.files.internal("Tetris.mp3"));
		music.setLooping(true);
		music.setVolume(1);
		music.play();

		classic = false;
		krayZBlox = false;
		escapeTheMatrix = false;
		sprint = false;
		marathon = false;
		time = false;
		singlePlayer = false;
		createMultiplayer = false;
		joinMultiplayer = false;
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
