package com.mygdx.mainpackage.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.mainpackage.Tetris;

public class MGSAndroid extends MainGameScreen {

    public MGSAndroid(final Tetris t){
        super(t);
    }

    @Override
    public void render(float delta){
        if (Gdx.input.getGyroscopeX() >= 0.0872665){
            t.g.shiftLeft();
        }

        if (Gdx.input.getAccelerometerX() <= -0.0872665){
            t.g.shiftRight();
        }

        super.render(delta);
    }
}
