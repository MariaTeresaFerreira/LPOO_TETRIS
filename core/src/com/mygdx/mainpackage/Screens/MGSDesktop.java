package com.mygdx.mainpackage.Screens;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.mainpackage.Tetris;


public class MGSDesktop extends MainGameScreen {

    public MGSDesktop(final Tetris t){
        super(t);
        stage.addListener( new InputListener(){
            @Override
            public boolean keyDown(InputEvent event, int keycode) {

                if (keycode == Input.Keys.UP || keycode == Input.Keys.W){
                    t.g.getCurr().rotate();
                }

                if (keycode == Input.Keys.DOWN || keycode == Input.Keys.S){
                    t.g.drop();
                }

                if (keycode == Input.Keys.LEFT || keycode == Input.Keys.A){
                    t.g.shiftLeft();
                }

                if (keycode == Input.Keys.RIGHT || keycode == Input.Keys.D){
                    t.g.shiftRight();
                }

                if (keycode == Input.Keys.SPACE){
                    t.g.hardDrop();
                }


                return super.keyDown(event, keycode);
            }
        });
    }
}
