/**
 * Authors: Tristan Mullin
 * Date: 4/10/2018
 * Overview: This program reads in a csv file and creates a graph from an adjacency list. Algorithms will be applied on the graph to find the MST and the Transitive closure
 * Special instructions/Notes: Non-connecting edges in the file inputs must be inputted as "INF" (case-insensitive) or "∞" (infinity symbol).  Each input file for each algorithm's input file is named for each algorithm (prim.csv, kruskal.csv, and floyd.csv).
 */
package ProgrammingAssignment3;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int choice = 5;

        //Menu system
        while(choice != 0) {
            System.out.println("Enter 1 for Prim's Algorithm.");
            System.out.println("Enter 2 for Kruskal's Algorithm.");
            System.out.println("Enter 3 for Floyd-Warshall's Algorithm.");
            System.out.println("Enter 0 to exit.");

            System.out.print("Please enter your choice >> ");
            choice = in.nextInt();
            System.out.println();

            switch(choice){
                case 0:
                    System.out.println("Exiting...");
                    break;
                case 1:
                    System.out.print("Prim's MST: ");
                    Path inputFile = Paths.get("input/prim.csv");
                    Graph graph = fileInput(inputFile);
                    PrimAlg primAlg = new PrimAlg(graph);
                    ArrayList<Edge> mst = primAlg.primMST();
                    for (Edge e : mst) {
                        e.printEdge();
                    }
                    System.out.println("\n");
                    break;
                case 2:
                    System.out.print("Kruskal's MST: ");
                    inputFile = Paths.get("input/kruskal.csv");
                    graph = fileInput(inputFile);
                    KruskalAlg kruskalAlg = new KruskalAlg(graph);
                    mst = kruskalAlg.kruskalMST();
                    for (Edge e : mst) {
                        e.printEdge();
                    }
                    System.out.println("\n");
                    break;
                case 3:
                    System.out.println("Floyd-Warshall's");
                    inputFile = Paths.get("input/floyd.csv");
                    graph = fileInput(inputFile);
                    FloydAlg floydAlg = new FloydAlg(graph);
                    floydAlg.floydSP();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid choice.\n");
            }
        }
    }

   //reads in the file, builds the graph, and returns the graph
   private static Graph fileInput(Path inputFile){
        Graph graph = null;
        try(BufferedReader inputBuffer = Files.newBufferedReader(inputFile)) {
            String inputLine = null;
            String[] vertexArray = null;
            Integer[][] adjacencyMatrix = null;
            boolean loopedOnce = false;
            int i = 0;
            while((inputLine = inputBuffer.readLine()) != null) {
                String[] inputArray = inputLine.split(",");
                if(!loopedOnce) {
                    vertexArray = inputArray;
                    adjacencyMatrix = new Integer[vertexArray.length][vertexArray.length];
                    loopedOnce = true;
                } else {
                    Integer[] intArray = new Integer[vertexArray.length];
                    for(int k = 0; k < inputArray.length; k++) {
                        if(inputArray[k].equalsIgnoreCase("INF")||inputArray[k].equals("∞")) {
                            intArray[k] = Integer.MAX_VALUE;
                        } else {
                            intArray[k] = Integer.parseInt(inputArray[k]);
                        }
                    }
                    adjacencyMatrix[i] = intArray;
                    i++;
                }
            }
            graph = new Graph(vertexArray, adjacencyMatrix);
            graph.buildGraph();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return graph;
    }
}
