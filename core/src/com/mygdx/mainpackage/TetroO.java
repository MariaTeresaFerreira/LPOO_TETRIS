package com.mygdx.mainpackage;

import java.util.LinkedList;

public class TetroO extends Tetromino {

    public TetroO(char power){
        super('O');
        Block a = new Block(new Coords (0, 0), 'O', 'N');
        Block b = new Block(new Coords (1, 0), 'O', 'N');
        Block c = new Block(new Coords (0, 1), 'O', power);
        Block d = new Block(new Coords (1, 1), 'O', 'N');
        super.putBlocks(startingPos, new Block(a),new Block(b), new Block(c), new Block(d));
        super.putBlocks(blocks, a, b, c, d);
    }

    public void rotate(LinkedList<Block> placed){}

    public void etmShift(int num){
        Coords a = this.getBlocks().get("A").getCoords();
        a.setCoords(a.X() + 2*num, a.Y());
        Coords b = this.getBlocks().get("B").getCoords();
        b.setCoords(b.X() + 2*num, b.Y());
        Coords c = this.getBlocks().get("C").getCoords();
        c.setCoords(c.X() + 2*num, c.Y());
        Coords d = this.getBlocks().get("D").getCoords();
        d.setCoords(d.X() + 2*num, d.Y());
    }
/*
    public static void main (String [] arg){
        Tetromino t = new TetroO('O');
        System.out.println(t);
    }*/
}
