package com.mygdx.weed;

public class TetroT extends Tetromino {

    public TetroT(char power){
        super('T');
        Block a = new Block(new Coords (1, 0), 'P', 'N');
        Block b = new Block(new Coords (0, 1), 'P', 'N');
        Block c = new Block(new Coords (1, 1), 'P', power);
        Block d = new Block(new Coords (2, 1), 'P', 'N');
        this.blocks.put("A", a);
        this.blocks.put("B", b);
        this.blocks.put("C", c);
        this.blocks.put("D", d);
    }

    public void rotate(){

        Coords ac = this.blocks.get("A").getCoords();
        Coords bc = this.blocks.get("A").getCoords();
        Coords cc = this.blocks.get("A").getCoords();
        Coords dc = this.blocks.get("A").getCoords();

        switch (this.dir) {
            case 0:
                this.blocks.get("A").getCoords().setCoords(ac.X(), ac.Y());
                this.blocks.get("B").getCoords().setCoords(bc.X() + 1, bc.Y() - 1);
                this.blocks.get("C").getCoords().setCoords(cc.X() + 1, cc.Y() + 1);
                this.blocks.get("D").getCoords().setCoords(dc.X() - 1, dc.Y() + 1);
                break;
            case 1:
                this.blocks.get("A").getCoords().setCoords(ac.X(), ac.Y());
                this.blocks.get("B").getCoords().setCoords(bc.X() + 1, bc.Y() + 1);
                this.blocks.get("C").getCoords().setCoords(cc.X() - 1, cc.Y() + 1);
                this.blocks.get("D").getCoords().setCoords(dc.X() - 1, dc.Y() - 1);
                break;
            case 2:
                this.blocks.get("A").getCoords().setCoords(ac.X(), ac.Y());
                this.blocks.get("B").getCoords().setCoords(bc.X() - 1, bc.Y() + 1);
                this.blocks.get("C").getCoords().setCoords(cc.X() - 1, cc.Y() - 1);
                this.blocks.get("D").getCoords().setCoords(dc.X() + 1, dc.Y() - 1);
                break;
            case 3:
                this.blocks.get("A").getCoords().setCoords(ac.X(), ac.Y());
                this.blocks.get("B").getCoords().setCoords(bc.X() - 1, bc.Y() - 1);
                this.blocks.get("C").getCoords().setCoords(cc.X() + 1, cc.Y() - 1);
                this.blocks.get("D").getCoords().setCoords(dc.X() + 1, dc.Y() + 1);
                break;
            default:
                break;
        }

    }
}
