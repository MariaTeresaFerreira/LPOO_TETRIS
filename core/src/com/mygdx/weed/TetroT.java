package com.mygdx.weed;

public class TetroT extends Tetromino {

    public TetroT(char power){
        super('T');
        Block a = new Block(new Coords (1, 0), 'P', 'N');
        Block b = new Block(new Coords (0, 1), 'P', 'N');
        Block c = new Block(new Coords (1, 1), 'P', power);
        Block d = new Block(new Coords (2, 1), 'P', 'N');
        this.blocks.add(a);
        this.blocks.add(b);
        this.blocks.add(c);
        this.blocks.add(d);
    }

    public void rotate(){}
}
