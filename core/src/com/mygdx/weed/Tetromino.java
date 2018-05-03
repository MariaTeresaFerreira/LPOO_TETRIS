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

public class Tetromino {

    protected LinkedList<Block> blocks = new LinkedList<Block>();
    protected char shape;
    protected Integer dir;
/*
   public void genO(char power){
       Block a = new Block(new Coords (0, 0), 'Y', 'N');
       Block b = new Block(new Coords (1, 0), 'Y', 'N');
       Block c = new Block(new Coords (0, 1), 'Y', power);
       Block d = new Block(new Coords (1, 1), 'Y', 'N');
       this.blocks.add(a);
       this.blocks.add(b);
       this.blocks.add(c);
       this.blocks.add(d);
       this.pivot = a;
       this.shape = 'O';
       this.dir = 1;

   }

   public void genT(char power){
       Block a = new Block(new Coords (1, 0), 'P', 'N');
       Block b = new Block(new Coords (0, 1), 'P', 'N');
       Block c = new Block(new Coords (1, 1), 'P', power);
       Block d = new Block(new Coords (2, 1), 'P', 'N');
       this.blocks.add(a);
       this.blocks.add(b);
       this.blocks.add(c);
       this.blocks.add(d);
       this.pivot = c;
       this.shape = 'T';
       this.dir = 1;

   }

   public void genS(char power){
       Block a = new Block(new Coords (0, 0), 'G', 'N');
       Block b = new Block(new Coords (1, 0), 'G', 'N');
       Block c = new Block(new Coords (1, 1), 'G', power);
       Block d = new Block(new Coords (2, 1), 'G', 'N');
       this.blocks.add(a);
       this.blocks.add(b);
       this.blocks.add(c);
       this.blocks.add(d);
       this.pivot = b;
       this.shape = 'S';
       this.dir = 1;
   }

   public void genZ(char power){
       Block a = new Block(new Coords (0, 0), 'R', 'N');
       Block b = new Block(new Coords (1, 2), 'R', power);
       Block c = new Block(new Coords (0, 1), 'R', 'N');
       Block d = new Block(new Coords (1, 1), 'R', 'N');
       this.blocks.add(a);
       this.blocks.add(b);
       this.blocks.add(c);
       this.blocks.add(d);
       this.pivot = c;
       this.shape = 'Z';
       this.dir = 1;

   }

   public void genL(char power){
       Block a = new Block(new Coords (0, 1), 'L', 'N');
       Block b = new Block(new Coords (1, 1), 'L', power);
       Block c = new Block(new Coords (2, 0), 'L', 'N');
       Block d = new Block(new Coords (2, 1), 'L', 'N');
       this.blocks.add(a);
       this.blocks.add(b);
       this.blocks.add(c);
       this.blocks.add(d);
       this.pivot = b;
       this.shape = 'L';
       this.dir = 1;
   }

   public void genJ(char power){
       Block a = new Block(new Coords (0, 0), 'J', 'N');
       Block b = new Block(new Coords (0, 1), 'J', 'N');
       Block c = new Block(new Coords (1, 1), 'J', power);
       Block d = new Block(new Coords (2, 1), 'J', 'N');
       this.blocks.add(a);
       this.blocks.add(b);
       this.blocks.add(c);
       this.blocks.add(d);
       this.pivot = c;
       this.shape = 'J';
       this.dir = 1;

   }*/

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
/*
    public static void main (String [] arg){
        Tetromino t = new Tetromino('Z');
        System.out.println(t);
    }*/
}
