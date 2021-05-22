/**
 * Authors: Tristan Mullin
 * Date: 4/10/2018
 * Overview: This program reads in a csv file and creates a graph from an adjacency list. Algorithms will be applied on the graph to find the MST and the Transitive closure
 * Special instructions/Notes: Non-connecting edges in the file inputs must be inputted as "INF" (case-insensitive) or "∞" (infinity symbol).  Each input file for each algorithm's input file is named for each algorithm (prim.csv, kruskal.csv, and floyd.csv).
 */
package ProgrammingAssignment3;

public class FloydAlg {
    //variables
    private Graph graph;
    private Integer[][] adjacencyMatrix;

    //constructor for creating a new instance of the FloydAlg with a graph
    public FloydAlg(Graph graph) {
        this.graph = graph;
    }

    //builds the all pairs shortest path matrix
    public void floydSP() {
        adjacencyMatrix = graph.getAdjacencyMatrix();

        for(int i = 0; i < graph.getVertices().length; i++) {
            adjacencyMatrix[i][i] = 0;
        }
        System.out.println("The initial matrix:");
        printMatrix();
        System.out.println();
        for(int k = 0; k < graph.getVertices().length; k++) {
            for(int i = 0; i < graph.getVertices().length; i++) {
                for(int j = 0; j < graph.getVertices().length; j++) {
                    int a = adjacencyMatrix[i][j];
                    int b = adjacencyMatrix[i][k];
                    int c = adjacencyMatrix[k][j];
                    if(Integer.compareUnsigned(a, (b+c)) > 0) {
                        adjacencyMatrix[i][j] = b+c;
                        printMatrix();
                        System.out.println();
                    }
                }
            }
        }
        System.out.println("The final matrix:");
        printMatrix();
        System.out.println();
    }

    //prints out the matrix with the rows and columns labeled
    private void printMatrix() {
        for(int i = 0; i < graph.getVertices().length; i++) {
            System.out.print("  " + graph.getVertices()[i].getVertexLabel());
        }
        System.out.println();
        for(int i = 0; i < graph.getVertices().length; i++) {
            System.out.print(graph.getVertices()[i].getVertexLabel() + " ");
            for(int j = 0; j < graph.getVertices().length; j++) {
                if(adjacencyMatrix[i][j] == Integer.MAX_VALUE) {
                    System.out.print("∞ ");
                } else {
                    System.out.print(adjacencyMatrix[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
