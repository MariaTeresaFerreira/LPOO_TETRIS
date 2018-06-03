package com.mygdx.mainpackage;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.mainpackage.Screens.ETMDScreen;
import com.mygdx.mainpackage.Screens.MGSDesktop;
import com.mygdx.mainpackage.Screens.MainMenuScreen;

public class Tetris extends Game {

	public SpriteBatch batch;
	public boolean sound;
	public boolean mobile; //False for Desktop, True for Android
	public Music music;
	public GameState g;

	public Float tToIncreaseSpeed;
	public Float speed;

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

	public GameState getGameState(){
		return g;
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		g = new GameState();
		/*
		REMOVE AFTER TESTING
		* */
		g.setMode('E');

		this.setScreen(new MainMenuScreen(this));
		tToIncreaseSpeed = (float) 0;
		speed = (float) 1;

		sound = true;
		music = Gdx.audio.newMusic(Gdx.files.internal("Mafiosa.mp3"));
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
		escapeTheMatrix = true;

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
