package com.mygdx.mainpackage.Tests;
/*
import static org.junit.Assert.*;
import com.mygdx.mainpackage.*;
import org.junit.Test;

public class TetroSTests {

	@Test
	public void testS() {
		Tetromino t = new TetroS('N');
		GameState g = new GameState(t);
		Coords a0 = new Coords(t.getBlocks().get("A").getCoords());
		Coords b0 = new Coords(t.getBlocks().get("B").getCoords());
		Coords c0 = new Coords(t.getBlocks().get("C").getCoords());
		Coords d0 = new Coords(t.getBlocks().get("D").getCoords());
		g.getCurr().rotate(g.getPlaced());
		
		Coords a1 = g.getCurr().getBlocks().get("A").getCoords();
		Coords b1 = g.getCurr().getBlocks().get("B").getCoords();
		Coords c1 = g.getCurr().getBlocks().get("C").getCoords();
		Coords d1 = g.getCurr().getBlocks().get("D").getCoords();
		
		/*First Rotation*/
/*		assertEquals(new Coords(a0.X() + 0, a0.Y() + 2), a1);
		assertEquals(new Coords(b0.X() + 1, b0.Y() + 1), b1);
		assertEquals(c0, c1);
		assertEquals(new Coords(d0.X() + 1, d0.Y() - 1), d1);
		
		a0 = new Coords(a1); b0 = new Coords(b1); c0 = new Coords(c1); d0 = new Coords(d1);
		g.getCurr().rotate(g.getPlaced());
		/*Second Rotation*/
/*		assertEquals(new Coords(a0.X() - 2, a0.Y() + 0), a1);
		assertEquals(new Coords(b0.X() - 1, b0.Y() + 1), b1);
		assertEquals(c0, c1);
		assertEquals(new Coords(d0.X() + 1, d0.Y() + 1), d1);
		
		a0 = new Coords(a1); b0 = new Coords(b1); c0 = new Coords(c1); d0 = new Coords(d1);
		g.getCurr().rotate(g.getPlaced());
		/*Third Rotation*/
/*		assertEquals(new Coords(a0.X() - 0, a0.Y() - 2), a1);
		assertEquals(new Coords(b0.X() - 1, b0.Y() - 1), b1);
		assertEquals(c0, c1);
		assertEquals(new Coords(d0.X() - 1, d0.Y() + 1), d1);
		
		a0 = new Coords(a1); b0 = new Coords(b1); c0 = new Coords(c1); d0 = new Coords(d1);
		g.getCurr().rotate(g.getPlaced());
		/*Fourth Rotation*/
/*		assertEquals(new Coords(a0.X() + 2, a0.Y() - 0), a1);
		assertEquals(new Coords(b0.X() + 1, b0.Y() - 1), b1);
		assertEquals(c0, c1);
		assertEquals(new Coords(d0.X() - 1, d0.Y() - 1), d1);
	}


}
*/