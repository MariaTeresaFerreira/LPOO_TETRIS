package com.mygdx.mainpackage.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.mainpackage.Tetris;

public class GameSetupScreen implements Screen{

    private Tetris t;
    private Texture background;

    private Viewport vp;
    private Stage stage;

    private Sprite backSprite;
    private Button backButton;

    private Sprite infoSprite;
    private Button infoButton;

    private Sprite forwardSprite;
    private Button forwardButton;

    public static final float SCREEN_WIDTH = 1600;
    public static final float SCREEN_HEIGHT = 900;

    public GameSetupScreen(Tetris t){
        this.t = t;
        background = new Texture("wallpaper.jpg");

        backSprite = new Sprite(new Texture("back.png"));
        backSprite.setSize(100, 100);
        backButton = new Button(new SpriteDrawable(backSprite));

        infoSprite = new Sprite(new Texture("info.png"));
        infoSprite.setSize(100, 100);
        infoButton = new Button(new SpriteDrawable(infoSprite));
        infoButton.setX(100);

        forwardSprite = new Sprite(new Texture("forward.png"));
        forwardSprite.setSize(100, 100);
        forwardButton = new Button(new SpriteDrawable(forwardSprite));
        forwardButton.setX(SCREEN_WIDTH - 110);

        vp = new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT);

        stage = new Stage(vp, t.batch);
        stage.addActor(backButton);
        stage.addActor(infoButton);
        stage.addActor(forwardButton);

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

        if(backButton.isPressed()) {
            this.dispose();
            t.setScreen(new MainMenuScreen(t));
            //TODO: SOUND NAO E SMP TRUE
        }
        if(infoButton.isPressed()){
            this.dispose();
            t.setScreen(new GameSetupInfoScreen(t));
            //TODO: criar info screen
        }
        if(forwardButton.isPressed()){
            this.dispose();
            //t.setScreen(new MainGameScreen(t));
            //TODO: pedro ta a fazer
        }

        stage.act();

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

        stage.getViewport().update(width, height);
    }
}
