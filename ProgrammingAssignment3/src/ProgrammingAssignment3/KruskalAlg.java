/**
 * Authors: Tristan Mullin
 * Date: 4/10/2018
 * Overview: This program reads in a csv file and creates a graph from an adjacency list. Algorithms will be applied on the graph to find the MST and the Transitive closure
 * Special instructions/Notes: Non-connecting edges in the file inputs must be inputted as "INF" (case-insensitive) or "∞" (infinity symbol).  Each input file for each algorithm's input file is named for each algorithm (prim.csv, kruskal.csv, and floyd.csv).
 */
package ProgrammingAssignment3;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class KruskalAlg {

    //variables
    private Graph graph;

    //constructor for creating a new instance of the KruskalAlg with a graph
    public KruskalAlg(Graph graph) {
        this.graph = graph;
    }

    //returns the MST of the graph as an array list of edges
    public ArrayList<Edge> kruskalMST() {
        ArrayList<Edge> edgeArrayList = new ArrayList<Edge>();
        PriorityQueue<Edge> edgePriorityQueue = new PriorityQueue<Edge>();
        ArrayList<ArrayList<Vertex>> forest = new ArrayList<ArrayList<Vertex>>();
        for (int i = 0; i < graph.getVertices().length; i++) {
            ArrayList<Vertex> sapling = new ArrayList<>();
            sapling.add(graph.getVertices()[i]);
            forest.add(i, sapling);
        }

        for (Edge e : graph.getEdgeList()) {
            edgePriorityQueue.add(e);
        }

        while(edgeArrayList.size() < graph.getVertices().length - 1) {
            Edge minEdge = edgePriorityQueue.remove();
            int i = findSapling(forest, minEdge.getOrigin());
            int j = findSapling(forest , minEdge.getDestination());
            if(i != j) {
                edgeArrayList.add(minEdge);
                forest.get(i).addAll(forest.get(j));
                forest.remove(j);
                forest.trimToSize();
            }
        }

        return edgeArrayList;
    }

    //finds where a sapling is in forest and returns the saplings index
    private int findSapling(ArrayList<ArrayList<Vertex>> forest, Vertex vertex) {
        int index = 0;
        for(int i = 0; i < forest.size(); i++) {
            if(forest.get(i).contains(vertex)) {
                index = i;
            }
        }
        return index;
    }
}
