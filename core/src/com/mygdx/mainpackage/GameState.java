package com.mygdx.mainpackage;


import java.util.LinkedList;
import java.util.Random;

public class GameState {

    private Tetromino curr;
    private Tetromino hold;
    private LinkedList<Block> placed = new LinkedList<Block>();
    private LinkedList<Tetromino> next = new LinkedList<Tetromino>();
    private char mode;

    public Tetromino genTetromino(char power){
        Random randomno = new Random();
        int x = randomno.nextInt(7);
        switch (x){
            case 0:
                return (new TetroI(power));
            case 1:
               return (new TetroL(power));
            case 2:
                return (new TetroJ(power));
            case 3:
                return (new TetroO(power));
            case 4:
                return (new TetroS(power));
            case 5:
                return (new TetroZ(power));
            default:
                return (new TetroT(power));

        }
    }

    public boolean canDrop(Coords a){

        for (Block b: placed){
            if (a.X() == b.getCoords().X() && a.Y() == b.getCoords().Y() - 1)
                return false;
        }

        if (a.Y() == 14) {
            return false;
        }

        return true;
    }

    public void drop(){
        Coords ac = curr.getBlocks().get("A").getCoords();
        Coords bc = curr.getBlocks().get("B").getCoords();
        Coords cc = curr.getBlocks().get("C").getCoords();
        Coords dc = curr.getBlocks().get("D").getCoords();
        if (canDrop(ac) && canDrop(bc) && canDrop(cc) && canDrop(dc)) {
            curr.getBlocks().get("A").getCoords().setCoords(ac.X() + 0, ac.Y() + 1);
            curr.getBlocks().get("B").getCoords().setCoords(bc.X() + 0, bc.Y() + 1);
            curr.getBlocks().get("C").getCoords().setCoords(cc.X() + 0, cc.Y() + 1);
            curr.getBlocks().get("D").getCoords().setCoords(dc.X() + 0, dc.Y() + 1);
        }
        else {
            placed.add(curr.getBlocks().get("A"));
            placed.add(curr.getBlocks().get("B"));
            placed.add(curr.getBlocks().get("C"));
            placed.add(curr.getBlocks().get("D"));
            curr = genTetromino('N');
        }
    }

    public Tetromino getCurr(){
        return this.curr;
    }

    public GameState(char mode){
        this.mode = mode;
        this.curr = this.genTetromino('N');
        this.next.add(this.genTetromino('N'));
    }

    public boolean defeat(){
        for(Block b: placed){
            if(b.getCoords().Y() == 0) return true;
        }
        return false;
    }

    public LinkedList<Block> getPlaced(){
        return this.placed;
    }
}
