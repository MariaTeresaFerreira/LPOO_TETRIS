package com.mygdx.mainpackage.Screens;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.mainpackage.GameState;
import com.mygdx.mainpackage.Tetris;

public class SwapImageClick {

    public static void mute(Tetris t, Sprite s){
        if (t.sound == true){
            t.sound = false;
            s.set(new Sprite(new Texture("mute.png")));
            t.music.stop();
        }else {
            t.sound = true;
            s.set(new Sprite(new Texture("sound.png")));
            t.music.play();
        }
    }

    public static void checked(Tetris t, Sprite s, int box, Sprite s1, Sprite s2){

        switch (box){
            case 0:
                clickBox(t, s, box);
                if(t.krayZBlox){
                    clickBox(t, s1, 1);
                }
                if(t.escapeTheMatrix){
                    clickBox(t, s2, 2);
                }
                break;

            case 1:
                clickBox(t, s, box);
                if(t.classic){
                    clickBox(t, s1, 0);
                }
                if(t.escapeTheMatrix){
                    clickBox(t, s2, 2);
                }
                break;

            case 2:
                clickBox(t, s, box);
                if(t.classic){
                    clickBox(t, s1, 0);
                }
                if(t.krayZBlox){
                    clickBox(t, s2, 1);
                }
                break;

            case 3:
                clickBox(t, s, box);
                if(t.marathon){
                    clickBox(t, s1, 4);
                }
                if(t.time){
                    clickBox(t, s2, 5);
                }
                break;

            case 4:
                clickBox(t, s, box);
                if(t.sprint){
                    clickBox(t, s1, 3);
                }
                if(t.time){
                    clickBox(t, s2, 5);
                }
                break;

            case 5:
                clickBox(t, s, box);
                if(t.sprint){
                    clickBox(t, s1, 3);
                }
                if(t.marathon){
                    clickBox(t, s2, 4);
                }
                break;

            case 6:
                clickBox(t, s, box);
                if(t.createMultiplayer){
                    clickBox(t, s1, 7);
                }
                if(t.joinMultiplayer){
                    clickBox(t, s2, 8);
                }
                break;

            case 7:
                clickBox(t, s, box);
                if(t.singlePlayer){
                    clickBox(t, s1, 6);
                }
                if(t.joinMultiplayer){
                    clickBox(t, s2, 8);
                }
                break;

            case 8:
                clickBox(t, s, box);
                if(t.singlePlayer){
                    clickBox(t, s1, 6);
                }
                if(t.createMultiplayer){
                    clickBox(t, s2, 7);
                }
                break;

            default:
                break;
        }

    }

    private static void clickBox(Tetris t, Sprite s, int box){
        switch(box){
            case 0:
                if(t.classic){
                    t.classic = false;
                    s.set(new Sprite(new Texture("unchecked.png")));
                }else {
                    t.classic = true;
                    s.set(new Sprite(new Texture("checked.png")));
                }
                break;

            case 1:
                if(t.krayZBlox){
                    t.krayZBlox = false;
                    s.set(new Sprite(new Texture("unchecked.png")));
                }else {
                    t.krayZBlox = true;
                    s.set(new Sprite(new Texture("checked.png")));
                }
                break;

            case 2:
                if(t.escapeTheMatrix){
                    t.escapeTheMatrix = false;
                    s.set(new Sprite(new Texture("unchecked.png")));
                }else {
                    t.escapeTheMatrix = true;
                    s.set(new Sprite(new Texture("checked.png")));
                }
                break;

            case 3:
                if(t.sprint){
                    t.sprint = false;
                    s.set(new Sprite(new Texture("unchecked.png")));
                }else {
                    t.sprint = true;
                    s.set(new Sprite(new Texture("checked.png")));
                }
                break;

            case 4:
                if(t.marathon){
                    t.marathon = false;
                    s.set(new Sprite(new Texture("unchecked.png")));
                }else {
                    t.marathon = true;
                    s.set(new Sprite(new Texture("checked.png")));
                }
                break;

            case 5:
                if(t.time){
                    t.time = false;
                    s.set(new Sprite(new Texture("unchecked.png")));
                }else {
                    t.time = true;
                    s.set(new Sprite(new Texture("checked.png")));
                }
                break;

            case 6:
                if(t.singlePlayer){
                    t.singlePlayer = false;
                    s.set(new Sprite(new Texture("unchecked.png")));
                }else {
                    t.singlePlayer = true;
                    s.set(new Sprite(new Texture("checked.png")));
                }
                break;

            case 7:
                if(t.createMultiplayer){
                    t.createMultiplayer = false;
                    s.set(new Sprite(new Texture("unchecked.png")));
                }else {
                    t.createMultiplayer = true;
                    s.set(new Sprite(new Texture("checked.png")));
                }
                break;

            case 8:
                if(t.joinMultiplayer){
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

    public static void validGame(Tetris t, Sprite etmSprite){

        boolean valid = false;

        //TODO: otimizar para quando se carrega para avançar so com o escape the matrix nao apagar tudo
        if((t.classic || t.krayZBlox) && (t.sprint || t.marathon || t.time) && (t.singlePlayer || t.createMultiplayer || t.joinMultiplayer)){
            valid = true;
        }else if(t.escapeTheMatrix){
            if(t.time && t.singlePlayer) {
                valid = true;
            }else {
                clickBox(t, etmSprite, 2);
            }

        }


        if(valid){
            t.g = new GameState();
            t.speed = 1f;

            if (t.krayZBlox) t.g.setMode('K');
            if (t.escapeTheMatrix) t.g.setMode('E');

            if (t.sprint) t.g.setEnd('S');
            if (t.time) t.g.setEnd('T');

            if(!t.mobile) {
                t.setScreen(new MGSDesktop(t));
            }

            else { t.setScreen(new MGSAndroid(t));
            }
        }
    }

}
