package com.mygdx.mainpackage;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class GameState {

    private Tetromino curr;
    private Tetromino hold;
    private boolean canHold;
    private LinkedList<Block> placed = new LinkedList<Block>();
    private LinkedList<Tetromino> next = new LinkedList<Tetromino>();
    private int[] lines = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private char mode;

    private int maxX = 10;

    public GameState(char mode){
        this.mode = mode;
        this.canHold = true;
        this.curr = this.genTetromino('N');
        this.next.add(this.genTetromino('N'));
    }

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

    public void hold(){
        if(this.canHold) {
            if (this.hold == null) {
                this.hold = this.curr;
                this.hold.blocks = this.hold.startingPos;
                this.next.remove(0);
                this.next.add(genTetromino('N'));
            } else {
                Tetromino temp = this.hold;
                this.hold = this.curr;
                this.hold.blocks = this.hold.startingPos;
                this.curr = temp;
            }

            this.canHold = false;
        }
    }

    public void canHold(){
        this.canHold = true;
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

    public boolean canDrop(){
        if (canDrop(curr.getBlocks().get("A").getCoords()) &&
                canDrop(curr.getBlocks().get("B").getCoords()) &&
                canDrop(curr.getBlocks().get("C").getCoords()) &&
                canDrop(curr.getBlocks().get("D").getCoords())){
            return true;
        }

        return false;
    }

    public void lock(){
        placed.add(curr.getBlocks().get("A"));
        placed.add(curr.getBlocks().get("B"));
        placed.add(curr.getBlocks().get("C"));
        placed.add(curr.getBlocks().get("D"));
        curr = this.next.get(0);
        this.next.remove(0);
        this.next.add(genTetromino('N'));
        canHold();
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
        } else {
            lock();

            lines[(int)curr.getBlocks().get("A").getCoords().Y()] += 1;
            lines[(int)curr.getBlocks().get("B").getCoords().Y()] += 1;
            lines[(int)curr.getBlocks().get("C").getCoords().Y()] += 1;
            lines[(int)curr.getBlocks().get("D").getCoords().Y()] += 1;


            curr = genTetromino('N');
        }
    }

    public void hardDrop(){

        while(canDrop()){
            drop();
        }

        lock();

        lines[(int)curr.getBlocks().get("A").getCoords().Y()] += 1;
        lines[(int)curr.getBlocks().get("B").getCoords().Y()] += 1;
        lines[(int)curr.getBlocks().get("C").getCoords().Y()] += 1;
        lines[(int)curr.getBlocks().get("D").getCoords().Y()] += 1;
    }


    public boolean validSL(){
        Coords ac = new Coords();
        Coords bc = new Coords();
        Coords cc = new Coords();
        Coords dc = new Coords();

        ac.setCoords(curr.getBlocks().get("A").getCoords().X() -1, curr.getBlocks().get("A").getCoords().Y());
        bc.setCoords(curr.getBlocks().get("B").getCoords().X() -1, curr.getBlocks().get("B").getCoords().Y());
        cc.setCoords(curr.getBlocks().get("C").getCoords().X() -1, curr.getBlocks().get("C").getCoords().Y());
        dc.setCoords(curr.getBlocks().get("D").getCoords().X() -1, curr.getBlocks().get("D").getCoords().Y());

        if (!(ac.X() >= 0 && bc.X() >= 0 && cc.X() >= 0 && dc.X() >= 0 )){
            return false;
        }

        for (Block b: placed){
            if (b.getCoords().equals(ac) || b.getCoords().equals(bc) ||
                    b.getCoords().equals(cc) || b.getCoords().equals(dc)){
                return false;
            }
        }

        return true;


    }

    public void shiftLeft(){
       if(validSL()){
           curr.getBlocks().get("A").getCoords().setCoords(curr.getBlocks().get("A").getCoords().X() -1,
                   curr.getBlocks().get("A").getCoords().Y());;
           curr.getBlocks().get("B").getCoords().setCoords(curr.getBlocks().get("B").getCoords().X() -1,
                   curr.getBlocks().get("B").getCoords().Y());;
           curr.getBlocks().get("C").getCoords().setCoords(curr.getBlocks().get("C").getCoords().X() -1,
                   curr.getBlocks().get("C").getCoords().Y());;
           curr.getBlocks().get("D").getCoords().setCoords(curr.getBlocks().get("D").getCoords().X() -1,
                   curr.getBlocks().get("D").getCoords().Y());;
       }
    }

    public boolean validSR(){

        Coords ac = new Coords();
        Coords bc = new Coords();
        Coords cc = new Coords();
        Coords dc = new Coords();

        ac.setCoords(curr.getBlocks().get("A").getCoords().X() +1, curr.getBlocks().get("A").getCoords().Y());
        bc.setCoords(curr.getBlocks().get("B").getCoords().X() +1, curr.getBlocks().get("B").getCoords().Y());
        cc.setCoords(curr.getBlocks().get("C").getCoords().X() +1, curr.getBlocks().get("C").getCoords().Y());
        dc.setCoords(curr.getBlocks().get("D").getCoords().X() +1, curr.getBlocks().get("D").getCoords().Y());

        if (!(ac.X() < maxX && bc.X() < maxX && cc.X() < maxX && dc.X() < maxX )){
            return false;
        }

        for (Block b: placed){
            if (b.getCoords().equals(ac) || b.getCoords().equals(bc) ||
                    b.getCoords().equals(cc) || b.getCoords().equals(dc)){
                return false;
            }
        }

        return true;

    }

    public void shiftRight(){
        if (validSR()){
            curr.getBlocks().get("A").getCoords().setCoords(curr.getBlocks().get("A").getCoords().X() +1,
                    curr.getBlocks().get("A").getCoords().Y());;
            curr.getBlocks().get("B").getCoords().setCoords(curr.getBlocks().get("B").getCoords().X() +1,
                    curr.getBlocks().get("B").getCoords().Y());;
            curr.getBlocks().get("C").getCoords().setCoords(curr.getBlocks().get("C").getCoords().X() +1,
                    curr.getBlocks().get("C").getCoords().Y());;
            curr.getBlocks().get("D").getCoords().setCoords(curr.getBlocks().get("D").getCoords().X() +1,
                    curr.getBlocks().get("D").getCoords().Y());;
        }
    }

    public Tetromino getCurr(){
        return this.curr;
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

    public void setPlaced(LinkedList<Block> b){
        this.placed = b;
    }

    public int[] getLines(){
        return this.lines;
    }

    public void setLinesValue(int i, int value){
        lines[i] = value;
    }

    public void setLines(int[] nl){
        lines = nl;
    }

}
