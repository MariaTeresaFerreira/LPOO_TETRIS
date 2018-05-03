package com.mygdx.weed;

/**
 * Created by pppereira3 on 03-05-2018.
 */

public class TetroI extends Tetromino {

    public TetroI(char power){
        super('I');
        Block a = new Block(new Coords (0, 0), 'B', power);
        Block b = new Block(new Coords (1, 0), 'B', 'N');
        Block c = new Block(new Coords (2, 0), 'B', 'N');
        Block d = new Block(new Coords (3, 0), 'B', 'N');
        this.blocks.add(a);
        this.blocks.add(b);
        this.blocks.add(c);
        this.blocks.add(d);
    }
    /*
    public static void main (String [] arg){
        Tetromino t = new TetroI('I');
        System.out.println(t);
    }
    * */

    /*ROTATE*/
}
