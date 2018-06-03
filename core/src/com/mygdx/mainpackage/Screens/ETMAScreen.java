package com.mygdx.mainpackage.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.mainpackage.Block;
import com.mygdx.mainpackage.Coords;
import com.mygdx.mainpackage.GameState;
import com.mygdx.mainpackage.Tetris;
import com.mygdx.mainpackage.Tetromino;

import java.util.LinkedList;

public class ETMAScreen extends MainGameScreen {

    private Dood dood;
    private Body curr1;
    private Body curr2;
    private World world;

    public ETMAScreen(final Tetris t, float speed){
        super(t);
        world = new World(new Vector2(0, -9.8f - 1/speed), true);
        t.speed = speed;

        dood = new Dood(world, displacementX + blockSize, displacementY );
    }


    public boolean doodColided(LinkedList<Block> placed){
        for(Block b: placed){
            if (super.genScreenCoords(b).equals(dood.getCoords())) return true;
        }
        float limInfX = genScreenCoords(t.g.getCurr().getBlocks().get("A")).X();
        float limSupX = genScreenCoords(t.g.getCurr().getBlocks().get("B")).X() + blockSize;
        float limInfY = genScreenCoords(t.g.getCurr().getBlocks().get("A")).Y() - blockSize;
        float limSupY = genScreenCoords(t.g.getCurr().getBlocks().get("D")).X();

        if (dood.getCoords().X() >= limInfX && dood.getCoords().X() <= limSupX
                && dood.getCoords().Y() >= limInfY && dood.getCoords().Y() <= limSupY) {
            return true;
        }

        if  (t.g.getCurr2() != null){
            limInfX = genScreenCoords(t.g.getCurr2().getBlocks().get("A")).X();
            limSupX = genScreenCoords(t.g.getCurr2().getBlocks().get("B")).X() + blockSize;
            limInfY = genScreenCoords(t.g.getCurr2().getBlocks().get("A")).Y() - blockSize;
            limSupY = genScreenCoords(t.g.getCurr2().getBlocks().get("D")).X();

            if (dood.getCoords().X() >= limInfX && dood.getCoords().X() <= limSupX
                    && dood.getCoords().Y() >= limInfY && dood.getCoords().Y() <= limSupY) {
                return true;
            }

        }

        return false;
    }

    public void upDoot(){
        if (dood.getCoords().X() > displacementX && dood.getCoords().X() < Gdx.graphics.getWidth() - displacementX ) dood.setCoords(new Coords(dood.getCoords().X() + Gdx.input.getAccelerometerX(), dood.getCoords().Y()));
    }

    public boolean reachedBottom(){
        if (dood.getCoords().Y() <= Gdx.graphics.getHeight() -  15* blockSize) return true;
        return false;
    }

    @Override
    public void render(float delta){
        if (doodColided(t.g.getPlaced())){
            this.dispose();
            t.setScreen(new GameOverScreen(t));
        }
        t.g.incScore(Gdx.graphics.getDeltaTime());
        super.render(delta);
        t.batch.begin();
        t.batch.draw(dood, dood.getX(), dood.getY(), 50, 50);
        t.batch.end();
        upDoot();
        dood.updateDood();
        if (reachedBottom()){
            this.dispose();
            if (t.speed > 0.1) t.speed -= 0.1f;
            t.g = new GameState();
            t.g.setMode('E');
            t.setScreen(new ETMDScreen(t, t.speed));
        }
        world.step(Gdx.graphics.getDeltaTime(), 6, 3);
    }

}