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

    public void drop(){
        Coords ac = this.blocks.get("A").getCoords();
        Coords bc = this.blocks.get("B").getCoords();
        Coords cc = this.blocks.get("C").getCoords();
        Coords dc = this.blocks.get("D").getCoords();
        this.blocks.get("A").getCoords().setCoords(ac.X() + 0, ac.Y() + 1);
        this.blocks.get("B").getCoords().setCoords(bc.X() + 0, bc.Y() + 1);
        this.blocks.get("C").getCoords().setCoords(cc.X() + 0, cc.Y() + 1);
        this.blocks.get("D").getCoords().setCoords(dc.X() + 0, dc.Y() + 1);
    }

    public abstract void rotate();
/*
    public static void main (String [] arg){
        Tetromino t = new Tetromino('Z');
        System.out.println(t);
    }*/
}
