package com.mygdx.mainpackage.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Screen;
import com.mygdx.mainpackage.Tetris;

public class MainGameScreen implements Screen{

    private Stage stage;
    private Tetris t;

    public MainGameScreen(Tetris t){
        this.t = t;
    }

    @Override
    public void dispose() {

    }

    @Override
    public void render(float delta) {
        t.batch.begin();
        t.batch.setColor(0, 0, 0, 1);
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
        //stage.getViewport().update(width, height);
    }

}
