package creatures;

import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import static huglife.HugLifeUtils.randomEntry;
import static huglife.HugLifeUtils.randomInt;

public class Clorus extends Creature {
    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    private final double energyMin = 0;
    /**
     * creates Clorus with energy equal to E.
     */
    public Clorus(double e) {
        super("Clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    public String Name(){
        return this.name();
    }
    /**
     * creates a Clorus with energy equal to 1.
     */
    public Clorus() {
        this(1);
    }

    /**
     * Should return a color with red = 99, blue = 76, and green that varies
     * linearly based on the energy of the Clorus. If the Clorus has zero energy,
     * it should have a green value of 63. If it has max energy, it should
     * have a green value of 255. The green value should vary with energy
     * linearly in between these two extremes. It's not absolutely vital
     * that you get this exactly correct.
     */
    public Color color() {
        return color(r, g, b);
    }

    /**
     * Do nothing with C, Plips are pacifists.
     */
    public void attack(Creature c) {
        energy += c.energy();
    }

    /**
     * Plips should lose 0.15 units of energy when moving. If you want to
     * to avoid the magic number warning, you'll need to make a
     * private static final variable. This is not required for this lab.
     */
    public void move() {
        energy -= 0.03;
    }


    /**
     * Plips gain 0.2 energy when staying due to photosynthesis.
     */
    public void stay() {
        energy -= 0.01;
    }

    /**
     * Plips and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Clorus.
     */
    public Clorus replicate() {
        Clorus offspring = new Clorus(this.energy/2);
        energy /= 2;
        return offspring;
    }

    /**
     * Cloruses should obey exactly the following behavioral rules:
     * 1. If there are no empty squares, the Clorus will STAY (even if there
     * are Plips nearby they could attack since plip squares do not count as empty squares).
     * 2. Otherwise, if any Plips are seen, the Clorus will ATTACK one of them randomly.
     * 3. Otherwise, if the Clorus has energy greater than or equal to one, it will
     * REPLICATE to a random empty square.
     * 4. Otherwise, the Clorus will MOVE to a random empty square.
     */

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<> ();
        Deque<Direction> neighborPilps = new ArrayDeque<>();
        for(Direction i : neighbors.keySet()){
            if(neighbors.get(i).name().equals("empty")){
                emptyNeighbors.add(i);
            }else if(neighbors.get(i).name().equals("plip")){
                neighborPilps.add(i);
            }
        }

        Action action;

        // Rule 1: If there are no empty squares, the Clorus will STAY
        // (even if there are Plips nearby they could attack since plip
        // squares do not count as empty squares).
        if (emptyNeighbors.size() == 0) {
            return new Action(Action.ActionType.STAY);
        }

        // Rule 2 if any Plips are seen, the Clorus will ATTACK one of them randomly.
        if (neighborPilps.size() != 0){

            Direction dir = randomEntry(neighborPilps);
            return new Action(Action.ActionType.ATTACK,dir);

        }

        //Rule 3 if the Clorus has energy greater than or equal to one, it will
        //REPLICATE to a random empty square.

        if(energy >= 1.0){
            Clorus os = this.replicate();
            Direction dir = randomEntry(emptyNeighbors);
            return new Action(Action.ActionType.REPLICATE,dir);
        }

        // Rule 4
        Direction dir = randomEntry(emptyNeighbors);
        return new Action(Action.ActionType.MOVE,dir);
    }

}
