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

    /**
     * Game setup info screen Tetris object
     */
    private Tetris t;
    /**
     * Background texture
     */
    private Texture background;

    /**
     * Game mode information sprite
     */
    private Sprite infoTextSprite;
    /**
     * Back sprite
     */
    private Sprite backSprite;
    /**
     * Back button
     */
    private Button backButton;


    /**
     * Game setup information screen's viewport
     */
    private Viewport vp;
    /**
     * Game setup information screen's stage
     */
    private Stage stage;


    /**
     * Game setup information screen's width
     */
    public static final float SCREEN_WIDTH = 1600;
    /**
     * Game setup information screen's height
     */
    public static final float SCREEN_HEIGHT = 900;

    /**
     * Game setup information screen constructor
     * All the mode variables are turned back to false in order to reset the chosen options after the player has read
     * the information of both the game modes and end modes
     * @param t
     */
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

    /**
     * Draws all the necessary parts on the screen, them being the back button, the background, the game mode
     * information sprite
     * Checks if the back button has been pressed, and if so, a new game setup screen is set
     * @param delta
     */
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

    /**
     * This function updates the screen size, keeping the proportions
     * @param width
     * @param height
     */
    @Override
    public void resize(int width, int height) {

        stage.getViewport().update(width, height);
    }
}
