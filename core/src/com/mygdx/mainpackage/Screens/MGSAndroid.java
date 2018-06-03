package com.mygdx.mainpackage.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.mainpackage.Tetris;
import com.badlogic.gdx.graphics.Texture;

public class MGSAndroid extends MainGameScreen {


    private float timeToMove;
    private float movespeed;
    private Sprite rotateSprite = new Sprite( new Texture("rotateANDROID.png"));
    private Sprite holdButtonSprite = new Sprite(new Texture("HoldANDROID.png"));
    private Sprite hardDropButtonSprite = new Sprite(new Texture("HardDrop.png"));
    private Button holdButton;
    private Button rotateButton;
    private Button hardDropButton;


    /**
     * Main game screen android specifically for android
     * Buttons for rotate (R), hold (H), and hard drop (H)
     * @param t
     */
    public MGSAndroid(final Tetris t){

        super(t);

        timeToMove = 0;
        movespeed = 0.1f;
        rotateButton = new Button(new SpriteDrawable(rotateSprite));
        rotateButton.setBounds((float) (2.5*displacementX), (float) (0.5*displacementY),2*blockSize,2*blockSize);
        rotateButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                t.g.getCurr().rotate(t.g.getPlaced());
            }
        });
        holdButton = new Button(new SpriteDrawable(holdButtonSprite));
        holdButton.setBounds((float) (2.5*displacementX), (float) (0.25*displacementY), 2*blockSize, 2*blockSize);
        holdButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                t.g.hold();
            }
        });
        hardDropButton = new Button(new SpriteDrawable(hardDropButtonSprite));
        hardDropButton.setBounds((float) (2.5*displacementX), (float) (0.75*displacementY), 2*blockSize, 2*blockSize);
        hardDropButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                t.g.hardDrop();
            }
        });

        stage.addActor(hardDropButton);
        stage.addActor(rotateButton);
        stage.addActor(holdButton);

    }
    /**
     * Draws all the necessary parts on the screen
     * Handles the accelerometer input to control the current tetromino
     * @param delta
     */
    @Override
    public void render(float delta){
        timeToMove += Gdx.graphics.getDeltaTime();
        if (timeToMove >= movespeed) {
            if (Gdx.input.getAccelerometerY() >= 4) {
                t.g.shiftRight();
            }

            if (Gdx.input.getAccelerometerY() <= -4) {
                t.g.shiftLeft();
            }

            if (Gdx.input.getAccelerometerX() >= 5) {
                t.g.drop();
            }

            timeToMove = 0;
        }
        stage.act();

        if(t.g.defeat()){
            this.dispose();
            t.setScreen(new GameOverScreen(t));
        }

        super.render(delta);
    }
}
