package com.mygdx.mainpackage;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;


public class GameState {

    /**
     * Current tetromino that is controlled by the player
     */
    private Tetromino curr;

    /**
     * Current tetromino2, Escape the matrix only
     */
    private Tetromino curr2;

    /**
     * Tetromino that is currently in the hold position
     */
    private Tetromino hold;
    /**
     * Boolean that tells if a current tetromino can change to the hold position
     */
    private boolean canHold;
    /**
     * List of all the already placed blocks
     */
    private LinkedList<Block> placed = new LinkedList<Block>();
    /**
     * List of the next tetrominos to enter the game
     */
    private LinkedList<Tetromino> next = new LinkedList<Tetromino>();
    /**
     * Number of cubes per horizontal line of game
     */
    private int[] lines = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    /**
     * Char that stores the active effect caused by a Kra-Z Block
     */
    private char activeEffect; // R ---> Rotation Lock, U ---> Speed Up, D ---> Speed Down, N ---> None
    /**
     * Times the effects that depend on time
     */
    public Float effectTimer;
    /**
     * Stores the selected game mode
     */
    private char mode;// C ---> Classic, K ---> Kray-Z Blox, E ---> Escape The Matrix
    /**
     * Stores the selected end mode
     */
    private char end; //'M' ---> Marathon, 'S' ---> Sprint,  'T' ---> Time
    /**
     * Handles the time
     */
    private Float timer;
    /**
     * Stores the current score of the game
     */
    private int score;
    /**
     * Stores how many lines the player needs to complete in order to finish the sprint game mode
     */
    private int objective;
    /**
     * Checks if a block can slide in the specific iteration for the slide effect cause by the Kray-Z Block
     */
    private Boolean toSlide;
    public Integer cubeCounter;

    /**
     * Maximum number of blocks simultaneously horizontal
     */
    private int maxX = 10;

