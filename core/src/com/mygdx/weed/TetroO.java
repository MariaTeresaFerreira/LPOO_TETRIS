package com.mygdx.weed;

public class TetroO extends Tetromino {

    public TetroO(char power){
        super('O');
        Block a = new Block(new Coords (0, 0), 'Y', 'N');
        Block b = new Block(new Coords (1, 0), 'Y', 'N');
        Block c = new Block(new Coords (0, 1), 'Y', power);
        Block d = new Block(new Coords (1, 1), 'Y', 'N');
        this.blocks.put("A", a);
        this.blocks.put("B", b);
        this.blocks.put("C", c);
        this.blocks.put("D", d);
    }

    public void rotate(){}
/*
    public static void main (String [] arg){
        Tetromino t = new TetroO('O');
        System.out.println(t);
    }*/
}
