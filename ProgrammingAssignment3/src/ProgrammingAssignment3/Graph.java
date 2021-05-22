/**
 * Authors: Tristan Mullin
 * Date: 4/10/2018
 * Overview: This program reads in a csv file and creates a graph from an adjacency list. Algorithms will be applied on the graph to find the MST and the Transitive closure
 * Special instructions/Notes: Non-connecting edges in the file inputs must be inputted as "INF" (case-insensitive) or "∞" (infinity symbol).  Each input file for each algorithm's input file is named for each algorithm (prim.csv, kruskal.csv, and floyd.csv).
 */
package ProgrammingAssignment3;

import java.util.ArrayList;

public class Graph {
    //variables
    private String[] vertexArray;
    private Integer[][] adjacencyMatrix;
    private Vertex[] vertices;
    private ArrayList<Edge> edgeList;

    //constructor for creating a new graph from a list of vertices and an adjacency matrix
    public Graph(String[] vertexArray, Integer[][] adjacencyMatrix) {
        this.vertexArray = vertexArray;
        this.adjacencyMatrix = adjacencyMatrix;
        this.edgeList = new ArrayList<Edge>();
    }

    //builds a graph
    public void buildGraph() {
        buildVertices();
        buildEdges();
    }

    //builds an array of Vertex objects from the vertex String array
    private void buildVertices() {
        vertices = new Vertex[vertexArray.length];
        for(int i = 0; i < vertexArray.length; i++) {
            vertices[i] = new Vertex(vertexArray[i]);
        }
    }

    //builds a list of edges
    private void buildEdges() {
        Edge tempEdge;
        boolean duplicate = false;
        for(int i = 0; i < vertices.length; i++) {
            for(int j = 0; j < vertices.length; j++) {
                if(adjacencyMatrix[j][i] != Integer.MAX_VALUE) {
                    if(adjacencyMatrix[j][i] == adjacencyMatrix[i][j]) {
                        tempEdge = new Edge(vertices[i],vertices[j], adjacencyMatrix[j][i], false);
                    } else {
                        tempEdge = new Edge(vertices[i],vertices[j], adjacencyMatrix[j][i],true);
                    }
                    if(edgeList.isEmpty()) {
                        edgeList.add(tempEdge);
                    } else {
                        for (Edge e : edgeList) {
                            if((e.getOrigin() == tempEdge.getDestination())&&(e.getDestination() == tempEdge.getOrigin())&&(e.getWeight() == tempEdge.getWeight())) {
                                duplicate = true;
                            }
                        }
                        if(!duplicate) {
                            edgeList.add(tempEdge);
                        }
                        duplicate = false;
                    }
                }
            }
        }
    }

    //returns the vertices
    public Vertex[] getVertices() {
        return vertices;
    }

    //returns the edge list
    public ArrayList<Edge> getEdgeList() {
        return edgeList;
    }

    //returns the adjacency matrix
    public Integer[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    //prints the vertices used for testing
    public void printVertices() {
        for(int i = 0; i < vertices.length; i++) {
            vertices[i].printVertex();
        }
    }

    //prints the edge list used for testing
    public void printEdges() {
        for (Edge  e: edgeList) {
            e.printEdge();
        }
    }
}
