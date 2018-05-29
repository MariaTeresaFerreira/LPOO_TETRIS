package com.mygdx.mainpackage.Screens;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.mainpackage.Tetris;

public class Mute {

    public static void click(Tetris t, Sprite s){
        if (t.sound == true){
            t.sound = false;
            s.set(new Sprite(new Texture("mute.png")));
        }else {
            t.sound = true;
            s.set(new Sprite(new Texture("sound.png")));
        }
    }
}
