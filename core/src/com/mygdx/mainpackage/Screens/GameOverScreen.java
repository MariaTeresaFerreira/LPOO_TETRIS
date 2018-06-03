package com.mygdx.mainpackage.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.mainpackage.Coords;
import com.mygdx.mainpackage.GameState;
import com.mygdx.mainpackage.Tetris;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class GameOverScreen implements Screen{

    /**
     * Background texture
     */
    private Texture background = new Texture("background2.png");
    /**
     * Game over texture
     */
    private Texture gameOver = new Texture("GameOver.png");

    /**
     * Main menu sprite
     */
    private Sprite mainMenuSprite = new Sprite(new Texture("MainMenuButton.png"));
    /**
     * Exit sprite
     */
    private Sprite exitSprite = new Sprite(new Texture("exitButton.png"));

    /**
     * Main menu button
     */
    private Button mainMenuButton;
    /**
     * Exit button
     */
    private Button exitButton;

    /**
     * Game over screen stage
     */
    private Stage stage;
    /**
     * Game over screen Tetris object
     */
    private Tetris t;

    /**
     * Number zero texture
     */
    private Texture zero = new Texture("numbers/0.png");
    /**
     * Number one texture
     */
    private Texture one = new Texture("numbers/1.png");
    /**
     * Number teo texture
     */
    private Texture two = new Texture("numbers/2.png");
    /**
     * Number three texture
     */
    private Texture three = new Texture("numbers/3.png");
    /**
     * Number four texture
     */
    private Texture four = new Texture("numbers/4.png");
    /**
     * Number five texture
     */
    private Texture five = new Texture("numbers/5.png");
    /**
     * Number six texture
     */
    private Texture six = new Texture("numbers/6.png");
    /**
     * Number seven texture
     */
    private Texture seven = new Texture("numbers/7.png");
    /**
     * Number eight texture
     */
    private Texture eight = new Texture("numbers/8.png");
    /**
     * Number nine texture
     */
    private Texture nine = new Texture("numbers/9.png");

    /**
     * Side size for the number textures of the score
     */
    private int numSide = 200;

    /**
     * Beginning coordinates for drawing both the score numbers and the game over texture
     */
    private Coords numberCoords = new Coords(Gdx.graphics.getWidth()/2-((float)2.5*numSide), Gdx.graphics.getHeight() * (float)0.42);
    /**
     * Array to handle the player's score
     */
    private int[] scoreArray = {0, 0, 0, 0, 0, 0, 0, 0, 0};


    /**
     * GameOverScreen class constructor that handles the existing buttons in this screen (exit and main menu)
     * @param t
     */
    public GameOverScreen(Tetris t){

        this.t = t;


        exitSprite.setSize((float) (Gdx.graphics.getWidth()*0.09375), (float) (Gdx.graphics.getHeight()*0.0834));
        exitButton = new Button(new SpriteDrawable(exitSprite));
        exitButton.setX(Gdx.graphics.getWidth()/2 - exitButton.getWidth()/2);
        exitButton.setY(Gdx.graphics.getHeight()/2 - exitButton.getHeight()/2 - exitButton.getHeight() * (float) 1.5 - 2*exitButton.getHeight());

        mainMenuSprite.setSize((float) (Gdx.graphics.getWidth()*0.09375), (float) (Gdx.graphics.getHeight()*0.0834));
        mainMenuButton = new Button(new SpriteDrawable(mainMenuSprite));
        mainMenuButton.setX(Gdx.graphics.getWidth()/2 - mainMenuButton.getWidth()/2);
        mainMenuButton.setY(Gdx.graphics.getHeight()/2 - mainMenuButton.getHeight()/2 - 2 * mainMenuButton.getHeight());

        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()), t.batch);
        stage.addActor(exitButton);
        stage.addActor(mainMenuButton);
        Gdx.input.setInputProcessor(stage);

    }


    /**
     * Function that is called in the render function of this screen to draw the player's score
     */
    public void drawScore(){

        int score = t.g.getScore();
        setScoreArrayValues(score);

    }

    /**
     * This function alters the scoreArray in order for the values of the array be the score numbers in the correct
     * spaces (the score must have 9 number being that the unused spaces to the left are zeroes)
     * The printNumber function is called to print each of the 9 numbers
     * @param s
     */
    public void setScoreArrayValues(Integer s){

        String score = s.toString();

        for(int i = 0; i < score.length(); i++){
            scoreArray[8 - i] = Character.getNumericValue((int) score.charAt(score.length() - 1 - i));
        }


        for(int i = 0; i < 9; i++){
            printNumber(i, scoreArray[i]);
        }

    }

    /**
     * This function prints the received number in the received position of the score (from 0 to 8 for the 9
     * numbers mandatory for the scoreArray)
     * @param pos
     * @param num
     */
    public void printNumber(int pos, int num){

        Texture tex;

        switch(num){
            case 0:
                tex = zero;
                break;
            case 1:
                tex = one;
                break;
            case 2:
                tex = two;
                break;
            case 3:
                tex = three;
                break;
            case 4:
                tex = four;
                break;
            case 5:
                tex = five;
                break;
            case 6:
                tex = six;
                break;
            case 7:
                tex = seven;
                break;
            case 8:
                tex = eight;
                break;
            case 9:
                tex = nine;
                break;
            default:
                tex = one;
                break;
        }
        t.batch.begin();
        t.batch.draw(tex, numberCoords.X() + pos * numSide *(float)0.5, numberCoords.Y(), numSide, numSide);
        t.batch.end();
    }


    @Override
    public void dispose() {

    }

    /**
     * Draws all the necessary parts on the screen, them being the exit and the main menu buttons (added to the stage),
     * the backgound and game over textures, and also the drawScrore function is called to draw the player's score
     * @param delta
     */
    @Override
    public void render(float delta) {

        t.batch.begin();
        t.batch.draw(background, 0, 0);
        t.batch.draw(gameOver, numberCoords.X() + 100, numberCoords.Y() + 170, 800, 300);
        t.batch.end();
        stage.draw();

        drawScore();

        if (exitButton.isPressed()){
            Gdx.app.exit();
        }

        if(mainMenuButton.isPressed()){
            this.dispose();
            t.setScreen(new MainMenuScreen(t));
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
