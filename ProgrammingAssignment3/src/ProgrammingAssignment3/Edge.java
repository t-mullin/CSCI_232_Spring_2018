/**
 * Authors: Tristan Mullin
 * Date: 4/10/2018
 * Overview: This program reads in a csv file and creates a graph from an adjacency list. Algorithms will be applied on the graph to find the MST and the Transitive closure
 * Special instructions/Notes: Non-connecting edges in the file inputs must be inputted as "INF" (case-insensitive) or "∞" (infinity symbol).  Each input file for each algorithm's input file is named for each algorithm (prim.csv, kruskal.csv, and floyd.csv).
 */
package ProgrammingAssignment3;

public class Edge implements Comparable<Edge>{
    //variables
    private Vertex origin;
    private Vertex destination;
    private int weight;
    private boolean directed;

    //constructor for creating a new edge with a neighbor node and the edges weight
    public Edge(Vertex origin, Vertex destination, int weight, boolean directed) {
        this.origin = origin;
        this.destination = destination;
        this.weight = weight;
        this.directed = directed;
    }

    //returns the origin vertex
    public Vertex getOrigin() {
        return origin;
    }

    //returns the destination vertex
    public Vertex getDestination() {
        return destination;
    }

    //returns the edges weight
    public int getWeight() {
        return weight;
    }

    //returns if the edge is directed
    public boolean isDirected() {
        return directed;
    }

    //prints out the neighbor and the weight of the edge
    public void printEdge() {
        System.out.print("(" + origin.getVertexLabel() + destination.getVertexLabel() + ", " + weight + ") ");
    }

    //makes the edges comparable so they can be inserted into a priority queue based on their weight value
    @Override
    public int compareTo(Edge o) {
        return Integer.compare(weight, o.weight);
    }
}