    /**
     * Game state constructor
     */
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
        cubeCounter = 0;
        toSlide = false;
    }
    
    public GameState(Tetromino t) {
    	this.mode = 'C';
    	this.end = 'M';
    	this.curr = t;
    }

    public void incScore(float delta){
        score += delta;
    }
    
    public void clearObjectiveSMTests() {
    	this.objective = 0;
    }
    
    public void clearTimerTMTests() {
    	this.timer = 0f;
    }

    /**
     * Sets the game mode
     * @param mode
     */
    public void setMode(char mode){

        this.mode = mode;
        if (mode == 'E') {
            this.curr = genCube();
            this.next.clear();
            this.next.add(genTetromino('N'));
            this.curr2 = null;
        }
    }

    /**
     * Sets the end mode
     * @param endM
     */
    public void setEnd(char endM){
        switch (endM){
            case 'S':
                this.end = 'S';
                objective = 50;
                break;
            case 'T':
                this.end = 'T';
                this.timer = 120f;
                break;
            default:
                this.end = 'M';
        }
    }

    /**
     * Decrements the timer's value
     * @param deltaTime
     */
    public void decTimer(float deltaTime){
        timer -= deltaTime;

    }

    /**
     * Increments the timer's value
     * @param deltaTime
     */
    public void incTimer(float deltaTime){
        timer += deltaTime;
    }

    /**
     * Generates a power
     * @return char according to the power generated
     */
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

    /**
     * Generates a new tetromino
     * @param power
     * @return Tetromino that will be added to the next list
     */
    public Tetromino genTetromino(char power){
        Random randomno = new Random();
        int x = randomno.nextInt(7);
        if (this.mode == 'E') return genCube();
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

    public Tetromino genCube(){
        TetroO tetromino = new TetroO('N');
        tetromino.etmShift(cubeCounter);
        cubeCounter++;
        if (cubeCounter > 4) cubeCounter = 0;
        return tetromino;
    }

    /**
     * Generates a tetromino with a Kray-Z block
     * @param spec
     * @return Tetromino that will be added to the next list
     */

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

    /**
     * If possible, moves the current tetromino to the hold position, and if there is already a
     * tetromino there, that one is placed as the current one
     */
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

    /**
     * Sets the boolean that checks if a tetromino can be placed in the hold position to true
     */
    public void canHold(){
        this.canHold = true;
    }

    /**
     * Checks if a block can be dropped
     * @param a
     * @return True upon success and false otherwise
     */
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

    /**
     * Call the canDrop function with the current tetromino as the given argument
     * @return True upon success and false otherwise
     */
    public boolean canDrop(){
        if (canDrop(curr.getBlocks().get("A").getCoords()) &&
                canDrop(curr.getBlocks().get("B").getCoords()) &&
                canDrop(curr.getBlocks().get("C").getCoords()) &&
                canDrop(curr.getBlocks().get("D").getCoords())){
            return true;
        }

        return false;
    }

    /**
     * Locks the current tetromino in place after it can no longer go down
     */
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

    /**
     * Locks the current tetromino2 in place after it can no longer go down
     */
    public void lock2(){
        placed.add(curr2.getBlocks().get("A"));
        placed.add(curr2.getBlocks().get("B"));
        placed.add(curr2.getBlocks().get("C"));
        placed.add(curr2.getBlocks().get("D"));
        curr2 = genTetromino('N');
        if (this.next.size() == 0) {
            this.next.add(genTetromino(genPower()));
        }
        Collections.sort(placed); //Sort Block List
    }


    /**
     * Drops the current tetromino one coordinate if possible
     */
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

    /**
     * Drops the current tetromino2 one coordinate if possible
     */
    public void dropCurr2(){
        Coords ac = curr2.getBlocks().get("A").getCoords();
        Coords bc = curr2.getBlocks().get("B").getCoords();
        Coords cc = curr2.getBlocks().get("C").getCoords();
        Coords dc = curr2.getBlocks().get("D").getCoords();
        if (canDrop(ac) && canDrop(bc) && canDrop(cc) && canDrop(dc)) {
            curr2.getBlocks().get("A").getCoords().setCoords(ac.X() + 0, ac.Y() + 1);
            curr2.getBlocks().get("B").getCoords().setCoords(bc.X() + 0, bc.Y() + 1);
            curr2.getBlocks().get("C").getCoords().setCoords(cc.X() + 0, cc.Y() + 1);
            curr2.getBlocks().get("D").getCoords().setCoords(dc.X() + 0, dc.Y() + 1);
        } else {

            lines[(int)curr2.getBlocks().get("A").getCoords().Y()] += 1;
            lines[(int)curr2.getBlocks().get("B").getCoords().Y()] += 1;
            lines[(int)curr2.getBlocks().get("C").getCoords().Y()] += 1;
            lines[(int)curr2.getBlocks().get("D").getCoords().Y()] += 1;
            lock2();
        }

    }

    /**
     * Effect that alters the next list to the same specific tetromino
     * @param next
     */
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

    /**
     * Handles the end of an effect
     */
    public void clearEffect(){
        this.activeEffect = 'N';
        this.effectTimer = 0f;
    }

    /**
     * Gets the effect that is currently active
     * @return
     */
    public char getActiveEffect(){
        return activeEffect;
    }

    /**
     * Checks if a placed block can slide left for the effect
     * @param b
     * @return True upon success and false otherwise
     */
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

    /**
     * This function handles the shift effect
     */
    public void shiftPlacedLeft(){
        for (Block b: placed) {
            while (canSlideLeft(b)) {
                b.setCoords(b.getCoords().X() - 1, b.getCoords().Y());
            }
        }
        toSlide = false;
    }

    /**
     * Activates a power
     * @param power
     */
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

    /**
     * Places the current tetromino in the corresponding place it is at that moment
     */
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


    /**
     * Checks if it is valid for the current tetromino to shift left
     * @return True upon success and false otherwise
     */
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

    /**
     * If it is valid, the current block will shift left
     */
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

    /**
     * Checks if it is valid for the current tetromino to shift right
     * @return True upon success and false otherwise
     */
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
    /**
     * If it is valid, the current block will shift right
     */
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

    /**
     * Gets the current tetromino
     * @return
     */
    public Tetromino getCurr(){
        return this.curr;
    }

    /**
     * If the top is reached, the game ends
     * @return True if the game has reached an end situation (keeping in mind the different end modes)
     */
    public boolean defeat(){
        for(Block b: placed){
            if(b.getCoords().Y() == 0) return true;
        }

        if (end == 'T' && timer <= 0f) return true;
        if (end == 'S' && objective <= 0) return true;

        return false;
    }

    /**
     * Gets the list of placed blocks
     * @return LinkedList<Block>
     */
    public LinkedList<Block> getPlaced(){
        return this.placed;
    }

    /**
     * Adds a block to the placed list
     * @param b
     */
    public void setPlaced(LinkedList<Block> b){
        this.placed = b;
    }

    /**
     * Sets the lines array to the array passed
     * @param nl
     */
    public void setLines(int[] nl){
        lines = nl;
    }

    /**
     * Increments the score according to the number of lines destroyed simultaneously
     * @param value
     */
    public void incrementScore(int value){
        this.score += value;
        if(this.score > 999999999){
            this.score = 999999999;
        }
    }

    /**
     * Gets the current score
     * @return score
     */
    public int getScore(){
        return this.score;
    }

    /**
     * Gets the next tetromino
     * @return Fist Tetromino of the next list
     */
    public Tetromino getNextTetromino(){
        return next.get(0);
    }

    /**
     * Gets the tetromino is the hold position
     * @return Tetromino in hold
     */
    public Tetromino getHold() {
        return hold;
    }

    /**
     * Gets the timer
     * @return timer
     */
    public float getTimer(){
        return timer;
    }


    /**
     * Find the line with the higher block
     * @return index of the line
     */
    public int findLine(){


        for(int i = 0; i < 15; i++){

            if(lines[i] == 10){
                return i;
            }
        }
        return -1;
    }

    /**
     * GEts to slide
     * @return toSlide
     */
    public  boolean getToSlide(){
        return toSlide;
    }

    /**
     * Function that handles the destruction of a full line
     * @return -1 if there are no lines to delete
     */
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

    /**
     * Handles the point given to the act of deleting lines
     */
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
    
    public void setCurr(Tetromino t) {
    	this.curr = t;
    }

    public void setCurr2(Tetromino t){ this.curr2 = t;}

    public Tetromino getCurr2(){
        return this.curr2;
    }
}
