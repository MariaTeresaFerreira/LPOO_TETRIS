package com.mygdx.mainpackage.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.mygdx.mainpackage.Tetris;

public class MainMenuScreen implements Screen{

    private Tetris t;
    private Texture background;
    private Sprite muteSprite;
    private Sprite startSprite;
    private Sprite highScoresSprite;
    private Sprite exitSprite;

    private Button exitButton;
    private Button startButton;
    private Button highScoresButton;

    public static final float BUTTON_WIDTH = 150;
    public static final float BUTTON_HEIGHT = 75;

    public MainMenuScreen(Tetris t){

        this.t = t;
        background = new Texture("wallpaper.jpg");
        muteSprite = new Sprite (new Texture("sound.png"));


        startSprite = new Sprite (new Texture("startButton.png"));
        startSprite.setSize( (float) (Gdx.graphics.getWidth()*0.09375),  (float) (Gdx.graphics.getHeight()*0.0834));
        startButton = new Button(new SpriteDrawable(startSprite));
        startButton.setX(Gdx.graphics.getWidth()/2 - startButton.getWidth()/2);
        startButton.setY(Gdx.graphics.getHeight()/2 - startButton.getHeight()/2 + startButton.getHeight() * (float)1.5);

        highScoresSprite = new Sprite (new Texture("hsButton.png"));
        highScoresSprite.setSize( (float) (Gdx.graphics.getWidth()*0.09375),  (float) (Gdx.graphics.getHeight()*0.0834));
        highScoresButton = new Button(new SpriteDrawable(highScoresSprite));
        highScoresButton.setX(Gdx.graphics.getWidth()/2 - highScoresButton.getWidth()/2);
        highScoresButton.setY(Gdx.graphics.getHeight()/2 - highScoresButton.getHeight()/2);

        exitSprite = new Sprite(new Texture("exitButton.png"));
        exitSprite.setSize( (float) (Gdx.graphics.getWidth()*0.09375),  (float) (Gdx.graphics.getHeight()*0.0834));
        exitButton = new Button(new SpriteDrawable(exitSprite));
        exitButton.setX(Gdx.graphics.getWidth()/2 - exitButton.getWidth()/2);
        exitButton.setY(Gdx.graphics.getHeight()/2 - exitButton.getHeight()/2 - exitButton.getHeight() * (float) 1.5);


        exitSprite.setSize( (float) (Gdx.graphics.getWidth()*0.09375),  (float) (Gdx.graphics.getHeight()*0.0834));

    }

    @Override
    public void dispose() {

    }

    @Override
    public void render(float delta) {

        t.batch.begin();
        t.batch.draw(background, 0, 0);
        //background.draw(this.t.batch, );
        t.batch.draw(muteSprite, 1500, 800);
        startButton.draw(t.batch, 1);
        highScoresButton.draw(t.batch, 1);
        exitButton.draw(t.batch, 1);
        //t.batch.draw(highScoresSprite, 700, 350, BUTTON_WIDTH, BUTTON_HEIGHT);
        //t.batch.draw(exitSprite, 700, 250, BUTTON_WIDTH, BUTTON_HEIGHT);
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
