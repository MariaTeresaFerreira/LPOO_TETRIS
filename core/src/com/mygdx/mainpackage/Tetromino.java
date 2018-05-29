package com.mygdx.mainpackage;

import java.util.HashMap;

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
        s += this.blocks.get("A").toString() + "\n";
        s += this.blocks.get("B").toString() + "\n";
        s += this.blocks.get("C").toString() + "\n";
        s += this.blocks.get("D").toString() + "\n";
        s += this.dir;
        return s;
    }

    public HashMap<String, Block> getBlocks(){
        return blocks;
    }

    public String getShape(){
        return String.valueOf(shape);
    }

    public abstract void rotate();
/*
    public static void main (String [] arg){
        Tetromino t = new Tetromino('Z');
        System.out.println(t);
    }*/
}
