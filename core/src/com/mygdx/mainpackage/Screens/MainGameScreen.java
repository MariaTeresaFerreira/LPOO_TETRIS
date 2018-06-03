package com.mygdx.mainpackage.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.mainpackage.*;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.HashMap;


public class MainGameScreen implements Screen{

    /**
     * Main game screen's stage
     */
    protected Stage stage;
    /**
     * Main screen Tetris object
     */
    protected Tetris t;
    /**
     * Background texture
     */
    protected Texture background= new Texture("background2.png");
    /**
     * Hold and next tetromino's box texture
     */
    protected Texture holdNextBox = new Texture("HoldNextBox.png");
    /**
     * Hold text texture
     */
    protected Texture holdText = new Texture("hold.png");
    /**
     * Next text texture
     */
    protected Texture nextText = new Texture("next.png");
    /**
     * Music mute sprite
     */
    protected Sprite muteSprite = new Sprite (new Texture("sound.png"));

    /**
     * Dark blue block texture
     */
    Texture darkBlue = new Texture("darkBlueBlock.png");
    /**
     * Light blue block texture
     */
    Texture lightBlue = new Texture("lightBlueBlock.png");
    /**
     * Purple block texture
     */
    Texture purple = new Texture("purpleBlock.png");
    /**
     * Orange block texture
     */
    Texture orange = new Texture("orangeBlock.png");
    /**
     * REd block texture
     */
    Texture red = new Texture("redBlock.png");
    /**
     * Green block texture
     */
    Texture green = new Texture("greenBlock.png");
    /**
     * Yellow block texture
     */
    Texture yellow = new Texture("yellowBlock.png");
    /**
     * Kray-Z block question mark (?) texture
     */
    Texture krayZ = new Texture("krayZBlox.png");
    /**
     * Gray wall block texture
     */
    Texture wall = new Texture("wallBlock.png");

    /**
     * Hash map to store and index the colour block textures by strings
     */
    protected HashMap<String, Sprite> spriteMap = new HashMap<String, Sprite>();

    /**
     * Relative to screen size side block size (resizable blocks)
     */
    protected static final float blockSize = (float) (Gdx.graphics.getHeight() * (0.056));
    /**
     * X displacement for drawing the blocks inside the game's playing field
     */
    protected static final float displacementX = 10*blockSize;
    /**
     * Y displacement for drawing the blocks inside the game's playing field
     */
    protected static final float displacementY = (float) (Gdx.graphics.getHeight() - 3*blockSize);

    /**
     * Variable to handle the time during the games
     */
    protected float time;

    /**
     * Music mute button
     */
    protected Button muteButton = new Button(new SpriteDrawable(muteSprite));

