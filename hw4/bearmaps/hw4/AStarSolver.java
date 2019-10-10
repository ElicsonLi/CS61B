package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;

import java.util.*;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {

    AStarGraph<Vertex> input;
    Vertex end;
    Vertex start;
    private SolverOutcome outcome;
    private int numStatesExplored;
    private double totalTime;
    private LinkedList<Vertex> solution;
    private ArrayHeapMinPQ<Vertex> PQ;
    private HashMap<Vertex,Double> distanceTo;
    private HashMap<Vertex,Vertex> edgeTo;



    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        this.start = start;
        this.end = end;
        solution = new LinkedList<>();
        this.input = input;
        distanceTo = new HashMap<Vertex, Double>();
        edgeTo = new HashMap<Vertex, Vertex>();
        numStatesExplored = 0;
        PQ = new ArrayHeapMinPQ<>();
        PQ.add(start, input.estimatedDistanceToGoal(start, end));
        distanceTo.put(start,0.0);
        
        long starttime = System.currentTimeMillis();

        while ((PQ.size() != 0) && (!PQ.getSmallest().equals(end)) && (outcome != SolverOutcome.TIMEOUT)){
            Vertex p = PQ.removeSmallest();
            numStatesExplored ++;
            long endtime = System.currentTimeMillis();
            totalTime = (endtime - starttime)/1000.0;
            if(totalTime > timeout){
                outcome = SolverOutcome.TIMEOUT;
            }
            relax(p);
        }
        if(outcome != SolverOutcome.TIMEOUT) {
            if(PQ.size() == 0){
                outcome = SolverOutcome.UNSOLVABLE;
            }else if (PQ.getSmallest().equals(end)) {
                outcome = SolverOutcome.SOLVED;
                Vertex v = end;
                while (!v.equals(start)){
                    solution.addFirst(v);
                    v = edgeTo.get(v);
                }
                solution.addFirst(start);
            } else {
                outcome = SolverOutcome.UNSOLVABLE;
            }
        }
    }


    private void relax(Vertex v){
        List<WeightedEdge<Vertex>> edges = input.neighbors(v);
        for(WeightedEdge<Vertex> e : edges){
            Vertex p = e.from();
            Vertex q = e.to();
            double w = e.weight();
            if((distanceTo.get(q) == null) || (distanceTo.get(p) + w < distanceTo.get(q))){
                distanceTo.put(q,distanceTo.get(p) + w);
                edgeTo.put(q,p);
                if(PQ.contains(q)){
                    PQ.changePriority(q,distanceTo.get(q) + input.estimatedDistanceToGoal(q,end));
                }else {
                    PQ.add(q,distanceTo.get(q) + input.estimatedDistanceToGoal(q,end));
                }
            }
        }

    }

    @Override
    public SolverOutcome outcome(){
        return outcome;

    }

    @Override
    public List<Vertex> solution(){
        return solution;
    }

    @Override
    public double solutionWeight(){
        if(outcome == SolverOutcome.SOLVED){
            return distanceTo.get(end);
        }
        return 0;


    }

    @Override
    public int numStatesExplored(){
        return numStatesExplored;
    }

    @Override
    public double explorationTime(){
        return totalTime;
    }

}
