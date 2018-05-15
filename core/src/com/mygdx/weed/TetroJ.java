package com.mygdx.weed;

public class TetroJ extends Tetromino {

    public TetroJ(char power){
        super('J');
        Block a = new Block(new Coords (0, 0), 'J', 'N');
        Block b = new Block(new Coords (0, 1), 'J', 'N');
        Block c = new Block(new Coords (1, 1), 'J', power);
        Block d = new Block(new Coords (2, 1), 'J', 'N');
        this.blocks.put("A", a);
        this.blocks.put("B", b);
        this.blocks.put("C", c);
        this.blocks.put("D", d);
    }

    public void rotate(){}
}
