package com.mygdx.weed;

public class TetroI extends Tetromino {

    public TetroI(char power){
        super('I');
        Block a = new Block(new Coords (0, 0), 'B', power);
        Block b = new Block(new Coords (1, 0), 'B', 'N');
        Block c = new Block(new Coords (2, 0), 'B', 'N');
        Block d = new Block(new Coords (3, 0), 'B', 'N');
        this.blocks.put("A", a);
        this.blocks.put("B", b);
        this.blocks.put("C", c);
        this.blocks.put("D", d);
    }

    public void rotate(){
/*
        switch (this.dir){
            case 0:

        }*/
    }

    /*
    public static void main (String [] arg){
        Tetromino t = new TetroI('I');
        System.out.println(t);
    }
    */
}
