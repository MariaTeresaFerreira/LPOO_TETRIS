package com.mygdx.mainpackage;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;


public class GameState {

    private Tetromino curr;
    private Tetromino hold;
    private boolean canHold;
    private LinkedList<Block> placed = new LinkedList<Block>();
    private LinkedList<Tetromino> next = new LinkedList<Tetromino>();
    private int[] lines = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private char activeEffect; // R ---> Rotation Lock, U ---> Speed Up, D ---> Speed Down, N ---> None
    public Float effectTimer;
    private char mode;// C ---> Classic, K ---> Kray-Z Blox, E ---> Escape The Matrix
    private char end; //'M' ---> Marathon, 'S' ---> Sprint,  'T' ---> Time
    private Float timer;
    private int score;
    private int objective;
    private Boolean toSlide;

    private int maxX = 10;

    public GameState(){
        this.mode = 'C'; // Default
        this.end = 'M';  // Default
        this.timer = 0f;
        this.canHold = true;
        this.curr = this.genTetromino('N');
        this.next.add(this.genTetromino('N'));
        this.activeEffect = 'N';
        this.effectTimer = 0.0f;
        score = 0;
        objective = 0;
        toSlide = false;
    }

    public void setMode(char mode){
        this.mode = mode;
    }

    public void setEnd(char endM){
        switch (endM){
            case 'S':
                this.end = 'S';
                objective = 50;
                break;
            case 'T':
                this.end = 'T';
                this.timer = 60f;
                break;
            default:
                this.end = 'M';
        }
    }

    public void decTimer(float deltaTime){
        timer -= deltaTime;

    }

    public void incTimer(float deltaTime){
        timer += deltaTime;
    }