    /**
     * Main game screen constructor
     * Hash map is filled with the necessary information regarding the blocks and their colours
     * Mute button is handled using a listener and an overridden clicked function
     * @param t
     */
    public MainGameScreen(final Tetris t){

        this.t = t;
        Sprite lb = new Sprite(lightBlue); //Light Blue (I)
        Sprite p = new Sprite(purple); //Purple (T)
        Sprite r = new Sprite(red);//Red (Z)
        Sprite g = new Sprite(green); //Green (S)
        Sprite y = new Sprite(yellow); //Yellow (O)
        Sprite db = new Sprite(darkBlue); //Dark Blue (J)
        Sprite o = new Sprite(orange); //Orange (L)
        Sprite k = new Sprite(krayZ);
        Sprite w = new Sprite(wall);
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
                System.out.println("AAA");
            }
        });
        stage.addActor(muteButton);
        Gdx.input.setInputProcessor(stage);

    }

    /**
     * This function receives a block and from it's internal coordinates, generates de coordinates for the game's playing
     * field
     * @param b
     * @return Coords (regarding the game's playing field instead of the generic coordinates)
     */
    public Coords genScreenCoords(Block b){
        Coords c = new Coords(displacementX + b.getCoords().X() * blockSize,
                displacementY - b.getCoords().Y()*blockSize + blockSize);
        return c;
    }

    /**
     * Receives a block and checks if it's coordinates are valid regarding the limits of the game's playing field
     * @param b
     * @return true if the coordinates are valid, false otherwise
     */
    public boolean validScreenCoordsBlock(Block b){

        Coords c = genScreenCoords(b);

        if(c.X() >= displacementX && c.X() <= displacementX + 10 * blockSize){
            if (c.Y() <= displacementY + blockSize && c.Y() >=  displacementY - 14 * blockSize);{
                return true;
            }
        }

        return false;
    }

    /**
     * This function handles each block present in the placed linked list and draws them after checking their
     * coordinate's validity
     */
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


    /**
     * This function draws the tetromino passed as tetrom if all the coordinates are valid
     * @param tetrom
     */
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

    /**
     * This function draws the limits os the game's playing field
     */
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


    /**
     * This function draws both the next and hold boxes and text textures
     * The function that draws the hold and next tetrominos is called
     */
    public void drawHoldAndNext(){

        t.batch.begin();
        t.batch.draw(holdNextBox, 150, 430, blockSize * 4, blockSize * 4);
        t.batch.draw(holdNextBox, 150, 130, blockSize * 4, blockSize * 4);
        t.batch.draw(holdText, 150, 600, blockSize * 4, blockSize * 2);
        t.batch.draw(nextText, 150, 300, blockSize * 4, blockSize * 2);
        t.batch.end();

        drawNHTetrominos(t.g.getNextTetromino(), true);

        if(t.g.getHold() != null){
            drawNHTetrominos(t.g.getHold(), false);
        }
    }

    /**
     * This function draws the hold and next tetrominos in their specific places
     * @param tetro
     * @param isNext
     */
    public void drawNHTetrominos(Tetromino tetro, boolean isNext){

        Coords holdCoords = new Coords(175, 480);
        Coords nextCoords = new Coords(175, 170);

        int blockSide = 40;

        t.batch.begin();


        switch(tetro.getShape().charAt(0)){

            case 'I':
                if(isNext){

                    for(int i = 0; i < 4; i++){
                        t.batch.draw(lightBlue, nextCoords.X() + i * 35 - 10, nextCoords.Y() + 20, 35, 35);
                    }
                }else {
                    for(int i = 0; i < 4; i++){
                        t.batch.draw(lightBlue, holdCoords.X() + i * 35 - 10, holdCoords.Y() + 20, 35, 35);
                    }
                }
                break;

            case 'T':
                if(isNext){
                    t.batch.draw(purple, nextCoords.X(), nextCoords.Y(), blockSide, blockSide);
                    t.batch.draw(purple, nextCoords.X() + blockSide, nextCoords.Y(), blockSide, blockSide);
                    t.batch.draw(purple, nextCoords.X() + 2 * blockSide, nextCoords.Y(), blockSide, blockSide);
                    t.batch.draw(purple, nextCoords.X() + blockSide, nextCoords.Y() + blockSide, blockSide, blockSide);

                } else {
                    t.batch.draw(purple, holdCoords.X(), holdCoords.Y(), blockSide, blockSide);
                    t.batch.draw(purple, holdCoords.X() + blockSide, holdCoords.Y(), blockSide, blockSide);
                    t.batch.draw(purple, holdCoords.X() + 2 * blockSide, holdCoords.Y(), blockSide, blockSide);
                    t.batch.draw(purple, holdCoords.X() + blockSide, holdCoords.Y() + blockSide, blockSide, blockSide);
                }
                break;

            case 'S':

                if(isNext){
                    t.batch.draw(green, nextCoords.X(), nextCoords.Y(), blockSide, blockSide);
                    t.batch.draw(green, nextCoords.X() + blockSide, nextCoords.Y(), blockSide, blockSide);
                    t.batch.draw(green, nextCoords.X() + blockSide, nextCoords.Y() + blockSide, blockSide, blockSide);
                    t.batch.draw(green, nextCoords.X() + 2 * blockSide, nextCoords.Y() + blockSide, blockSide, blockSide);

                } else {
                    t.batch.draw(green, holdCoords.X(), holdCoords.Y(), blockSide, blockSide);
                    t.batch.draw(green, holdCoords.X() + blockSide, holdCoords.Y(), blockSide, blockSide);
                    t.batch.draw(green, holdCoords.X() + blockSide, holdCoords.Y() + blockSide, blockSide, blockSide);
                    t.batch.draw(green, holdCoords.X() + 2 * blockSide, holdCoords.Y() + blockSide, blockSide, blockSide);

                }


                break;
            case 'Z':

                if(isNext){
                    t.batch.draw(red, nextCoords.X(), nextCoords.Y() + blockSide, blockSide, blockSide);
                    t.batch.draw(red, nextCoords.X() + blockSide, nextCoords.Y() + blockSide, blockSide, blockSide);
                    t.batch.draw(red, nextCoords.X() + blockSide, nextCoords.Y(), blockSide, blockSide);
                    t.batch.draw(red, nextCoords.X() + 2 * blockSide, nextCoords.Y(), blockSide, blockSide);

                } else {
                    t.batch.draw(red, holdCoords.X(), holdCoords.Y() + blockSide, blockSide, blockSide);
                    t.batch.draw(red, holdCoords.X() + blockSide, holdCoords.Y() + blockSide, blockSide, blockSide);
                    t.batch.draw(red, holdCoords.X() + blockSide, holdCoords.Y(), blockSide, blockSide);
                    t.batch.draw(red, holdCoords.X() + 2 * blockSide, holdCoords.Y(), blockSide, blockSide);
                }
                break;

            case 'L':

                if(isNext){
                    t.batch.draw(orange, nextCoords.X(), nextCoords.Y(), blockSide, blockSide);
                    t.batch.draw(orange, nextCoords.X() + blockSide, nextCoords.Y(), blockSide, blockSide);
                    t.batch.draw(orange, nextCoords.X() + 2 * blockSide, nextCoords.Y(), blockSide, blockSide);
                    t.batch.draw(orange, nextCoords.X() + 2 * blockSide, nextCoords.Y() + blockSide, blockSide, blockSide);
                } else {
                    t.batch.draw(orange, holdCoords.X(), holdCoords.Y(), blockSide, blockSide);
                    t.batch.draw(orange, holdCoords.X() + blockSide, holdCoords.Y(), blockSide, blockSide);
                    t.batch.draw(orange, holdCoords.X() + 2 * blockSide, holdCoords.Y(), blockSide, blockSide);
                    t.batch.draw(orange, holdCoords.X() + 2 * blockSide, holdCoords.Y() + blockSide, blockSide, blockSide);
                }
                break;

            case 'J':

                if(isNext){
                    t.batch.draw(darkBlue, nextCoords.X(), nextCoords.Y(), blockSide, blockSide);
                    t.batch.draw(darkBlue, nextCoords.X(), nextCoords.Y() + blockSide, blockSide, blockSide);
                    t.batch.draw(darkBlue, nextCoords.X() + blockSide, nextCoords.Y(), blockSide, blockSide);
                    t.batch.draw(darkBlue, nextCoords.X() + 2 * blockSide, nextCoords.Y(), blockSide, blockSide);
                } else {
                    t.batch.draw(darkBlue, holdCoords.X(), holdCoords.Y(), blockSide, blockSide);
                    t.batch.draw(darkBlue, holdCoords.X(), holdCoords.Y() + blockSide, blockSide, blockSide);
                    t.batch.draw(darkBlue, holdCoords.X() + blockSide, holdCoords.Y(), blockSide, blockSide);
                    t.batch.draw(darkBlue, holdCoords.X() + 2 * blockSide, holdCoords.Y(), blockSide, blockSide);
                }
                break;

            case 'O':

                if(isNext){
                    t.batch.draw(yellow, 20 + nextCoords.X(), nextCoords.Y(), blockSide, blockSide);
                    t.batch.draw(yellow, 20 +  nextCoords.X() + blockSide, nextCoords.Y(), blockSide, blockSide);
                    t.batch.draw(yellow, 20 +  nextCoords.X(), nextCoords.Y() + blockSide, blockSide, blockSide);
                    t.batch.draw(yellow, 20 +  nextCoords.X() + blockSide, nextCoords.Y() + blockSide, blockSide, blockSide);
                } else {
                    t.batch.draw(yellow, 20 + holdCoords.X(), holdCoords.Y(), blockSide, blockSide);
                    t.batch.draw(yellow, 20 +  holdCoords.X() + blockSide, holdCoords.Y(), blockSide, blockSide);
                    t.batch.draw(yellow, 20 +  holdCoords.X(), holdCoords.Y() + blockSide, blockSide, blockSide);
                    t.batch.draw(yellow, 20 +  holdCoords.X() + blockSide, holdCoords.Y() + blockSide, blockSide, blockSide);

                }



                break;
            default:
                break;

        }

        t.batch.end();
    }


    @Override
    public void dispose() {

    }

    /**
     * Draws all the necessary parts on the screen, them being the background, the game's borders and tetrominos
     * Handles the times of the game, considering the different game modes
     * @param delta
     */
    @Override
    public void render(float delta) {
        //TODO: FIX TETROMINOES DRAWING OUTSIDE PLAYING FIELD
        time += Gdx.graphics.getDeltaTime();
        t.tToIncreaseSpeed += Gdx.graphics.getDeltaTime();

        if (t.time){
            t.g.decTimer(Gdx.graphics.getDeltaTime());
        }

        if (t.sprint){
            t.g.incTimer(Gdx.graphics.getDeltaTime());
        }

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
        t.g.clearLinesScore();
        if (t.g.getToSlide()){
            t.g.shiftPlacedLeft();
        }
        drawTetromino(t.g.getCurr());
        drawPlayingField();
        drawPlaced();
        drawHoldAndNext();
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

    /**
     * This function updates the screen size, keeping the proportions
     * @param width
     * @param height
     */
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

}
