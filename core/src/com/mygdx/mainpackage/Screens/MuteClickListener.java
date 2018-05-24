package com.mygdx.mainpackage.Screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.mainpackage.Tetris;

public class MuteClickListener extends ClickListener{
    Tetris t;

    public MuteClickListener(Tetris t){
        this.t = t;
    }

    public void clicked(InputEvent e, float x, float y){

    }
}
