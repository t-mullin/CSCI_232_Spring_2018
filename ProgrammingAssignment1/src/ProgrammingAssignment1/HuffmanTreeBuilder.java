/*
 * Author: Tristan Mullin
 * Date: 2/5/2018
 * Overview: This program reads in a text file and encoded the contents using a Huffman tree and outputs the encoded contents to a file.
 * It also decodes the encoded content using a code table
 * References: (Robert Lafore. 2002. Data Structures and Algorithms in Java (2 ed.). Sams, Indianapolis, IN, US
 */

package ProgrammingAssignment1;

import java.util.PriorityQueue;

public class HuffmanTreeBuilder {

    //private variables
    private PriorityQueue<Node> priorityQueue;

    //constructor for a new instance of the huffmantreebuilder class
    public HuffmanTreeBuilder(PriorityQueue<Node> priorityQueue) {
        this.priorityQueue = priorityQueue;
    }

    //builds the huffman tree by popping two items
    public Node buildTree() {
        Node tempLeft;
        Node tempRight;
        Node treeRoot = null;
        //loops until the queue is empty
        while(!priorityQueue.isEmpty()) {
            //sets the root as the last item in the queue
            if(priorityQueue.size() == 1){
                treeRoot = priorityQueue.remove();
            } else {
                //pops two nodes out the queue and creates a sub tree and puts the subtree into the queue
                tempLeft = priorityQueue.remove();
                tempRight = priorityQueue.remove();
                priorityQueue.add(buildSubtree(tempLeft, tempRight));
            }
        }
        return treeRoot;
    }

    //builds the subtrees for the buildTree method
    private Node buildSubtree(Node leftLeaf, Node rightLeaf) {
        //makes a internal node with the sum of the frequencies of the child nodes
        Node localRoot = new Node((leftLeaf.getFrequency() + rightLeaf.getFrequency()));
        localRoot.setLeft(leftLeaf);
        localRoot.setRight(rightLeaf);
        return localRoot;
    }
}
