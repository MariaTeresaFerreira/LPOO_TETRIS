package com.mygdx.weed;


public class TetroL extends Tetromino {

    public TetroL(char power){
        super('L');
        Block a = new Block(new Coords (0, 1), 'L', 'N');
        Block b = new Block(new Coords (1, 1), 'L', power);
        Block c = new Block(new Coords (2, 0), 'L', 'N');
        Block d = new Block(new Coords (2, 1), 'L', 'N');
        this.blocks.put("A", a);
        this.blocks.put("B", b);
        this.blocks.put("C", c);
        this.blocks.put("D", d);
    }

    public void rotate(){}
}
