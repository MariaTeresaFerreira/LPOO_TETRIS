package com.mygdx.mainpackage.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.mainpackage.Tetris;

import java.awt.Checkbox;

public class GameSetupInfoScreen implements Screen {

    private Tetris t;
    private Texture background;

    private Sprite infoTextSprite;
    //private Button infoTextButton;
    private Sprite backSprite;
    private Button backButton;



    private Viewport vp;
    private Stage stage;


    public static final float SCREEN_WIDTH = 1600;
    public static final float SCREEN_HEIGHT = 900;

    public GameSetupInfoScreen(Tetris t){
        this.t = t;
        background = new Texture("background2.png");
        infoTextSprite = new Sprite(new Texture("infoText.png"));
        infoTextSprite.setSize(1028, 732);
        infoTextSprite.setX(300);
        infoTextSprite.setY(100);



        backSprite = new Sprite(new Texture("back.png"));
        backSprite.setSize(100, 100);
        backButton = new Button(new SpriteDrawable(backSprite));

        vp = new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT);

        stage = new Stage(vp, t.batch);
        stage.addActor(backButton);

        t.classic = false;
        t.krayZBlox = false;
        t.escapeTheMatrix = false;
        t.sprint = false;
        t.marathon = false;
        t.time = false;
        t.singlePlayer = false;
        t.createMultiplayer = false;
        t.joinMultiplayer = false;



        Gdx.input.setInputProcessor(stage);
    }


    @Override
    public void dispose() {

    }

    @Override
    public void render(float delta) {

        t.batch.begin();
        t.batch.draw(background, 0, 0);
        infoTextSprite.draw(t.batch);
        t.batch.end();
        stage.draw();
        if(backButton.isPressed()) {
            this.dispose();
            t.setScreen(new GameSetupScreen(t));
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
