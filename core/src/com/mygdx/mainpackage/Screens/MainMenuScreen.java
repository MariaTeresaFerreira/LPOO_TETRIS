package com.mygdx.mainpackage.Screens;


import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.mainpackage.Tetris;

public class MainMenuScreen implements Screen{

    Tetris t;
    Texture background;
    Texture muteButton;
    Texture startButton;
    Texture highScoresButton;
    Texture exitButton;
    public static final float BUTTON_WIDTH = 150;
    public static final float BUTTON_HEIGHT = 75;

    public MainMenuScreen(Tetris t){

        this.t = t;
        background = new Texture("wallpaper.jpg");
        muteButton = new Texture("sound.png");
        startButton = new Texture("startButton.png");
        highScoresButton = new Texture("hsButton.png");
        exitButton = new Texture("exitButton.png");

    }

    @Override
    public void dispose() {

    }

    @Override
    public void render(float delta) {

        t.batch.begin();
        t.batch.draw(background, 0, 0);
        t.batch.draw(muteButton, 1500, 800);
        t.batch.draw(startButton, 700, 450, BUTTON_WIDTH, BUTTON_HEIGHT);
        t.batch.draw(highScoresButton, 700, 350, BUTTON_WIDTH, BUTTON_HEIGHT);
        t.batch.draw(exitButton, 700, 250, BUTTON_WIDTH, BUTTON_HEIGHT);
        t.batch.end();

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void resize(int width, int height) {

    }
}
