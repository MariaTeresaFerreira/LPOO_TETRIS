package com.mygdx.mainpackage;

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

    public void rotate(){

        Coords ac = this.blocks.get("A").getCoords();
        Coords bc = this.blocks.get("A").getCoords();
        Coords cc = this.blocks.get("A").getCoords();
        Coords dc = this.blocks.get("A").getCoords();

        switch (this.dir) {
            case 0:
                this.blocks.get("A").getCoords().setCoords(ac.X() + 1, ac.Y() - 1);
                this.blocks.get("B").getCoords().setCoords(bc.X(), bc.Y());
                this.blocks.get("C").getCoords().setCoords(cc.X() + 1, cc.Y() + 1);
                this.blocks.get("D").getCoords().setCoords(dc.X(), dc.Y() + 2);
                break;
            case 1:
                this.blocks.get("A").getCoords().setCoords(ac.X() + 1, ac.Y() + 1);
                this.blocks.get("B").getCoords().setCoords(bc.X(), bc.Y());
                this.blocks.get("C").getCoords().setCoords(cc.X() - 1, cc.Y() + 1);
                this.blocks.get("D").getCoords().setCoords(dc.X() - 2, dc.Y());
                break;
            case 2:
                this.blocks.get("A").getCoords().setCoords(ac.X() - 1, ac.Y() + 1);
                this.blocks.get("B").getCoords().setCoords(bc.X(), bc.Y());
                this.blocks.get("C").getCoords().setCoords(cc.X() - 1, cc.Y() - 1);
                this.blocks.get("D").getCoords().setCoords(dc.X(), dc.Y() - 2);
                break;
            case 3:
                this.blocks.get("A").getCoords().setCoords(ac.X() - 1, ac.Y() - 1);
                this.blocks.get("B").getCoords().setCoords(bc.X(), bc.Y());
                this.blocks.get("C").getCoords().setCoords(cc.X() + 1, cc.Y() - 1);
                this.blocks.get("D").getCoords().setCoords(dc.X() + 2, dc.Y());
                break;
            default:
                break;
        }
    }
}
