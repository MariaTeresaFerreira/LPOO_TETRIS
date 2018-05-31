package com.mygdx.mainpackage;

import java.util.LinkedList;

public class TetroI extends Tetromino {

    public TetroI(char power){
        super('I');
        Block a = new Block(new Coords (0, 0), 'I', power);
        Block b = new Block(new Coords (1, 0), 'I', 'N');
        Block c = new Block(new Coords (2, 0), 'I', 'N');
        Block d = new Block(new Coords (3, 0), 'I', 'N');
        super.putBlocks(startingPos, new Block(a),new Block(b), new Block(c), new Block(d));
        super.putBlocks(blocks, a, b, c, d);
    }


    public void rotate(LinkedList<Block> placed){
        Coords ac = this.blocks.get("A").getCoords();
        Coords bc = this.blocks.get("B").getCoords();
        Coords cc = this.blocks.get("C").getCoords();
        Coords dc = this.blocks.get("D").getCoords();
        Coords na, nb, nc, nd;
        switch (this.dir){
            case 0:
                na = new Coords(ac.X() + 2, ac.Y() - 1);
                nb = new Coords(bc.X() + 1, bc.Y() + 0);
                nc = new Coords(cc.X() + 0, cc.Y() + 1);
                nd = new Coords(dc.X() - 1, dc.Y() + 2);
                checkAndSet(placed, na, nb, nc, nd);
                break;

            case 1:
                na = new Coords(ac.X() + 1, ac.Y() + 2);
                nb = new Coords(bc.X() + 0, bc.Y() + 1);
                nc = new Coords(cc.X() - 1, cc.Y() + 0);
                nd = new Coords(dc.X() - 2, dc.Y() - 1);
                checkAndSet(placed, na, nb, nc, nd);
                break;

            case 2:
                na = new Coords(ac.X() - 2, ac.Y() + 1);
                nb = new Coords(bc.X() - 1, bc.Y() + 0);
                nc = new Coords(cc.X() - 0, cc.Y() - 1);
                nd = new Coords(dc.X() + 1, dc.Y() - 2);
                checkAndSet(placed, na, nb, nc, nd);
                break;

            case 3:
                na = new Coords(ac.X() - 1, ac.Y() - 2);
                nb = new Coords(bc.X() - 0, bc.Y() - 1);
                nc = new Coords(cc.X() + 1, cc.Y() - 0);
                nd = new Coords(dc.X() + 2, dc.Y() + 1);
                checkAndSet(placed, na, nb, nc, nd);
                break;

            default:
                break;
        }

    }

}
