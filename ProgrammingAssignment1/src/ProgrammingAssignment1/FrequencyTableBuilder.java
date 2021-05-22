/*
 * Author: Tristan Mullin
 * Date: 2/5/2018
 * Overview: This program reads in a text file and encoded the contents using a Huffman tree and outputs the encoded contents to a file.
 * It also decodes the encoded content using a code table
 * References: (Robert Lafore. 2002. Data Structures and Algorithms in Java (2 ed.). Sams, Indianapolis, IN, US
 */

package ProgrammingAssignment1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class FrequencyTableBuilder {
    //private variables
    Path inputPath;

    //constructor for a new instance of the frequencytablebuilder class
    public FrequencyTableBuilder(Path inputPath) {
        this.inputPath = inputPath;
    }

    //builds the frequency table and output it as a priority queue
    public PriorityQueue<Node> buildFrequencyTable() {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>();
        try(BufferedReader inputBuffer = Files.newBufferedReader(inputPath)) {
            String message = null;
            String inputLine = null;
            ArrayList<Node> charList = new ArrayList<Node>();
            while((inputLine = inputBuffer.readLine()) != null) {
                message = inputLine+"\n";
                for(int i = 0; i < message.length(); i++) {
                    //checks if the ArrayList is empty
                    if(charList.isEmpty()) {
                        //adds the first node into the ArrayList
                        charList.add(new Node(1, message.charAt(i)));
                    } else {
                        boolean found = false;
                        //iterates through the ArrayList
                        for(int j = 0; j < charList.size(); j++) {
                            //checks to see if found is still false
                            if(!found) {
                                //checks to see if there is a character match
                                if (message.charAt(i) == charList.get(j).getCharacter()) {
                                    //increments the frequency counter
                                    charList.get(j).incFrequency();
                                    found = true;
                                }
                            }
                        }
                        //if the Node doesn't exist, make a new Node and add it to the ArrayList
                        if(!found) {
                            charList.add(new Node(1, message.charAt(i)));
                        }
                    }
                }
            }
            //add the Nodes to the priority queue
            for(int k = 0; k < charList.size(); k++) {
                priorityQueue.add(charList.get(k));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
     return priorityQueue;
    }
}
