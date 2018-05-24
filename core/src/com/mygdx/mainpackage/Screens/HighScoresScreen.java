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

public class HighScoresScreen implements Screen{

    private Tetris t;
    private Texture background;
    private Sprite phSprite;
    private Button phButton;
    private Viewport vp;
    private Stage stage;

    public static final float SCREEN_WIDTH = 1600;
    public static final float SCREEN_HEIGHT = 900;

    public HighScoresScreen(Tetris t){
        this.t = t;
        background = new Texture("wallpaper.jpg");
        phSprite = new Sprite(new Texture("phHSS.png"));
        phButton = new Button(new SpriteDrawable(phSprite));

        vp = new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT);

        stage = new Stage(vp, t.batch);
        stage.addActor(phButton);

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

        if(phButton.isPressed()) {
            this.dispose();
            t.setScreen(new MainMenuScreen(t));
            //TODO: SOUND NAO E SMP TRUE
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

