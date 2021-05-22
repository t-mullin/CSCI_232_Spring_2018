/**
 * Authors: Tristan Mullin
 * Date: 2/27/2018
 * Overview: This program read in a file called "input.txt" then builds a AVL tree. Actions are performed on said tree
 * Note: This uses code from (Robert Lafore. 2002.Data Structures and Algorithms in Java(2 ed.).Sams, Indianapolis, IN, USA)
 */

package ProgrammingAssignment2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class TreeApp {

    public static void main(String[] args) throws IOException {
        int value;
        Tree theTree = new Tree();

        Path inputFile = Paths.get("././input/input.txt");

        try (BufferedReader inputBuffer = Files.newBufferedReader(inputFile)) {
            String inputLine = null;
            while((inputLine = inputBuffer.readLine()) != null) {
                String[] split = inputLine.split(" ");
                if(split[0].equalsIgnoreCase("insert")) {
                    String[] insertValues = split[1].split(",");
                    int i = 0;
                    int temp = 0;
                    while(i < insertValues.length) {
                        temp = Integer.parseInt(insertValues[i]);
                        theTree.insert(temp, temp+0.9);
                        //theTree.displayTree();
                        i++;
                    }
                    System.out.println("Inserting: " + split[1]);
                } else if(split[0].equalsIgnoreCase("find")) {
                    Node match;
                    System.out.print("Found: ");
                    match = theTree.find(Integer.parseInt(split[1]));
                    match.displayNode();
                    System.out.println();
                } else if(split[0].equalsIgnoreCase("delete")) {
                    if (theTree.delete(Integer.parseInt(split[1]))) {
                        System.out.println("Deleted: " + split[1]);
                    } else {
                        System.out.println(split[1] + " Was not found");
                    }
                } else if(split[0].equalsIgnoreCase("traverse")) {
                    theTree.traverse(Integer.parseInt(split[1]));
                } else if(split[0].equalsIgnoreCase("min")) {
                    Node min = theTree.findMin();
                    System.out.print("Min: ");
                    min.displayNode();
                    System.out.println();
                } else if(split[0].equalsIgnoreCase("max")) {
                    Node max = theTree.findMax();
                    System.out.print("Max: ");
                    max.displayNode();
                    System.out.println();
                } else if(split[0].equalsIgnoreCase("show")) {
                    theTree.displayTree();
                } else {

                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

    }  // end TreeApp class
}
////////////////////////////////////////////////////////////////