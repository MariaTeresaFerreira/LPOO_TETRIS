package com.mygdx.mainpackage;

import java.util.HashMap;
import java.util.LinkedList;

public abstract class Tetromino {

    protected HashMap<String, Block> startingPos = new HashMap<String, Block>();
    protected HashMap<String, Block> blocks = new HashMap<String, Block>();
    protected char shape;
    protected Integer dir;

    public Tetromino(char s){
      this.shape = s;
      this.dir = 0;

    }

    public void putBlocks(HashMap<String, Block> map, Block a, Block b, Block c, Block d){
        map.put("A", a);
        map.put("B", b);
        map.put("C", c);
        map.put("D", d);
    }

    public String toString(){
        String s = "";
        s += this.shape + "\n";
        s += this.blocks.get("A").toString() + "\n";
        s += this.blocks.get("B").toString() + "\n";
        s += this.blocks.get("C").toString() + "\n";
        s += this.blocks.get("D").toString() + "\n";
        s += this.dir;
        return s;
    }

    public HashMap<String, Block> getBlocks(){
        return blocks;
    }

    public String getShape(){
        return String.valueOf(shape);
    }

    public void printCoords(){
        System.out.println(this.blocks.get("A").getCoords());
        System.out.println(this.blocks.get("B").getCoords());
        System.out.println(this.blocks.get("C").getCoords());
        System.out.println(this.blocks.get("D").getCoords());
    }

    public boolean validRotation(LinkedList<Block> placed, Coords a, Coords b, Coords c, Coords d){
        for (Block e: placed){
            if (e.getCoords().equals(a) || e.getCoords().equals(b) || e.getCoords().equals(c) || e.getCoords().equals(d)) {
                return false;
            }
        }

        if (a.X() < 0 || a.X() > 9 || a.Y() > 14) return false;
        if (b.X() < 0 || b.X() > 9 || b.Y() > 14) return false;
        if (c.X() < 0 || c.X() > 9 || c.Y() > 14) return false;
        if (d.X() < 0 || d.X() > 9 || d.Y() > 14) return false;

        return true;
    }

    public boolean srWorks(LinkedList<Block> placed, Coords a, Coords b, Coords c, Coords d){
        Coords na = new Coords(a.X() + 1, a.Y());
        Coords nb = new Coords(b.X() + 1, b.Y());
        Coords nc = new Coords(c.X() + 1, c.Y());
        Coords nd = new Coords(d.X() + 1, d.Y());

        if (validRotation(placed, na, nb, nc, nd)){
            a.setCoords(na);
            b.setCoords(nb);
            c.setCoords(nc);
            d.setCoords(nd);
            return true;
        }

        return false;
    }

    public boolean slWorks(LinkedList<Block> placed, Coords a, Coords b, Coords c, Coords d){
        Coords na = new Coords(a.X() - 1, a.Y());
        Coords nb = new Coords(b.X() - 1, b.Y());
        Coords nc = new Coords(c.X() - 1, c.Y());
        Coords nd = new Coords(d.X() - 1, d.Y());

        if (validRotation(placed, na, nb, nc, nd)){
            a.setCoords(na);
            b.setCoords(nb);
            c.setCoords(nc);
            d.setCoords(nd);
            return true;
        }

        return false;

    }

    public void checkAndSet(LinkedList<Block> placed, Coords a, Coords b, Coords c, Coords d){

        if (validRotation(placed, a, b, c, d) || slWorks(placed, a, b, c, d) || srWorks(placed, a, b, c, d)){
            this.blocks.get("A").setCoords(a);
            this.blocks.get("B").setCoords(b);
            this.blocks.get("C").setCoords(c);
            this.blocks.get("D").setCoords(d);
            this.dir += 1;
            if (this.dir == 4) this.dir = 0;
        }
    }

    public abstract void rotate(LinkedList<Block> placed);
}
