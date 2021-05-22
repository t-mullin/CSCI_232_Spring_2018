/**
 * Authors: Tristan Mullin
 * Date: 2/27/2018
 * Overview: This program read in a file called "input.txt" then builds a AVL tree. Actions are performed on said tree
 * Note: This uses code from (Robert Lafore. 2002.Data Structures and Algorithms in Java(2 ed.).Sams, Indianapolis, IN, USA)
 */

package ProgrammingAssignment2;

import static java.lang.Integer.max;

public class Node {
    public int iData;           // data item (key)
    public double dData;        // data item
    public Node leftChild;      // this Node's left child
    public Node rightChild;     // this Node's right child
    private int height;

    public Node() {

    }

    //Constructor for the node class
    public Node(int iData, double dData) {
        this.iData = iData;
        this.dData = dData;
        this.leftChild = null;
        this.rightChild = null;
        this.height = 1;
    }

    //sets the height of the node
    public void setHeight() {
        int left;
        int right;
        if(leftChild == null) {
            left = 1;
        } else {
            left = leftChild.height;
        }
        if(rightChild == null) {
            right = 1;
        } else {
            right = rightChild.height;
        }
        if((leftChild == null) && (rightChild == null)) {
            height = 1;
        } else {
            //sets the height in relation to the deeper subtree
            height = 1 + max(left, right);
        }
    }

    //gets the height of the node
    public int getHeight() {
        return height;
    }

    public void displayNode() { // display ourself
        System.out.print('{');
        System.out.print(iData);
        System.out.print(", ");
        System.out.print(dData);
        System.out.print("} ");
    }
}
