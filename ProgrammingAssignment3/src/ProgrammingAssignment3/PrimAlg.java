/**
 * Authors: Tristan Mullin
 * Date: 4/10/2018
 * Overview: This program reads in a csv file and creates a graph from an adjacency list. Algorithms will be applied on the graph to find the MST and the Transitive closure
 * Special instructions/Notes: Non-connecting edges in the file inputs must be inputted as "INF" (case-insensitive) or "∞" (infinity symbol).  Each input file for each algorithm's input file is named for each algorithm (prim.csv, kruskal.csv, and floyd.csv).
 */
package ProgrammingAssignment3;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

public class PrimAlg {
    //variables
    private Graph graph;

    //constructor for creating a new instance of the primAlg class with a graph
    public PrimAlg(Graph graph) {
        this.graph = graph;
    }

    //returns the MST of the graph as an array list of edges
    public ArrayList<Edge> primMST() {
        Vertex[] temp = graph.getVertices();
        ArrayList<Edge> edgeArrayList = new ArrayList<Edge>();
        PriorityQueue<Vertex> vertexPriorityQueue = new PriorityQueue<Vertex>();
        Random random = new Random();
        int r = random.nextInt(temp.length);

        for (Vertex e : temp) {
            e.setKey(Integer.MAX_VALUE);
            e.setParent(null);
        }
        temp[r].setKey(0);
        for (Vertex e: temp) {
            vertexPriorityQueue.add(e);
        }
        while(!vertexPriorityQueue.isEmpty()) {
            Vertex vertex = vertexPriorityQueue.remove();
            ArrayList<Vertex> adjVertices = findAdj(vertex);
            if(vertex.getParent() != null) {
                edgeArrayList.add(connectingEdge(vertex, vertex.getParent()));
            }
            for (Vertex e : adjVertices) {
                int weight = connectingEdge(vertex, e).getWeight();
                if((vertexPriorityQueue.contains(e)) && (weight < e.getKey())){
                    e.setParent(vertex);
                    e.setKey(weight);
                }
            }
            PriorityQueue<Vertex> tempQueue = new PriorityQueue<Vertex>();
            for (Vertex e : vertexPriorityQueue) {
                tempQueue.add(e);
            }
            vertexPriorityQueue = tempQueue;
        }
        return edgeArrayList;
    }

    //returns the adjacent vertices of a specified vertex
    private ArrayList<Vertex> findAdj(Vertex vertex) {
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        for (Edge e : graph.getEdgeList()) {
            if(e.getOrigin().getVertexLabel().equals(vertex.getVertexLabel())) {
                vertices.add(e.getDestination());
            }
            if(e.getDestination().getVertexLabel().equals(vertex.getVertexLabel())) {
                vertices.add(e.getOrigin());
            }
        }
        return vertices;
    }

    //returns the connecting edge between two vertices
    private Edge connectingEdge(Vertex u, Vertex v) {
        Edge connection = null;
        for (Edge e : graph.getEdgeList()) {
            if(((e.getOrigin().getVertexLabel().equals(u.getVertexLabel())) || (e.getDestination().getVertexLabel().equals(u.getVertexLabel()))) && ((e.getOrigin().getVertexLabel().equals(v.getVertexLabel())) || (e.getDestination().getVertexLabel().equals(v.getVertexLabel())))) {
               connection = e;
            }
        }
        return connection;
    }
}
