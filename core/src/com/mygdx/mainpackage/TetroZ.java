package com.mygdx.mainpackage;

public class TetroZ extends Tetromino {

    public TetroZ(char power){
        super('Z');
        Block a = new Block(new Coords (0, 0), 'Z', 'N');
        Block b = new Block(new Coords (1, 2), 'Z', power);
        Block c = new Block(new Coords (0, 1), 'Z', 'N');
        Block d = new Block(new Coords (1, 1), 'Z', 'N');
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
                this.blocks.get("A").getCoords().setCoords(ac.X() + 2, ac.Y());
                this.blocks.get("B").getCoords().setCoords(bc.X() + 1, bc.Y() + 1);
                this.blocks.get("C").getCoords().setCoords(cc.X(), cc.Y());
                this.blocks.get("D").getCoords().setCoords(dc.X() - 1, dc.Y() + 1);
                break;
            case 1:
                this.blocks.get("A").getCoords().setCoords(ac.X(), ac.Y() + 2);
                this.blocks.get("B").getCoords().setCoords(bc.X() - 1, bc.Y() + 1);
                this.blocks.get("C").getCoords().setCoords(cc.X(), cc.Y());
                this.blocks.get("D").getCoords().setCoords(dc.X() - 1, dc.Y() - 1);
                break;
            case 2:
                this.blocks.get("A").getCoords().setCoords(ac.X() - 2, ac.Y());
                this.blocks.get("B").getCoords().setCoords(bc.X() - 1, bc.Y() - 1);
                this.blocks.get("C").getCoords().setCoords(cc.X(), cc.Y());
                this.blocks.get("D").getCoords().setCoords(dc.X() + 1, dc.Y() - 1);
                break;
            case 3:
                this.blocks.get("A").getCoords().setCoords(ac.X(), ac.Y() - 2);
                this.blocks.get("B").getCoords().setCoords(bc.X() + 1, bc.Y() - 1);
                this.blocks.get("C").getCoords().setCoords(cc.X(), cc.Y());
                this.blocks.get("D").getCoords().setCoords(dc.X() + 1, dc.Y() + 1);
                break;
            default:
                break;
        }

    }
}
