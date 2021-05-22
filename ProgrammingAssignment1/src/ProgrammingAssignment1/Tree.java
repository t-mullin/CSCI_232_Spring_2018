/*
 * Author: Tristan Mullin
 * Date: 2/5/2018
 * Overview: This program reads in a text file and encoded the contents using a Huffman tree and outputs the encoded contents to a file.
 * It also decodes the encoded content using a code table
 * References: (Robert Lafore. 2002. Data Structures and Algorithms in Java (2 ed.). Sams, Indianapolis, IN, US
 */

package ProgrammingAssignment1;

import java.util.Stack;

public class Tree {
    private Node root;                 // first Node of Tree

    public Tree() {                    // constructor
        root = null;                   // no nodes in tree yet
    }

    //constructor for a new instance of the tree class
    public Tree(Node root) {
        this.root = root;
    }

    //returns the root of the tree
    public Node getRoot() {
        return root;
    }

    public void displayTree() {
        Stack<Node> globalStack = new Stack<Node>();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println(".................................................................");
        while(isRowEmpty==false) {
            Stack<Node> localStack = new Stack<Node>();
            isRowEmpty = true;
            for (int j = 0; j < nBlanks; j++) {
                System.out.print(' ');
            }
            while(globalStack.isEmpty()==false) {
                Node temp = (Node) globalStack.pop();
                if(temp != null) {
                    if(temp.getCharacter() == '\n') {
                        System.out.print("\\n");
                    } else {
                        System.out.print(temp.getCharacter());
                    }
                    localStack.push(temp.getLeft());
                    localStack.push(temp.getRight());
                    if (temp.getLeft() != null ||
                            temp.getRight() != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for(int j = 0; j < nBlanks*2-2; j++) {
                    System.out.print(' ');
                }
            } // end while globalStack not empty
            System.out.println();
            nBlanks /= 2;
            while(localStack.isEmpty()==false) {
                globalStack.push(localStack.pop());
            } // end while isRowEmpty is false
            System.out.println(
                    ".................................................................");
        } // end displayTree()
    } // end class Tree
}

