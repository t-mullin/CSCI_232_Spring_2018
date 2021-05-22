/**
 * Authors: Tristan Mullin
 * Date: 1/26/2018
 * Overview: This program read in a file called "input.txt" then builds a binary tree. Actions are performed on said tree
 * Note: This uses code from (Robert Lafore. 2002.Data Structures and Algorithms in Java(2 ed.).Sams, Indianapolis, IN, USA)
 */

package Lab2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Stack;

//////////////////////////////////////////////////////////////
class Node {
    public int iData;           // data item (key)
    public double dData;        // data item
    public Node leftChild;      // this Node's left child
    public Node rightChild;     // this Node's right child

    public void displayNode() { // display ourself
        System.out.print('{');
        System.out.print(iData);
        System.out.print(", ");
        System.out.print(dData);
        System.out.print("} ");
    }
} // end Class Node
////////////////////////////////////////////////////////////////

class Tree {
    private Node root;                 // first Node of Tree

    public Tree() {                    // constructor
        root = null;                   // no nodes in tree yet
    }


    public Node find(int key) {      // find node with given key
        Node current = root;         // (assumes non-empty tree)
        while (current.iData != key) {          // while no match
            if (key < current.iData) {          // go left?
                current =  current.leftChild;
            }
            else {                              // or go right?
                current =  current.rightChild;
            }
            if(current == null)                 // if no child
            {                                   // didn't find it
                return null;
            }
        }
        return current;                         // found it
    }  //end find()

    public Node findMin() {
        Node current  = root;
        Node min = root;
        while(current != null) {
            if(current.leftChild == null){
                min = current;
            }
            current = current.leftChild;
        }
        return min;
    }

    public Node findMax() {
        Node current = root;
        Node max = root;
        while(current != null) {
            if(current.rightChild == null) {
                max = current;
            }
            current = current.rightChild;
        }
        return max;
    }

    public void insert(int id, double dd) {
        Node newNode = new Node();    // make new Node
        newNode.iData = id;           // insert data
        newNode.dData = dd;
        newNode.leftChild = null;
        newNode.rightChild = null;
        if(root == null) {            // no node in root
            root = newNode;
        }
        else {                        // root occupied
            Node current = root;      // start at root
            Node parent;
            while (true) {            // exits internally
                parent = current;
                if (id < current.iData) {              // go left?
                    current =  current.leftChild;
                    if(current == null) {             // if the end of the line
                        parent.leftChild = newNode;   // insert on left
                        return;
                    }
                } //end if go left
                else {                                // or go right?
                    current =  current.rightChild;
                    if(current == null)               // if the end of the line
                    {                                 // insert on right
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    } // end insert()


    public boolean delete(int key) {             // delete node with given key
        Node current = root;		             // (assumes non-empty list)
        Node parent = root;
        boolean isLeftChild = true;

        while (current.iData != key) {           // search for Node
            parent = current;
            if (key < current.iData) {           // go left?
                isLeftChild = true;
                current =  current.leftChild;
            }
            else {                               // or go right?
                isLeftChild = false;
                current =  current.rightChild;
            }
            if(current == null) {                // end of the line,
                return false;                    // didn't find it
            }
        }
        //found the node to delete

        //if no children, simply delete it
        if (current.leftChild == null && current.rightChild == null) {
            if (current == root) {              // if root,
                root = null;                    // tree is empty
            }
            else if (isLeftChild) {
                parent.leftChild = null;        // disconnect
            }                                   // from parent
            else {
                parent.rightChild = null;
            }
        }
        //if no right child, replace with left subtree
        else if (current.rightChild == null) {
            if (current == root) {
                root = current.leftChild;
            }
            else if (isLeftChild) {
                parent.leftChild = current.leftChild;
            }
            else {
                parent.rightChild = current.leftChild;
            }
        }

        //if no left child, replace with right subtree
        else if (current.leftChild == null) {
            if (current == root) {
                root = current.rightChild;
            }
            else if (isLeftChild) {
                parent.leftChild = current.rightChild;
            }
            else {
                parent.rightChild = current.rightChild;
            }
        }

        else { // two children, so replace with inorder successor
            // get successor of node to delete (current)
            Node successor = getSuccessor(current);

            // connect parent of current to successor instead
            if (current == root) {
                root = successor;
            }
            else if (isLeftChild) {
                parent.leftChild = successor;
            }
            else {
                parent.rightChild = successor;
            }

            //connect successor to current's left child
            successor.leftChild = current.leftChild;
        } // end else two children
        // (successor cannot have a left child)
        return true;              // success
    }// end delete()


    //returns node with next-highest value after delNode
    //goes right child, then right child's left descendants
    private Node getSuccessor(Node delNode) {
        Node successorParent = delNode;
        Node successor = delNode;
        Node current = delNode.rightChild;        // go to the right child
        while (current != null) {                 // until no more
            successorParent = successor;          // left children
            successor = current;
            current = current.leftChild;
        }

        if (successor != delNode.rightChild) {    // if successor not right child,
            //make connections
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = delNode.rightChild;
        }
        return successor;
    }


    public void traverse(int traverseType) {
        switch (traverseType) {
            case 1:
                System.out.print("\nPreorder traversal: ");
                preOrder(root);
                break;
            case 2:
                System.out.print("\nInorder traversal: ");
                inOrder(root);
                break;
            case 3:
                System.out.print("\nPostorder traversal: ");
                postOrder(root);
                break;
            default:
                System.out.print("Invalid traversal type\n");
                break;
        }
        System.out.println();
    }


    private void preOrder(Node localRoot) {
        if (localRoot != null) {
            System.out.print(localRoot.iData + " ");
            preOrder(localRoot.leftChild);
            preOrder(localRoot.rightChild);
        }
    }


    private void inOrder(Node localRoot) {
        if (localRoot != null) {
            inOrder(localRoot.leftChild);
            System.out.print(localRoot.iData + " ");
            inOrder(localRoot.rightChild);
        }
    }


    private void postOrder(Node localRoot) {
        if (localRoot != null) {
            postOrder(localRoot.leftChild);
            postOrder(localRoot.rightChild);
            System.out.print(localRoot.iData + " ");
        }
    }


    public void displayTree() {
        Stack<Node> globalStack = new Stack<Node>();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println(
                ".................................................................");
        while (isRowEmpty==false) {
            Stack<Node> localStack = new Stack<Node>();
            isRowEmpty = true;

            for (int j = 0; j < nBlanks; j++) {
                System.out.print(' ');
            }

            while (globalStack.isEmpty()==false) {
                Node temp = (Node) globalStack.pop();
                if (temp != null) {
                    System.out.print(temp.iData);
                    localStack.push(temp.leftChild);
                    localStack.push(temp.rightChild);
                    if (temp.leftChild != null ||
                            temp.rightChild != null) {
                        isRowEmpty = false;
                    }
                }
                else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }

                for (int j = 0; j < nBlanks*2-2; j++) {
                    System.out.print(' ');
                }
            } // end while globalStack not empty
            System.out.println();
            nBlanks /= 2;
            while (localStack.isEmpty()==false) {
                globalStack.push(localStack.pop());
            } // end while isRowEmpty is false
            System.out.println(
                    ".................................................................");
        } // end displayTree()
    } // end class Tree
}
////////////////////////////////////////////////////////////////

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