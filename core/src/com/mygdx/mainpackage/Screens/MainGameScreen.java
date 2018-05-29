package com.mygdx.mainpackage.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Screen;
import com.mygdx.mainpackage.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import java.awt.TextComponent;
import java.util.HashMap;
import java.util.LinkedList;

import javax.xml.soap.Text;

public class MainGameScreen implements Screen{

    private Stage stage;
    private Tetris t;
    private Texture background= new Texture("background2.png");
    private HashMap<String, Sprite> spriteMap = new HashMap<String, Sprite>();
    private static final float blockSize = (float) (Gdx.graphics.getHeight() * (0.056));
    private static final float displacementX = 10*blockSize;
    private static final float displacementY = (float) (Gdx.graphics.getHeight() - 3*blockSize);
    private float time;

    public MainGameScreen(Tetris t){

        this.t = t;
        Sprite lb = new Sprite(new Texture("lightBlueBlock.png")); //Light Blue (I)
        Sprite p = new Sprite(new Texture("purpleBlock.png")); //Purple (T)
        Sprite r = new Sprite(new Texture("redBlock.png"));//Red (Z)
        Sprite g = new Sprite(new Texture("greenBlock.png")); //Green (S)
        Sprite y = new Sprite(new Texture("yellowBlock.png")); //Yellow (O)
        Sprite db = new Sprite(new Texture("darkBlueBlock.png")); //Dark Blue (J)
        Sprite o = new Sprite(new Texture("orangeBlock.png")); //Orange (L)
        Sprite k = new Sprite(new Texture("krayZBlox.png"));
        Sprite w = new Sprite(new Texture("wallBlock.png"));
        time = 0;

        spriteMap.put("I", lb);
        spriteMap.put("T", p);
        spriteMap.put("Z", r);
        spriteMap.put("S", g);
        spriteMap.put("O", y);
        spriteMap.put("J", db);
        spriteMap.put("L", o);
        spriteMap.put("KRAYZ", k);
        spriteMap.put("W", w);

    }

    public Coords genScreenCoords(Block b){
        Coords c = new Coords(displacementX + b.getCoords().X() * blockSize,
                displacementY - b.getCoords().Y()*blockSize + blockSize);
        return c;
    }

    public boolean validScreenCoordsBlock(Block b){

        Coords c = genScreenCoords(b);

        if (c.X() >= displacementX && c.X() <= Gdx.graphics.getWidth() - displacementX){
            if (c.Y() <= displacementY + blockSize && c.Y() >= (Gdx.graphics.getHeight() - displacementY) - 2*blockSize) {
                return true;
            }
        }

        return false;
    }

    public void drawPlaced(){
        t.batch.begin();
        for(Block b: t.g.getPlaced()){
            Coords c = genScreenCoords(b);
            if (b.getPower() != 'N') {
                t.batch.draw(spriteMap.get("KRAYZ"), c.X(), c.Y(), blockSize, blockSize);
            } else {
                t.batch.draw(spriteMap.get(String.valueOf(b.getColour())), c.X(), c.Y(), blockSize, blockSize);
            }

        }

        t.batch.end();
    }

    public void drawTetromino(Tetromino tetrom){

        Block a = tetrom.getBlocks().get("A");
        Block b = tetrom.getBlocks().get("B");
        Block c = tetrom.getBlocks().get("C");
        Block d = tetrom.getBlocks().get("D");

        Coords ac = genScreenCoords(a);
        Coords bc = genScreenCoords(b);
        Coords cc = genScreenCoords(c);
        Coords dc = genScreenCoords(d);

        t.batch.begin();

        if (validScreenCoordsBlock(a)) {
            if (a.getPower() != 'N') {
                t.batch.draw(spriteMap.get("KRAYZ"), ac.X(), ac.Y(), blockSize, blockSize);
            } else {
                t.batch.draw(spriteMap.get(String.valueOf(tetrom.getShape())), ac.X(), ac.Y(), blockSize, blockSize);
            }
        }

        if (validScreenCoordsBlock(b)) {
            if (b.getPower() != 'N') {
                t.batch.draw(spriteMap.get("KRAYZ"), bc.X(), bc.Y(), blockSize, blockSize);
            } else {
                t.batch.draw(spriteMap.get(String.valueOf(tetrom.getShape())), bc.X(), bc.Y(), blockSize, blockSize);
            }
        }

        if (validScreenCoordsBlock(c)) {
            if (c.getPower() != 'N') {
                t.batch.draw(spriteMap.get("KRAYZ"), cc.X(), cc.Y(), blockSize, blockSize);
            } else {
                t.batch.draw(spriteMap.get(String.valueOf(tetrom.getShape())), cc.X(), cc.Y(), blockSize, blockSize);
            }
        }

        if (validScreenCoordsBlock(d)) {
            if (d.getPower() != 'N') {
                t.batch.draw(spriteMap.get("KRAYZ"), dc.X(), dc.Y(), blockSize, blockSize);
            } else {
                t.batch.draw(spriteMap.get(String.valueOf(tetrom.getShape())), dc.X(), dc.Y(), blockSize, blockSize);
            }
        }
        t.batch.end();

    }

    public void drawPlayingField(){
        int i, j;
        t.batch.begin();

        for (i = -1; i < 11; i++){

            t.batch.draw(spriteMap.get("W"), displacementX + i*blockSize,  Gdx.graphics.getHeight() - displacementY - 2*blockSize, blockSize, blockSize);
            t.batch.draw(spriteMap.get("W"), displacementX + i*blockSize, displacementY + 2*blockSize, blockSize, blockSize);

        }

        for (j = -1; j < 14; j++) {
            t.batch.draw(spriteMap.get("W"), displacementX - blockSize, displacementY - j * blockSize, blockSize, blockSize);
            t.batch.draw(spriteMap.get("W"), Gdx.graphics.getWidth() - displacementX - (float)(1.75* blockSize), displacementY - j * blockSize, blockSize, blockSize);
        }

        t.batch.end();
    }

    @Override
    public void dispose() {

    }

    @Override
    public void render(float delta) {
        time += Gdx.graphics.getDeltaTime();
        if (time >= 1){
            t.g.drop();
            time = 0;
        }
        t.batch.begin();
        t.batch.draw(background, 0, 0);
        t.batch.end();
        drawTetromino(t.g.getCurr());
        drawPlayingField();
        drawPlaced();
        if (t.g.defeat()){
            this.dispose();
            t.setScreen(new GameOverScreen(t));
        }
        //t.batch.end();
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

    @Override
    public void resize(int width, int height) {
        //stage.getViewport().update(width, height);
    }

}
