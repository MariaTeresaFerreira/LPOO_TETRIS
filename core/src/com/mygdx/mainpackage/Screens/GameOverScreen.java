package com.mygdx.mainpackage.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.mainpackage.GameState;
import com.mygdx.mainpackage.Tetris;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class GameOverScreen implements Screen{

    private Texture background = new Texture("background2.png");

    private Sprite mainMenuSprite = new Sprite(new Texture("MainMenuButton.png"));
    private Sprite exitSprite = new Sprite(new Texture("exitButton.png"));

    private Button mainMenuButton;
    private Button exitButton;

    private Stage stage;
    private Viewport vp;
    private Tetris t;

    public GameOverScreen(Tetris t){

        this.t = t;

        exitSprite.setSize((float) (Gdx.graphics.getWidth()*0.09375), (float) (Gdx.graphics.getHeight()*0.0834));
        exitButton = new Button(new SpriteDrawable(exitSprite));
        exitButton.setX(Gdx.graphics.getWidth()/2 - exitButton.getWidth()/2);
        exitButton.setY(Gdx.graphics.getHeight()/2 - exitButton.getHeight()/2 - exitButton.getHeight() * (float) 1.5);

        mainMenuSprite.setSize((float) (Gdx.graphics.getWidth()*0.09375), (float) (Gdx.graphics.getHeight()*0.0834));
        mainMenuButton = new Button(new SpriteDrawable(mainMenuSprite));
        mainMenuButton.setX(Gdx.graphics.getWidth()/2 - mainMenuButton.getWidth()/2);
        mainMenuButton.setY(Gdx.graphics.getHeight()/2 - mainMenuButton.getHeight()/2);

        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()), t.batch);
        stage.addActor(exitButton);
        stage.addActor(mainMenuButton);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void dispose() {

    }

    @Override
    public void render(float delta) {

        t.batch.begin();
        t.batch.draw(background, 0, 0);
        t.batch.end();
        stage.draw();

        if (exitButton.isPressed()){
            Gdx.app.exit();
        }

        if(mainMenuButton.isPressed()){
            this.dispose();
            t.setScreen(new MainMenuScreen(t));
        }

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
