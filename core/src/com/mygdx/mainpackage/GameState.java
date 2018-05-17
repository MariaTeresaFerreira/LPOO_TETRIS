package com.mygdx.mainpackage;


import java.util.LinkedList;
import java.util.Random;

public class GameState {

    private Tetromino curr;
    private Tetromino hold;
    private LinkedList<Block> placed = new LinkedList<Block>();
    private LinkedList<Tetromino> next = new LinkedList<Tetromino>();
    private char mode;

    public void genTetromino(char power){
        Random randomno = new Random();
        int x = randomno.nextInt(6);
        switch (x){
            case 0:
                this.next.add(new TetroI(power));
                break;
            case 1:
                this.next.add(new TetroL(power));
                break;
            case 2:
                this.next.add(new TetroJ(power));
                break;
            case 3:
                this.next.add(new TetroO(power));
                break;
            case 4:
                this.next.add(new TetroS(power));
                break;
            case 5:
                this.next.add(new TetroZ(power));
                break;
            default:
                this.next.add(new TetroT(power));

        }
    }

    public void drop(){
        Coords ac = curr.getBlocks().get("A").getCoords();
        Coords bc = curr.getBlocks().get("B").getCoords();
        Coords cc = curr.getBlocks().get("C").getCoords();
        Coords dc = curr.getBlocks().get("D").getCoords();
        curr.getBlocks().get("A").getCoords().setCoords(ac.X() + 0, ac.Y() + 1);
        curr.getBlocks().get("B").getCoords().setCoords(bc.X() + 0, bc.Y() + 1);
        curr.getBlocks().get("C").getCoords().setCoords(cc.X() + 0, cc.Y() + 1);
        curr.getBlocks().get("D").getCoords().setCoords(dc.X() + 0, dc.Y() + 1);
    }


    public GameState(char mode){
        this.mode = mode;
        this.genTetromino('N');
        this.genTetromino('N');
        this.curr = this.next.get(0);
        this.next.remove(0);

    }
}
