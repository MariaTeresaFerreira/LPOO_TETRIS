package com.mygdx.weed;

import java.util.HashMap;

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
        this.coords = c;
        this.colour = colour;
        this.power = pow;
    }

    public Coords getCoords(){
        return this.coords;
    }

    public void setCoords(Coords c){
        this.coords.setCoords(c);
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

    public String toString(){
        String s = "";
        s += this.colour + "\n";
        s += "(" + this.getCoords().X() + ", " + this.getCoords().Y() + ")\n";
        s += this.power;
        return s;
    }

}

public abstract class Tetromino {

    protected HashMap<String ,Block> blocks = new HashMap<String, Block>();
    protected char shape;
    protected Integer dir;

    public Tetromino(char s){
      this.shape = s;
      this.dir = 0;

    }

    public String toString(){
        String s = "";
        s += this.shape + "\n";
        for (int i = 0; i < this.blocks.size(); i++){
            s += this.blocks.get(i).toString() + "\n";
        }
        s += this.dir;
        return s;
    }

    public HashMap<String, Block> getBlocks(){
        return blocks;
    }

    public abstract void rotate();
/*
    public static void main (String [] arg){
        Tetromino t = new Tetromino('Z');
        System.out.println(t);
    }*/
}
