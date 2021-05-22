/*
 * Author: Tristan Mullin
 * Date: 2/5/2018
 * Overview: This program reads in a text file and encoded the contents using a Huffman tree and outputs the encoded contents to a file.
 * It also decodes the encoded content using a code table
 * References: (Robert Lafore. 2002. Data Structures and Algorithms in Java (2 ed.). Sams, Indianapolis, IN, US
 */

package ProgrammingAssignment1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.PriorityQueue;

public class Encode {

    //private variables
    private Path inputPath;
    private Path outputPath;
    private PriorityQueue<Node> frequencyTable;
    private Tree huffmanTree;
    private String[] codeTable;
    private String encodedMessage;

    //constructor for a new instance of the encode class
    public Encode(Path inputPath, Path outputPath) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }

    //creates the frequency table, huffman tree, and code table. Then encodes input message.
    //Prints the input message, frequency table, code table, and the encode message.
    public void encodeData() {
        frequencyTable = new FrequencyTableBuilder(inputPath).buildFrequencyTable();
        huffmanTree = new Tree(new HuffmanTreeBuilder(new FrequencyTableBuilder(inputPath).buildFrequencyTable()).buildTree());
        codeTable = new CodeTableBuilder(huffmanTree).buildCodeTable();
        encodeMessage();
        print();
    }

    //encodes the data and writes the encoded message into the output file
    private void encodeMessage() {
        encodedMessage = "";
        try(BufferedReader inputBuffer = Files.newBufferedReader(inputPath)) {
            String inputLine;
            while((inputLine = inputBuffer.readLine()) != null) {
                //walks the input message and builds the encode message
                for(int i = 0; i < inputLine.length(); i++) {
                    encodedMessage = encodedMessage + codeTable[inputLine.charAt(i)];
                }
                encodedMessage = encodedMessage + codeTable[10];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //writes to the output file
        try(BufferedWriter outputBuffer = Files.newBufferedWriter(outputPath)) {
            outputBuffer.write(encodedMessage, 0, encodedMessage.length());
            outputBuffer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //prints the contents of the input.txt file
    private void printInputMessage() {
        System.out.println("Printing the input message:");
        try(BufferedReader inputBuffer = Files.newBufferedReader(inputPath)) {
            String inputLine;
             while((inputLine = inputBuffer.readLine()) != null) {
                 System.out.print(inputLine+"\n");
             }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //prints the frequency table
    private void printFrequencyTable() {
        System.out.println("\nPrinting the frequency table:");
        while(!frequencyTable.isEmpty()) {
            frequencyTable.remove().printNode();
        }
    }

    //prints out the huffman code table
    private void printCodeTable() {
        System.out.println("\nPrinting the code table:");
        for(int i = 0; i < codeTable.length; i++) {
            if(codeTable[i] != null) {
                if(i == 10) {
                    System.out.println("\\n " + codeTable[i]);
                } else {
                    System.out.println(((char) i) + " " + codeTable[i]);
                }
            }
        }
    }

    //prints the encoded message
    private void printEncodedMessage() {
        System.out.print("\nPrinting the encoded message");
        System.out.print("\n" + encodedMessage + "\n");
        System.out.println();
    }

    //calls the four print methods above
    private void print() {
        printInputMessage();
        printFrequencyTable();
        printCodeTable();
        printEncodedMessage();
    }

    //returns the Huffman tree
    public Tree getHuffmanTree() {
        return huffmanTree;
    }
}
