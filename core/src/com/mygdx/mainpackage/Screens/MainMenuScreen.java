package com.mygdx.mainpackage.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.mainpackage.Tetris;

public class MainMenuScreen implements Screen{

    private Tetris t;
    private Texture background= new Texture("background2.png");
    private Sprite muteSprite = new Sprite (new Texture("sound.png"));
    private Sprite startSprite = new Sprite (new Texture("startButton.png"));
    private Sprite highScoresSprite = new Sprite (new Texture("hsButton.png"));
    private Sprite exitSprite = new Sprite(new Texture("exitButton.png"));

    private Button exitButton;
    private Button startButton;
    private Button highScoresButton;
    private Button  muteButton = new Button(new SpriteDrawable(muteSprite));

    private Viewport vp = new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT);
    private Stage stage;

    public static final float SCREEN_WIDTH = 1600;
    public static final float SCREEN_HEIGHT = 900;



    public MainMenuScreen(final Tetris t){

        this.t = t;
        muteButton.setX(Gdx.graphics.getWidth() - muteSprite.getWidth());
        muteButton.setY(Gdx.graphics.getHeight() - muteSprite.getRegionHeight());
        muteButton.addListener(new ClickListener(){
          @Override
            public void clicked(InputEvent event, float x, float y){
              SwapImageClick.mute(t, muteSprite);
          }
        });

        startSprite.setSize((float) (Gdx.graphics.getWidth()*0.09375), (float) (Gdx.graphics.getHeight()*0.0834));
        exitSprite.setSize((float) (Gdx.graphics.getWidth()*0.09375), (float) (Gdx.graphics.getHeight()*0.0834));
        highScoresSprite.setSize((float) (Gdx.graphics.getWidth()*0.09375), (float) (Gdx.graphics.getHeight()*0.0834));

        startButton = new Button(new SpriteDrawable(startSprite));
        startButton.setX(Gdx.graphics.getWidth()/2 - startButton.getWidth()/2);
        startButton.setY(Gdx.graphics.getHeight()/2 - startButton.getHeight()/2 + startButton.getHeight() * (float)1.5);

        highScoresButton = new Button(new SpriteDrawable(highScoresSprite));
        highScoresButton.setX(Gdx.graphics.getWidth()/2 - highScoresButton.getWidth()/2);
        highScoresButton.setY(Gdx.graphics.getHeight()/2 - highScoresButton.getHeight()/2);

        exitButton = new Button(new SpriteDrawable(exitSprite));
        exitButton.setX(Gdx.graphics.getWidth()/2 - exitButton.getWidth()/2);
        exitButton.setY(Gdx.graphics.getHeight()/2 - exitButton.getHeight()/2 - exitButton.getHeight() * (float) 1.5);

        stage = new Stage(vp, t.batch);
        stage.addActor(startButton);
        stage.addActor(highScoresButton);
        stage.addActor(exitButton);
        stage.addActor(muteButton);

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
        //Para podermos dar inputs ao stage :P

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

        if (highScoresButton.isPressed()){
            this.dispose();
            t.setScreen(new HighScoresScreen(t));
        }
        if (startButton.isPressed()){
            this.dispose();
            t.setScreen(new GameSetupScreen(t));
        }

    }
/*
        if (muteButton.isPressed()){
            //TODO: DESLIGAR MUSICA (PARA ISSO E PRECISO PRIMEIRO TER MUSICA)
            //TODO: LIGAR MUSICA (PARA ISSO E PRECISO PRIMEIRO TER MUSICA)
            if (this.sound == true) {
                muteSprite.set(new Sprite(new Texture("mute.png")));
                this.sound = false;
            }else {
                muteSprite.set(new Sprite(new Texture("sound.png")));
                this.sound = true;
            }

        stage.act();

    }*/


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
