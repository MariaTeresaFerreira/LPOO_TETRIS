package com.mygdx.weed;

/**
 * Created by pppereira3 on 12-05-2018.
 */

public class TetroS extends Tetromino {

    public TetroS(char power){
        super('S');
        Block a = new Block(new Coords (0, 0), 'G', 'N');
        Block b = new Block(new Coords (1, 0), 'G', 'N');
        Block c = new Block(new Coords (1, 1), 'G', power);
        Block d = new Block(new Coords (2, 1), 'G', 'N');
        this.blocks.put("A", a);
        this.blocks.put("B", b);
        this.blocks.put("C", c);
        this.blocks.put("D", d);
    }

    public void rotate(){}
}
