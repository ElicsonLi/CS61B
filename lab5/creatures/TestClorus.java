package creatures;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

/** Tests the plip class
 *  @authr EthanL
 */

public class TestClorus {

    @Test
    public void testBasics() {
        Clorus c = new Clorus(2);
        assertEquals(2, c.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), c.color());
        c.move();
        assertEquals(1.97, c.energy(), 0.01);
        c.move();
        assertEquals(1.94, c.energy(), 0.01);
        c.stay();
        assertEquals(1.93, c.energy(), 0.01);
        c.stay();
        assertEquals(1.92, c.energy(), 0.01);
    }

    @Test
    public void testReplicate() {
        Clorus c = new Clorus(2);
        assertEquals(2, c.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), c.color());
        c.move();
        assertEquals(1.97, c.energy(), 0.01);
        Clorus os = c.replicate();
        assertNotEquals(os, c);
        assertEquals(1.97/2, c.energy(), 0.01);
        assertEquals(1.97/2, os.energy(), 0.01);
    }

    @Test
    public void testAttack() {
        Clorus c = new Clorus(1);
        Plip p = new Plip(1.5);
        c.attack(p);
        assertEquals(2.5, c.energy(), 0.01);
    }

    @Test
    public void testChoose() {

        // No empty adjacent spaces; stay.
        Clorus c = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);


        // Have pilps, Attack.
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> topPilps = new HashMap<Direction, Occupant>();
        topPilps.put(Direction.TOP, new Plip(1.5));
        topPilps.put(Direction.BOTTOM, new Empty());
        topPilps.put(Direction.LEFT, new Impassible());
        topPilps.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(topPilps);
        expected = new Action(Action.ActionType.ATTACK, Direction.TOP);


        assertEquals(expected, actual);


        // Energy >= 1; replicate towards an empty space.
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> BottomEmpty = new HashMap<Direction, Occupant>();
        BottomEmpty.put(Direction.TOP, new Impassible());
        BottomEmpty.put(Direction.BOTTOM, new Empty());
        BottomEmpty.put(Direction.LEFT, new Impassible());
        BottomEmpty.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(BottomEmpty);
        expected = new Action(Action.ActionType.REPLICATE,Direction.BOTTOM);

        assertEquals(expected, actual);
        assertEquals(0.6, c.energy(),0.01);


        // Energy < 1; move.
        c = new Clorus(0.5);
        HashMap<Direction, Occupant> topEmpty = new HashMap<Direction, Occupant>();
        topEmpty.put(Direction.TOP, new Empty());
        topEmpty.put(Direction.BOTTOM, new Impassible());
        topEmpty.put(Direction.LEFT, new Impassible());
        topEmpty.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.MOVE, Direction.TOP);

        assertEquals(expected, actual);



        // We don't have Cloruses yet, so we can't test behavior for when they are nearby right now.
    }
}
