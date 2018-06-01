package com.mygdx.mainpackage.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.mainpackage.*;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;


public class MainGameScreen implements Screen{

    protected Stage stage;
    protected Tetris t;
    protected Texture background= new Texture("background2.png");
    protected Texture boxes = new Texture("HoldNextBox.png");
    //TODO: DESENHAR HOLD
    protected Sprite muteSprite = new Sprite (new Texture("sound.png"));
    protected HashMap<String, Sprite> spriteMap = new HashMap<String, Sprite>();

    protected static final float blockSize = (float) (Gdx.graphics.getHeight() * (0.056));
    protected static final float displacementX = 10*blockSize;
    protected static final float displacementY = (float) (Gdx.graphics.getHeight() - 3*blockSize);

    protected float time;

    protected Button muteButton = new Button(new SpriteDrawable(muteSprite));

    public MainGameScreen(final Tetris t){

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

        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()), t.batch);

        muteButton.setX(Gdx.graphics.getWidth() - muteSprite.getWidth());
        muteButton.setY(Gdx.graphics.getHeight() - muteSprite.getRegionHeight());
        muteButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                SwapImageClick.mute(t, muteSprite);
            }
        });

        stage.addActor(muteButton);
        Gdx.input.setInputProcessor(stage);


    }

    public Coords genScreenCoords(Block b){
        Coords c = new Coords(displacementX + b.getCoords().X() * blockSize,
                displacementY - b.getCoords().Y()*blockSize + blockSize);
        return c;
    }

    public boolean validScreenCoordsBlock(Block b){

        Coords c = genScreenCoords(b);

        if(c.X() >= displacementX && c.X() <= displacementX + 10 * blockSize){
            if (c.Y() <= displacementY + blockSize && c.Y() >=  displacementY - 14 * blockSize);{
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

            t.batch.draw(spriteMap.get("W"), displacementX + i * blockSize, displacementY - 14 * blockSize, blockSize, blockSize); //linha de baixo
            t.batch.draw(spriteMap.get("W"), displacementX + i * blockSize, displacementY + blockSize, blockSize, blockSize); //linha de cima

        }

        for (j = -1; j < 14; j++) {
            t.batch.draw(spriteMap.get("W"), displacementX - blockSize, displacementY - j * blockSize, blockSize, blockSize); // esquerda
            t.batch.draw(spriteMap.get("W"), displacementX + 10 * blockSize, displacementY  - j * blockSize ,blockSize, blockSize); // direita
        }

        t.batch.end();
    }


    public int findLine(){


        for(int i = 0; i < 15; i++){

            if(t.g.getLines()[i] == 10){
                return i;
            }
        }
        return -1;
    }

    public int deleteLine(){

        LinkedList <Block> newBlockList = new LinkedList<Block>();

        int line = findLine();

        if(line == -1){
            return -1;
        }

        for(Block b: t.g.getPlaced()){
            if(b.getCoords().Y() > line){
                newBlockList.add(b);
            }else if (b.getCoords().Y() < line){
                Block c = new Block (new Coords(b.getCoords().X(), b.getCoords().Y() + 1), b.getColour(), b.getPower());
                newBlockList.add(c);
            } else {
                t.g.activatePower(b.getPower());
            }
        }

        t.g.setPlaced(newBlockList);


        int[] newList = new int[15];


        for(int i = 0; i <= line; i++){
            if (i == 0){
                newList[0] = 0;
            } else {
                newList[i] = t.g.getLines()[i - 1];
            }
        }
        for (int j = line + 1; j < 15; j ++){
            newList[j] = t.g.getLines()[j];
        }

        t.g.setLines(newList);

        return 0;

    }

    @Override
    public void dispose() {

    }

    @Override
    public void render(float delta) {
        //TODO: FIX TETROMINOES DRAWING OUTSIDE PLAYING FIELD
        time += Gdx.graphics.getDeltaTime();
        t.tToIncreaseSpeed += Gdx.graphics.getDeltaTime();
        if (t.g.effectTimer > 0) {
            t.g.effectTimer -= Gdx.graphics.getDeltaTime();
        } else {
            t.g.effectTimer = 0f;
            t.g.clearEffect();

        }
        if (t.g.getActiveEffect() != 'U' && t.g.getActiveEffect() != 'D') {
            if (time >= t.speed) {
                t.g.drop();
                time = 0;
            }
        }else if (t.g.getActiveEffect() == 'U') {
            if (time >= 0.25){
                t.g.drop();
                time = 0;
            }
        } else if (t.g.getActiveEffect() == 'D'){
            if (time >= 2){
                t.g.drop();
                time = 0;
            }
        }

        if(t.tToIncreaseSpeed >= 20){
            t.tToIncreaseSpeed = new Float(0);
            if (t.speed > 0.3) t.speed -= (float) 0.1;
        }

        t.batch.begin();
        t.batch.draw(background, 0, 0);
        t.batch.end();
        stage.draw();
        deleteLine();
        drawTetromino(t.g.getCurr());
        drawPlayingField();
        drawPlaced();
        if (t.g.defeat()){
            this.dispose();
            t.setScreen(new GameOverScreen(t));
        }
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
        stage.getViewport().update(width, height);
    }

}
