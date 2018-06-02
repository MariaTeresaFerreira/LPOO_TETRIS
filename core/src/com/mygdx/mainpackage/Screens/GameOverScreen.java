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

    private Texture background = new Texture("background2.png");
    private Texture gameOver = new Texture("GameOver.png");

    private Sprite mainMenuSprite = new Sprite(new Texture("MainMenuButton.png"));
    private Sprite exitSprite = new Sprite(new Texture("exitButton.png"));

    private Button mainMenuButton;
    private Button exitButton;

    private Stage stage;
    private Viewport vp;
    private Tetris t;

    private Texture zero = new Texture("numbers/0.png");
    private Texture one = new Texture("numbers/1.png");
    private Texture two = new Texture("numbers/2.png");
    private Texture three = new Texture("numbers/3.png");
    private Texture four = new Texture("numbers/4.png");
    private Texture five = new Texture("numbers/5.png");
    private Texture six = new Texture("numbers/6.png");
    private Texture seven = new Texture("numbers/7.png");
    private Texture eight = new Texture("numbers/8.png");
    private Texture nine = new Texture("numbers/9.png");

    private int numSide = 200;
    private Coords numberCoords = new Coords(Gdx.graphics.getWidth()/2-((float)2.5*numSide), Gdx.graphics.getHeight() * (float)0.42);
    private int[] scoreArray = {0, 0, 0, 0, 0, 0, 0, 0, 0};



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

    public void drawScore(){

        //System.out.println(t.g.getScore());

        int score = t.g.getScore();
        setScoreArrayValues(score);



    }

    public void setScoreArrayValues(Integer s){

        String score = s.toString();

        for(int i = 0; i < score.length(); i++){
            scoreArray[8 - i] = Character.getNumericValue((int) score.charAt(score.length() - 1 - i));
        }


        for(int i = 0; i < 9; i++){
            printNumber(i, scoreArray[i]); // VE ESTA PARTE
        }

        //System.out.println("Depois");
        for(int i = 0; i < 9; i++){
            //System.out.print(scoreArray[i]);
        }
        //System.out.println();
    }

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

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }
}
