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

public class GameSetupScreen implements Screen{

    /**
     * Game setup screen Tetris object
     */
    private Tetris t;
    /**
     * Background texture
     */
    private Texture background;

    /**
     * Game setup screen's viewport
     */
    private Viewport vp;
    /**
     * Game setup information screen's stage
     */
    private Stage stage;

    /**
     * Back sprite
     */
    private Sprite backSprite;
    /**
     * Back button
     */
    private Button backButton;

    /**
     * Game mode information (i) sprite
     */
    private Sprite infoSprite;
    /**
     * Game mode information button
     */
    private Button infoButton;

    /**
     * Forward sprite
     */
    private Sprite forwardSprite;
    /**
     * Forward button
     */
    private Button forwardButton;

    /**
     * Unchecked texture for the mode's checkbox buttons
     */
    private Texture unchecked;



    /**
     * Classic mode sprite
     */
    private Sprite classicSprite;
    /**
     * Classic mode button
     */
    private Button classicButton;
    /**
     * Kray-Z Blox mode sprite
     */
    private Sprite krayZBloxSprite;
    /**
     * Kray-Z Blox mode Button
     */
    private Button krayZBloxButton;
    /**
     * Escape the matrix mode sprite
     */
    private Sprite escapeTheMatrixSprite;
    /**
     * Escape the matrix mode button
     */
    private Button escapeTheMatrixButton;
    /**
     * Sprint mode sprite
     */
    private Sprite sprintSprite;
    /**
     * Sprint mode button
     */
    private Button sprintButton;
    /**
     * Marathon mode sprite
     */
    private Sprite marathonSprite;
    /**
     * Marathon mode button
     */
    private Button marathonButton;
    /**
     * Time mode sprite
     */
    private Sprite timeSprite;
    /**
     * Time mode button
     */
    private Button timeButton;
    /**
     * Single player mode sprite
     */
    private Sprite singleplayerSprite;
    /**
     * Single player mode button
     */
    private Button singleplayerButton;
    /**
     * Create multi player mode sprite
     */
    private Sprite createMultiplayerSprite;
    /**
     * Create multi player mode button
     */
    private Button createMultiplayerButton;
    /**
     * Join multi player mode sprite
     */
    private Sprite joinMultiplayerSprite;
    /**
     * Join multi player mode button
     */
    private Button joinMultiplayerButton;

    /**
     * Game mode selector layout sprite
     */
    private Sprite text;

    /**
     * Game setup information screen's width
     */
    public static final float SCREEN_WIDTH = 1600;
    /**
     * Game setup information screen's height
     */
    public static final float SCREEN_HEIGHT = 900;


    /**
     * Game setup screen constructor
     * All the mode's checkboxes are added to the stage after being formatted accordingly to the layout sprite
     * Click listeners are added to all the buttons and the buttons regarding each part of the game setup are exclusive,
     * this is, only one box can be selected for each row
     * The escape the matrix mode is only compatible with the time and single player modes, so if the player tries to
     * start the game with non compatible setting regarding the escape the matrix mode, this box will automatically deselected
     * The user can only start a game after there are all the needed modes chosen
     * @param t
     */
    public GameSetupScreen(final Tetris t){
        this.t = t;
        background = new Texture("background2.png");

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

        text = new Sprite(new Texture("gamesetup.png"));
        text.setSize(1028, 732);
        text.setX(300);
        text.setY(100);


        unchecked = new Texture("unchecked.png");
        //checked = new Texture("checked.png");

        //0
        classicSprite = new Sprite(unchecked);
        classicButton = new Button(new SpriteDrawable(classicSprite));
        classicButton.setY(663);
        classicButton.setX(400);
        //1
        krayZBloxSprite = new Sprite(unchecked);
        krayZBloxButton = new Button(new SpriteDrawable(krayZBloxSprite));
        krayZBloxButton.setY(663);
        krayZBloxButton.setX(710);
        //2
        escapeTheMatrixSprite = new Sprite(unchecked);
        escapeTheMatrixButton = new Button(new SpriteDrawable(escapeTheMatrixSprite));
        escapeTheMatrixButton.setY(663);
        escapeTheMatrixButton.setX(1010);
        //3
        sprintSprite = new Sprite(unchecked);
        sprintButton = new Button(new SpriteDrawable(sprintSprite));
        sprintButton.setY(436);
        sprintButton.setX(400);
        //4
        marathonSprite = new Sprite(unchecked);
        marathonButton = new Button(new SpriteDrawable(marathonSprite));
        marathonButton.setY(436);
        marathonButton.setX(710);
        //5
        timeSprite = new Sprite(unchecked);
        timeButton = new Button(new SpriteDrawable(timeSprite));
        timeButton.setY(436);
        timeButton.setX(1010);
        //6
        singleplayerSprite = new Sprite(unchecked);
        singleplayerButton = new Button(new SpriteDrawable(singleplayerSprite));
        singleplayerButton.setY(212);
        singleplayerButton.setX(400);
        //7
        createMultiplayerSprite = new Sprite(unchecked);
        createMultiplayerButton = new Button(new SpriteDrawable(createMultiplayerSprite));
        createMultiplayerButton.setY(212);
        createMultiplayerButton.setX(710);
        //8
        joinMultiplayerSprite = new Sprite(unchecked);
        joinMultiplayerButton = new Button(new SpriteDrawable(joinMultiplayerSprite));
        joinMultiplayerButton.setY(212);
        joinMultiplayerButton.setX(1010);


        vp = new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT);

