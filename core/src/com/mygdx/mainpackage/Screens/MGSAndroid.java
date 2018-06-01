package com.mygdx.mainpackage.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.mainpackage.Tetris;

public class MGSAndroid extends MainGameScreen {


    private float timeToMove;
    private float movespeed;

    public MGSAndroid(final Tetris t){

        super(t);
        timeToMove = 0;
        movespeed = 0.25f;
    }

    @Override
    public void render(float delta){
        timeToMove += Gdx.graphics.getDeltaTime();
        if (timeToMove >= movespeed) {
            if (Gdx.input.getAccelerometerY() >= 5) {
                t.g.shiftRight();
            }

            if (Gdx.input.getAccelerometerY() <= -5) {
                t.g.shiftLeft();
            }

            if (Gdx.input.getAccelerometerX() >= 5) {
                t.g.drop();
            }
        }

        super.render(delta);
    }
}
