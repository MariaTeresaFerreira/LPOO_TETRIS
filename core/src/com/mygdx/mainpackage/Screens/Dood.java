package com.mygdx.mainpackage.Screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.mainpackage.Coords;
import com.mygdx.mainpackage.GameState;

import java.util.LinkedList;

public class Dood extends Sprite {

    private World world;
    private Body body;
    private Coords c;

    public Dood(World world, float dispX, float dispY){
        super(new Texture("dood.png"));
        setPosition(dispX, dispY);
        c = new Coords(dispX + 300, dispY);
        this.world = world;
        createBody();

    }

    void createBody(){

        BodyDef bodyDef = new BodyDef();

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(getX(),getY());
        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getX(), getY());

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1;

        Fixture fixture = body.createFixture(fixtureDef);
        shape.dispose();
    }

    public Body getBody(){
        return body;
    }

    public void setCoords(Coords nc){
        c = nc;
        setPosition(nc.X(), nc.Y());
    }

    public Coords getCoords(){
        return this.c;
    }

    void updateDood(){
        this.setPosition(c.X(), body.getWorldCenter().y);
        c.setCoords(new Coords(c.X(), body.getWorldCenter().y));
    }
}