        stage = new Stage(vp, t.batch);
        stage.addActor(backButton);
        stage.addActor(infoButton);
        stage.addActor(forwardButton);

        stage.addActor(classicButton);
        stage.addActor(krayZBloxButton);
        stage.addActor(escapeTheMatrixButton);
        stage.addActor(sprintButton);
        stage.addActor(marathonButton);
        stage.addActor(timeButton);
        stage.addActor(singleplayerButton);
        stage.addActor(createMultiplayerButton);
        stage.addActor(joinMultiplayerButton);


        classicButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                SwapImageClick.checked(t, classicSprite, 0, krayZBloxSprite, escapeTheMatrixSprite);
            }
        });
        krayZBloxButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                SwapImageClick.checked(t, krayZBloxSprite, 1, classicSprite, escapeTheMatrixSprite);
            }
        });
        escapeTheMatrixButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                SwapImageClick.checked(t, escapeTheMatrixSprite, 2, classicSprite, krayZBloxSprite);
            }
        });
        sprintButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                SwapImageClick.checked(t, sprintSprite, 3, marathonSprite, timeSprite);
            }
        });
        marathonButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                SwapImageClick.checked(t, marathonSprite, 4, sprintSprite, timeSprite);
            }
        });
        timeButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                SwapImageClick.checked(t, timeSprite, 5, sprintSprite, marathonSprite);
            }
        });
        singleplayerButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                SwapImageClick.checked(t, singleplayerSprite, 6, createMultiplayerSprite, joinMultiplayerSprite);
            }
        });
        createMultiplayerButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                SwapImageClick.checked(t, createMultiplayerSprite, 7, singleplayerSprite, joinMultiplayerSprite);
            }
        });
        joinMultiplayerButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                SwapImageClick.checked(t, joinMultiplayerSprite, 8, singleplayerSprite, createMultiplayerSprite);
            }
        });

        forwardButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                SwapImageClick.validGame(t, escapeTheMatrixSprite);
            }
        });

        Gdx.input.setInputProcessor(stage);
    }


    @Override
    public void dispose() {
    }

    /**
     * Draws all the necessary parts on the screen, them being the background, the game mode layout and all the objects
     * added to the screen's stage
     * information sprite
     * Checks if the back button or the info button have been pressed
     * @param delta
     */
    @Override
    public void render(float delta) {

        t.batch.begin();
        t.batch.draw(background, 0, 0);
        text.draw(t.batch);
        t.batch.end();
        stage.draw();

        if(backButton.isPressed()) {
            this.dispose();
            t.setScreen(new MainMenuScreen(t));
        }
        if(infoButton.isPressed()){
            this.dispose();
            t.setScreen(new GameSetupInfoScreen(t));
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
