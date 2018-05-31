package com.mygdx.mainpackage;

import java.util.LinkedList;

public class TetroT extends Tetromino {

    public TetroT(char power){
        super('T');
        Block a = new Block(new Coords (1, 0), 'T', 'N');
        Block b = new Block(new Coords (0, 1), 'T', 'N');
        Block c = new Block(new Coords (1, 1), 'T', power);
        Block d = new Block(new Coords (2, 1), 'T', 'N');
        super.putBlocks(startingPos, new Block(a),new Block(b), new Block(c), new Block(d));
        super.putBlocks(blocks, a, b, c, d);
    }

    public void rotate(LinkedList<Block> placed){

        Coords ac = this.blocks.get("A").getCoords();
        Coords bc = this.blocks.get("B").getCoords();
        Coords cc = this.blocks.get("C").getCoords();
        Coords dc = this.blocks.get("D").getCoords();
        Coords na, nb, nd;

        switch (this.dir) {
            case 0:
                na = new Coords(ac.X() + 1, ac.Y() + 1);
                nb = new Coords(bc.X() + 1, bc.Y() - 1);
                nd = new Coords(dc.X() - 1, dc.Y() + 1);
                checkAndSet(placed, na, nb, cc, nd);
                break;
            case 1:
                na = new Coords(ac.X() - 1, ac.Y() + 1);
                nb = new Coords(bc.X() + 1, bc.Y() + 1);
                nd = new Coords(dc.X() - 1, dc.Y() - 1);
                checkAndSet(placed, na, nb, cc, nd);
                break;
            case 2:
                na = new Coords(ac.X() - 1, ac.Y() - 1);
                nb = new Coords(bc.X() - 1, bc.Y() + 1);
                nd = new Coords(dc.X() + 1, dc.Y() - 1);
                checkAndSet(placed, na, nb, cc, nd);
                break;
            case 3:
                na = new Coords(ac.X() + 1, ac.Y() - 1);
                nb = new Coords(bc.X() - 1, bc.Y() - 1);
                nd = new Coords(dc.X() + 1, dc.Y() + 1);
                checkAndSet(placed, na, nb, cc, nd);
                break;
            default:
                break;
        }

    }
}