    public char genPower(){

        if (mode == 'K') {
            Random randomno = new Random();
            int x = randomno.nextInt(100);
            if (x > 20){
                x = randomno.nextInt(11);
                switch (x){
                    case 0:
                        return 'T'; // NEXT 5 TETROMINOES ARE T-SHAPED
                    case 1:
                        return 'I'; // NEXT 5 TETROMINOES ARE I-SHAPED
                    case 2:
                        return 'O'; // NEXT 5 TETROMINOES ARE O-SHAPED
                    case 3:
                        return 'L'; // NEXT 5 TETROMINOES ARE L-SHAPED
                    case 4:
                        return 'J'; // NEXT 5 TETROMINOES ARE J-SHAPED
                    case 5:
                        return 'S'; // NEXT 5 TETROMINOES ARE S-SHAPED
                    case 6:
                        return 'Z'; // NEXT 5 TETROMINOES ARE Z-SHAPED
                    case 7:
                        return '+'; // FOR 10 s, SPEED IS SET TO 0.25 (4 drops/sec)
                    case 8:
                        return '-'; // FOR 10 s, SPEED IS SET TO 2 (0.5 drops/sec)
                    case 9:
                        return 'R'; // FOR 10 s, ROTATION IS LOCKED
                    case 10:
                        return 'K'; // SHIFTS THE PLACED BLOCKS TO THE LEFT AND DOWN, FILLING THE SPACES AND BREAKING LINES
                    default:
                        break;
                }
            }
        }

        return 'N';
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

    public Tetromino genSpecTetromino(char spec){
        switch (spec){
            case 'I':
                return (new TetroI('N'));
            case 'L':
                return (new TetroL('N'));
            case 'J':
                return (new TetroJ('N'));
            case 'O':
                return (new TetroO('N'));
            case 'S':
                return (new TetroS('N'));
            case 'Z':
                return (new TetroZ('N'));
            default:
                return (new TetroT('N'));

        }
    }

    public void hold(){
        if(this.canHold) {
            if (this.hold == null) {
                this.hold = this.curr;
                this.hold.dir = 0;
                this.hold.blocks = this.hold.startingPos;
                this.curr = this.next.get(0);
                this.next.remove(0);
                if (this.next.size() == 0) {
                    this.next.add(genTetromino(genPower()));
                }
            } else {
                Tetromino temp = this.hold;
                this.hold = this.curr;
                this.hold.dir = 0;
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
        if (this.next.size() == 0) {
            this.next.add(genTetromino(genPower()));
        }
        canHold();
        Collections.sort(placed); //Sort Block List
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

            lines[(int)curr.getBlocks().get("A").getCoords().Y()] += 1;
            lines[(int)curr.getBlocks().get("B").getCoords().Y()] += 1;
            lines[(int)curr.getBlocks().get("C").getCoords().Y()] += 1;
            lines[(int)curr.getBlocks().get("D").getCoords().Y()] += 1;
            lock();
        }
    }

    public void setNext5P(char next){
        this.next.clear();
        Tetromino t1 = genSpecTetromino(next);
        Tetromino t2 = genSpecTetromino(next);
        Tetromino t3 = genSpecTetromino(next);
        Tetromino t4 = genSpecTetromino(next);
        Tetromino t5 = genSpecTetromino(next);


        this.next.add(t1);
        this.next.add(t2);
        this.next.add(t3);
        this.next.add(t4);
        this.next.add(t5);
    }

    public void clearEffect(){
        this.activeEffect = 'N';
        this.effectTimer = 0f;
    }

    public char getActiveEffect(){
        return activeEffect;
    }

    public boolean canSlideLeft(Block b){
        int i = 0;
        Block c = new Block(b);
        if (b.getCoords().X() == 0) return false;
        c.setCoords(c.getCoords().X() - 1, c.getCoords().Y());
        for(Block e: placed){
            if (e.getCoords().equals(c.getCoords())) {
                return false;
            }
        }
        return true;
    }

    public void shiftPlacedLeft(){
        for (Block b: placed) {
            while (canSlideLeft(b)) {
                b.setCoords(b.getCoords().X() - 1, b.getCoords().Y());
            }
        }
        toSlide = false;
    }

    public void activatePower(char power){
        switch (power){
            case 'I':
                setNext5P('I');
                break;
            case 'L':
                setNext5P('L');
                break;
            case 'J':
                setNext5P('J');
                break;
            case 'O':
                setNext5P('O');
                break;
            case 'S':
                setNext5P('S');
                break;
            case 'Z':
                setNext5P('Z');
                break;
            case 'T':
                setNext5P('T');
                break;
            case '+':
                activeEffect = 'U';
                effectTimer = 10f;
                break;
            case '-':
                activeEffect = 'D';
                effectTimer = 10f;
                break;
            case 'R':
                activeEffect = 'R';
                effectTimer = 10f;
                break;
            default:
                break;
        }
    }

    public void hardDrop(){

        while(canDrop()){
            drop();
        }

        lines[(int)curr.getBlocks().get("A").getCoords().Y()] += 1;
        lines[(int)curr.getBlocks().get("B").getCoords().Y()] += 1;
        lines[(int)curr.getBlocks().get("C").getCoords().Y()] += 1;
        lines[(int)curr.getBlocks().get("D").getCoords().Y()] += 1;
        lock();

        score += 10;
        if(score > 999999999){
            score = 999999999;
        }
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

        if (end == 'T' && timer <= 0f) return true;
        if (end == 'S' && objective <= 0) return true;

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

    public void incrementScore(int value){
        this.score += value;
        if(this.score > 999999999){
            this.score = 999999999;
        }
    }
    public int getScore(){
        return this.score;
    }
    public Tetromino getNextTetromino(){
        return next.get(0);
    }

    public Tetromino getHold() {
        return hold;
    }

    public float getTimer(){
        return timer;
    }



    public int findLine(){


        for(int i = 0; i < 15; i++){

            if(lines[i] == 10){
                return i;
            }
        }
        return -1;
    }

    public  boolean getToSlide(){
        return toSlide;
    }

    public int deleteLine(){

        LinkedList <Block> newBlockList = new LinkedList<Block>();

        int line = findLine();

        if(line == -1){
            return -1;
        }

        for(Block b: placed){
            if(b.getCoords().Y() > line){
                newBlockList.add(b);
            }else if (b.getCoords().Y() < line){
                Block c = new Block (new Coords(b.getCoords().X(), b.getCoords().Y() + 1), b.getColour(), b.getPower());
                newBlockList.add(c);
            } else {
                if (b.getPower() == 'K') {
                    toSlide = true;
                }
                activatePower(b.getPower());
            }
        }

        setPlaced(newBlockList);


        int[] newList = new int[15];


        for(int i = 0; i <= line; i++){
            if (i == 0){
                newList[0] = 0;
            } else {
                newList[i] = lines[i - 1];
            }
        }
        for (int j = line + 1; j < 15; j ++){
            newList[j] = lines[j];
        }

        setLines(newList);

        return 0;

    }

    public void clearLinesScore(){
        int noOfLines = 0;
        int pointsAdded = 0;
        while(deleteLine() != -1){
            noOfLines ++;
        }

        switch (noOfLines){
            case 1:
                pointsAdded = 100;
                break;
            case 2:
                pointsAdded = 250;
                break;
            case 3:
                pointsAdded = 500;
                break;
            case 4:
                pointsAdded = 1000;
                break;
            default:
                break;
        }
        objective -= noOfLines;

        incrementScore(pointsAdded);

    }
}
