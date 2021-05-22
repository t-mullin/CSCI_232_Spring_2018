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

public class Decode {
    //private variables
    private Path inputPath;
    private Tree huffmanTree;
    private char[] decodedMessageArray;

    //constructor for a new instance of the decode class
    public Decode(Path inputPath, Tree huffmanTree) {
        this.inputPath = inputPath;
        this.huffmanTree = huffmanTree;
    }

    //decodes the encoded message
    public void decodeData() {
        System.out.println("Printing the decoded message:");
        Node current = huffmanTree.getRoot();
        try (BufferedReader inputBuffer = Files.newBufferedReader(inputPath)) {
            String inputLine;
            //loops until there is nothing else to read in
            while((inputLine = inputBuffer.readLine()) != null) {
                decodedMessageArray = inputLine.toCharArray();
                int i = 0;
                //while loop to walk the encoded message character by character
                while(i < decodedMessageArray.length) {
                    //checks if the current node a leaf
                    if((current.getLeft() == null)&&(current.getRight() == null)) {
                        //if it is, then it has reached a character
                        System.out.print(current.getCharacter());
                        //returns to the root
                        current = huffmanTree.getRoot();
                    } else {
                        //moves down the tree. the path depends on if it is a 1 or 0
                        if(decodedMessageArray[i] == '0') {
                            current = current.getLeft();
                        } else {
                            current = current.getRight();
                        }
                        i++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
