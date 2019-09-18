package creatures;

import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import static huglife.HugLifeUtils.randomEntry;
import static huglife.HugLifeUtils.randomInt;

/**
 * An implementation of a motile pacifist photosynthesizer.
 *
 * @author Josh Hug
 */
public class Plip extends Creature {

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

    private final double energyMax = 2;
    private final double energyMin = 0;
    /**
     * creates plip with energy equal to E.
     */
    public Plip(double e) {
        super("plip");
        r = 99;
        g = 0;
        b = 76;
        energy = e;
    }

    public String Name(){
        return this.name();
    }
    /**
     * creates a plip with energy equal to 1.
     */
    public Plip() {
        this(1);
    }

    /**
     * Should return a color with red = 99, blue = 76, and green that varies
     * linearly based on the energy of the Plip. If the plip has zero energy,
     * it should have a green value of 63. If it has max energy, it should
     * have a green value of 255. The green value should vary with energy
     * linearly in between these two extremes. It's not absolutely vital
     * that you get this exactly correct.
     */
    public Color color() {
        g = (int) (96*energy+63);
        return color(r, g, b);
    }

    /**
     * Do nothing with C, Plips are pacifists.
     */
    public void attack(Creature c) {
        // do nothing.
    }

    /**
     * Plips should lose 0.15 units of energy when moving. If you want to
     * to avoid the magic number warning, you'll need to make a
     * private static final variable. This is not required for this lab.
     */
    public void move() {
        energy -= 0.15;
    }


    /**
     * Plips gain 0.2 energy when staying due to photosynthesis.
     */
    public void stay() {
        energy += 0.2;
        if(energy > energyMax){
            energy = energyMax;
        }
    }

    /**
     * Plips and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Plip.
     */
    public Plip replicate() {
        Plip offspring = new Plip(this.energy/2);
        energy /= 2;
        return offspring;
    }

    /**
     * Plips take exactly the following actions based on NEIGHBORS:
     * 1. If no empty adjacent spaces, STAY.
     * 2. Otherwise, if energy >= 1, REPLICATE towards an empty direction
     * chosen at random.
     * 3. Otherwise, if any Cloruses, MOVE with 50% probability,
     * towards an empty direction chosen at random.
     * 4. Otherwise, if nothing else, STAY
     * <p>
     * Returns an object of type Action. See Action.java for the
     * scoop on how Actions work. See SampleCreature.chooseAction()
     * for an example to follow.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<> ();
        boolean anyClorus = false;
        for(Direction i : neighbors.keySet()){
            if(neighbors.get(i).name().equals("empty")){
                emptyNeighbors.add(i);
            }else if(neighbors.get(i).name().equals("Clorus")){
                anyClorus = true;
            }
        }

        Action action;

        // Rule 1: if there is no empty space, stay;
        if (emptyNeighbors.size() == 0) {
            action = new Action(Action.ActionType.STAY);
            return action;
        }

        // Rule 2 if the Plip has energy greater than or equal to 1.0,
        // it should replicate to an available space.
        if(energy >= 1.0){
            Plip os = this.replicate();
            Direction dir = randomEntry(emptyNeighbors);
            return new Action(Action.ActionType.REPLICATE,dir);
        }

        // Rule 3 if it sees a neighbor with name() equal to “clorus”, it will
        // move to any available empty square with probability 50%. It should
        // choose the empty square randomly. As an example, if it sees a Clorus
        // to the BOTTOM, and “empty” to the TOP , LEFT, and RIGHT, there is a
        // 50% chance it will move (due to fear of Cloruses), and if it does move,
        // it will pick uniformly at random between TOP, LEFT, and RIGHT.
        if(anyClorus){
            Direction dir = randomEntry(emptyNeighbors);
            if(randomInt(100) > 50){
                return new Action(Action.ActionType.MOVE,dir);

            }
        }

        // Rule 4
        return new Action(Action.ActionType.STAY);
    }
}
