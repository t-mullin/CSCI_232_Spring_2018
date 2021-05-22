/**
 * Authors: Tristan Mullin
 * Date: 4/10/2018
 * Overview: This program reads in a csv file and creates a graph from an adjacency list. Algorithms will be applied on the graph to find the MST and the Transitive closure
 * Special instructions/Notes: Non-connecting edges in the file inputs must be inputted as "INF" (case-insensitive) or "∞" (infinity symbol).  Each input file for each algorithm's input file is named for each algorithm (prim.csv, kruskal.csv, and floyd.csv).
 */
package ProgrammingAssignment3;

public class Vertex implements Comparable<Vertex> {
    //variables
    private String label;
    private int key;
    private Vertex parent;
    private boolean visited;

    //constructor for creating a new vertex with a given label
    public Vertex(String label) {
        this.label = label;
        this.visited = false;
    }

    //returns the vertices' label
    public String getVertexLabel() {
        return label;
    }

    //sets the vertices' key value
    public void setKey(int key) {
        this.key = key;
    }

    //sets the vertices' parent
    public void setParent(Vertex parent) {
        this.parent = parent;
    }

    //returns the vertices key
    public int getKey() {
        return key;
    }

    //returns the vertices parent
    public Vertex getParent() {
        return parent;
    }

    //prints the vertex and the edgeList
    public void printVertex() {
        System.out.println("Vertex: " + label);
        System.out.println();
    }

    //prints the vertex key pair for testing
    public void printKey() {
        System.out.println("Vertex: " + label + " Key: " + key);
    }

    //makes the vertices comparable so they can be inserted into a priority queue based on their key value
    @Override
    public int compareTo(Vertex o) {
        return Integer.compare(key, o.key);
    }
}
