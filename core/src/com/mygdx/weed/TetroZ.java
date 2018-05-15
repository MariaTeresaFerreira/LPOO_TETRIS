package com.mygdx.weed;

public class TetroZ extends Tetromino {

    public TetroZ(char power){
        super('Z');
        Block a = new Block(new Coords (0, 0), 'R', 'N');
        Block b = new Block(new Coords (1, 2), 'R', power);
        Block c = new Block(new Coords (0, 1), 'R', 'N');
        Block d = new Block(new Coords (1, 1), 'R', 'N');
        this.blocks.add(a);
        this.blocks.add(b);
        this.blocks.add(c);
        this.blocks.add(d);
    }

    public void rotate(){}
}
