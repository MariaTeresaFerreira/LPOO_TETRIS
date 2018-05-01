package com.mygdx.weed;

import java.util.LinkedList;

class Block{
    private Coords coords;
    private char colour;
    private char power;

    public Block(){
        this.coords = new Coords(0 ,0);
        this.colour = ' ';
        this.power = ' ';
    }

    public Block(Coords c, char colour, char pow) {
        this.coords.setCoords(c);
        this.colour = colour;
        this.power = pow;
    }

    public Coords getCoords(){
        return this.coords;
    }

    public char getColour(){
        return  this.colour;
    }

    public char getPower(){
        return this.power;
    }

    public int compareTo(Block b){
        return this.coords.compareTo(b.getCoords());
    }

}

public class Tetromino {

    LinkedList<Block> blocks = new LinkedList<Block>();
    char shape;
    char dir;

}
