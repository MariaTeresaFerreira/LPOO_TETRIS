package com.mygdx.mainpackage.Screens;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.mainpackage.Tetris;

public class SwapImageClick {

    public static void mute(Tetris t, Sprite s){
        if (t.sound == true){
            t.sound = false;
            s.set(new Sprite(new Texture("mute.png")));
        }else {
            t.sound = true;
            s.set(new Sprite(new Texture("sound.png")));
        }
    }

    public static void checked(Tetris t, Sprite s, int box, Sprite s1, Sprite s2){

        switch (box){
            case 0:
                clickBox(t, s, box);
                if(t.krayZBlox == true){
                    clickBox(t, s1, 1);
                }
                if(t.escapeTheMatrix == true){
                    clickBox(t, s2, 2);
                }
                break;

            case 1:
                clickBox(t, s, box);
                if(t.classic == true){
                    clickBox(t, s1, 0);
                }
                if(t.escapeTheMatrix == true){
                    clickBox(t, s2, 2);
                }
                break;

            case 2:
                clickBox(t, s, box);
                if(t.classic == true){
                    clickBox(t, s1, 0);
                }
                if(t.krayZBlox == true){
                    clickBox(t, s2, 1);
                }
                break;

            case 3:
                clickBox(t, s, box);
                if(t.marathon == true){
                    clickBox(t, s1, 4);
                }
                if(t.time == true){
                    clickBox(t, s2, 5);
                }
                break;

            case 4:
                clickBox(t, s, box);
                if(t.sprint == true){
                    clickBox(t, s1, 3);
                }
                if(t.time == true){
                    clickBox(t, s2, 5);
                }
                break;

            case 5:
                clickBox(t, s, box);
                if(t.sprint == true){
                    clickBox(t, s1, 3);
                }
                if(t.marathon == true){
                    clickBox(t, s2, 4);
                }
                break;

            case 6:
                clickBox(t, s, box);
                if(t.createMultiplayer == true){
                    clickBox(t, s1, 7);
                }
                if(t.joinMultiplayer == true){
                    clickBox(t, s2, 8);
                }
                break;

            case 7:
                clickBox(t, s, box);
                if(t.singlePlayer == true){
                    clickBox(t, s1, 6);
                }
                if(t.joinMultiplayer == true){
                    clickBox(t, s2, 8);
                }
                break;

            case 8:
                clickBox(t, s, box);
                if(t.singlePlayer == true){
                    clickBox(t, s1, 6);
                }
                if(t.createMultiplayer == true){
                    clickBox(t, s2, 7);
                }
                break;

            default:
                break;
        }



    }

    public static void clickBox(Tetris t, Sprite s, int box){
        switch(box){
            case 0:
                if(t.classic == true){
                    t.classic = false;
                    s.set(new Sprite(new Texture("unchecked.png")));
                }else {
                    t.classic = true;
                    s.set(new Sprite(new Texture("checked.png")));
                }
                break;

            case 1:
                if(t.krayZBlox == true){
                    t.krayZBlox = false;
                    s.set(new Sprite(new Texture("unchecked.png")));
                }else {
                    t.krayZBlox = true;
                    s.set(new Sprite(new Texture("checked.png")));
                }
                break;

            case 2:
                if(t.escapeTheMatrix == true){
                    t.escapeTheMatrix = false;
                    s.set(new Sprite(new Texture("unchecked.png")));
                }else {
                    t.escapeTheMatrix = true;
                    s.set(new Sprite(new Texture("checked.png")));
                }
                break;

            case 3:
                if(t.sprint == true){
                    t.sprint = false;
                    s.set(new Sprite(new Texture("unchecked.png")));
                }else {
                    t.sprint = true;
                    s.set(new Sprite(new Texture("checked.png")));
                }
                break;

            case 4:
                if(t.marathon == true){
                    t.marathon = false;
                    s.set(new Sprite(new Texture("unchecked.png")));
                }else {
                    t.marathon = true;
                    s.set(new Sprite(new Texture("checked.png")));
                }
                break;

            case 5:
                if(t.time == true){
                    t.time = false;
                    s.set(new Sprite(new Texture("unchecked.png")));
                }else {
                    t.time = true;
                    s.set(new Sprite(new Texture("checked.png")));
                }
                break;

            case 6:
                if(t.singlePlayer == true){
                    t.singlePlayer = false;
                    s.set(new Sprite(new Texture("unchecked.png")));
                }else {
                    t.singlePlayer = true;
                    s.set(new Sprite(new Texture("checked.png")));
                }
                break;

            case 7:
                if(t.createMultiplayer == true){
                    t.createMultiplayer = false;
                    s.set(new Sprite(new Texture("unchecked.png")));
                }else {
                    t.createMultiplayer = true;
                    s.set(new Sprite(new Texture("checked.png")));
                }
                break;

            case 8:
                if(t.joinMultiplayer == true){
                    t.joinMultiplayer = false;
                    s.set(new Sprite(new Texture("unchecked.png")));
                }else {
                    t.joinMultiplayer = true;
                    s.set(new Sprite(new Texture("checked.png")));
                }
                break;

            default:
                break;

        }
    }

}
