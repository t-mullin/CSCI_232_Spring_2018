/*
 * Author: Tristan Mullin
 * Date: 2/5/2018
 * Overview: This program reads in a text file and encoded the contents using a Huffman tree and outputs the encoded contents to a file.
 * It also decodes the encoded content using a code table
 * References: (Robert Lafore. 2002. Data Structures and Algorithms in Java (2 ed.). Sams, Indianapolis, IN, US
 */

package ProgrammingAssignment1;

public class Node implements Comparable<Node> {

    //private variables
    private int frequency;
    private char character;
    private Node left;
    private Node right;

    //constructor for making leaf nodes
    public Node(int frequency, char character) {
        this.frequency = frequency;
        this.character = character;
        this.left = null;
        this.right = null;
    }

    //constructor for making internal nodes
    public Node(int frequency) {
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }

    //sets the left child of the node
    public void setLeft(Node left) {
        this.left = left;
    }

    //sets the right chile of the node
    public void setRight(Node right) {
        this.right = right;
    }

    //returns the node's frequency
    public int getFrequency() {
        return frequency;
    }

    //returns the node's character
    public char getCharacter() {
        return character;
    }

    //gets the node's left child
    public Node getLeft() {
        return left;
    }

    //gets the node's right child
    public Node getRight() {
        return right;
    }

    //increments the node's frequency
    public void incFrequency() {
        frequency++;
    }

    //prints out the contents of the node
    public void printNode() {
        if(getCharacter() == '\n') {
            System.out.println("{ " + getFrequency() + " , " + "\\n" + " }");
        } else {
            System.out.println("{ " + getFrequency() + " , " + getCharacter() + " }");
        }
    }

    //implements the compareTo method so that the node can be used in a priority queue
    @Override
    public int compareTo(Node o) {
        return Integer.compare(frequency, o.getFrequency());
    }
}
