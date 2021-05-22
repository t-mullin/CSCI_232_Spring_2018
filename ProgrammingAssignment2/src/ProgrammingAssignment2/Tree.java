/**
 * Authors: Tristan Mullin
 * Date: 2/27/2018
 * Overview: This program read in a file called "input.txt" then builds a AVL tree. Actions are performed on said tree
 * Note: This uses code from (Robert Lafore. 2002.Data Structures and Algorithms in Java(2 ed.).Sams, Indianapolis, IN, USA)
 */

package ProgrammingAssignment2;

import java.util.Random;
import java.util.Stack;

class Tree {
    private Node root;                 // first Node of Tree
    private Boolean isDeleted = false;

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

    //finds the min value in the tree by going left until reaching a null node
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

    //finds the max value in the tree by going right until reaching a null node
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

    //gets the balance factor of the current node
    public int getBF(Node current) {
        //null nodes will not contribute a weight to the balance factor
        if( current == null || ((current.leftChild == null) && (current.rightChild == null))) {
            return 0;
        } else if ((current.leftChild == null) && (current.rightChild != null)) {
            //if the subtree is weighted to the right due to a null child
            return current.rightChild.getHeight();
        } else if ((current.leftChild != null) && (current.rightChild == null)){
            //if the subtree is weighted to the left due to a null child
            return -current.leftChild.getHeight();
        }
        //if the current node has two non-null children then subtract the heights to get a balance factor
        return current.rightChild.getHeight() - current.leftChild.getHeight();
    }

    //selects the type of rotation performed
    public Node selectRotation(int balanceFactor, Node current, int key) {
        //height variables
        int leftHeight;
        int rightHeight;
        //checks if the balance factor is greater than a positive 1
        if((balanceFactor > 1)) {

            //sets the height variables for comparisons
            if(current.rightChild.leftChild == null ){
                leftHeight = 0;
            } else {
                leftHeight = current.rightChild.leftChild.getHeight();
            }
            if(current.rightChild.rightChild == null) {
                rightHeight = 0;
            } else {
                rightHeight = current.rightChild.rightChild.getHeight();
            }

            //picks at random a rotation if the height of the subtrees are the same
            if(rightHeight == leftHeight) {
                Random random = new Random();
                if(random.nextInt(1) == 0) {
                    return leftRotation(current);
                } else {
                    Node localRoot = rightRotation(current.rightChild);
                    current.rightChild = localRoot;
                    return leftRotation(current);
                }
            }

            if(key > (current.rightChild.iData) || (rightHeight > leftHeight)) {
                //if the key is greater than the value in the current right child
                //then it will need only one rotation to the left
                Node localRoot = leftRotation(current);
                return localRoot;
            } else {
                //if the key is less than the value in the current right child
                //then it will need a right rotation then a left rotation
                Node localRoot = rightRotation(current.rightChild);
                current.rightChild = localRoot;
                return leftRotation(current);
            }

        } else if (balanceFactor < -1) {
            //checks if the balance factor is less than -1
            //sets the height variables for comparisons
            if(current.leftChild.leftChild == null ){
                leftHeight = 0;
            } else {
                leftHeight = current.leftChild.leftChild.getHeight();
            }
            if(current.leftChild.rightChild == null) {
                rightHeight = 0;
            } else {
                rightHeight = current.leftChild.rightChild.getHeight();
            }

            //picks at random a rotation if the height of the subtrees are the same
            if(leftHeight == rightHeight) {
                Random random = new Random();
                if(random.nextInt(1) == 0) {
                    return rightRotation(current);
                } else {
                    Node localRoot = rightRotation(current.leftChild);
                    current.leftChild = localRoot;
                    return rightRotation(current);
                }
            }

            if((key < (current.leftChild.iData)) || (leftHeight > rightHeight)) {
                //if the key is less than the value in the current left child
                //then it will only need one rotation to the right
                return rightRotation(current);
            } else {
                //if the key is greater than the value in the current left child
                //then it will need a left rotation then a right rotation
                Node localRoot = leftRotation(current.leftChild);
                current.leftChild = localRoot;
                return rightRotation(current);
            }
        }
        return current;
    }

    //right rotations
    public Node rightRotation(Node current) {
        //sets the left child of the current node to the local root
        Node localRoot = current.leftChild;
        //replaces left child of the current node with the local root's right child
        current.leftChild = localRoot.rightChild;
        //sets current to be the local root's right child
        localRoot.rightChild = current;

        //updates the heights of the nodes
        if(localRoot.leftChild != null) {
            localRoot.leftChild.setHeight();
        }
        if(localRoot.rightChild != null) {
            localRoot.rightChild.setHeight();
        }
        if(localRoot != null) {
            localRoot.setHeight();
        }
        return localRoot;
    }

    //left rotations
    public Node leftRotation(Node current) {
        //sets the right child of the current node to the local root
        Node localRoot = current.rightChild;
        //replaces right child of the current node with the local root's left child
        current.rightChild = localRoot.leftChild;
        //sets current to be the local root't left child
        localRoot.leftChild = current;

        //updates the heights of the nodes
        if(localRoot.leftChild != null) {
            localRoot.leftChild.setHeight();
        }
        if(localRoot.rightChild != null) {
            localRoot.rightChild.setHeight();
        }
        if(localRoot != null) {
            localRoot.setHeight();
        }
        return localRoot;
    }

    //helper method for inserting a node
    public void insert(int id, double dd) {
        root = insert(root, id, dd);
    }

    //recursively inserts nodes into the tree
    private Node insert(Node current, int id, double dd) {
        //if the current node is null then insert a new node
        if(current == null) {
            return new Node(id, dd);
        }
        //recurse down the tree
        if(id < current.iData) {
            current.leftChild = insert(current.leftChild, id, dd);
        } else {
            current.rightChild = insert(current.rightChild, id, dd);
        }
        //updates the heights of the nodes
        current.setHeight();
        //checks if there is an unbalanced node
        if(Math.abs(getBF(current)) > 1) {
            //if there is then the tree will need to be re-balanced
            current = selectRotation(getBF(current), current, id);
        }
        return current;
    }

    //helper method for deleting a node
    public boolean delete(int key) {
        Node current;
        Boolean isLeftChild = true;
        isDeleted = false;
        current = delete(root, root, key, isLeftChild);
        if(current == null) {
            isDeleted = false;
        } else {
            isDeleted = true;
        }
        return isDeleted;
    }

    //recursively travel down the tree to find a node an remove it from the tree
    private Node delete(Node current, Node parent, int key, boolean isLeftChild) {
        //if the node was not in the tree then returns null
        if(current == null) {
            return null;
        }
        //if the node is found then remove the node
        if (key == current.iData) {
           delete(current, parent, isLeftChild);
        }
        //recurse down the tree
        if(key < current.iData) {
            current = delete(current.leftChild, current, key, true);
        } else {
           current = delete(current.rightChild, current, key, false);
        }
        //updates the heights
        parent.setHeight();
        //checks if the tree needs to be re-balanced
        if(((Math.abs(getBF(parent.leftChild)) > 1) || ((Math.abs(getBF(parent.rightChild)))) > 1) || (Math.abs(getBF(parent)) > 1)){
            //checks on what side of the subtree the imbalance is occurring
            if((Math.abs(getBF(parent.leftChild)) > Math.abs(getBF(parent.rightChild)))) {
                //the subtree is left heavy
                parent.leftChild = selectRotation(getBF(parent.leftChild), parent.leftChild, parent.leftChild.iData);
            } else if((Math.abs(getBF(parent.leftChild)) < Math.abs(getBF(parent.rightChild)))){
                //the subtree is right heavy
                parent.rightChild = selectRotation(getBF(parent.rightChild), parent.rightChild, parent.rightChild.iData);
            }
            //if the imbalance is occurring at the root
            if(parent == root) {
                if(getBF(parent) <= -2) {
                    root = selectRotation(getBF(parent), parent, parent.iData);
                } else if(getBF(parent) >= 2) {
                    root = selectRotation(getBF(parent), parent, parent.iData);
                }
            }
        }
        return parent;
    }

    //method to perform the deletion of the requested node
    private void delete(Node current, Node parent, Boolean isLeftChild) {
        //found the node to delete
        //if no children, simply delete it
        if (current.leftChild == null && current.rightChild == null) {
            if (current == root) {              // if root,
                root = null;                    // tree is empty
            } else if (isLeftChild) {
                parent.leftChild = null;        // disconnect from parent
            } else {
                parent.rightChild = null;
            }
        }//if no right child, replace with left subtree
        else if (current.rightChild == null) {
            if (current == root) {
                root = current.leftChild;
            } else if (isLeftChild) {
                parent.leftChild = current.leftChild;
            } else {
                parent.rightChild = current.leftChild;
            }
        }//if no left child, replace with right subtree
        else if (current.leftChild == null) {
            if (current == root) {
                root = current.rightChild;
            } else if (isLeftChild) {
                parent.leftChild = current.rightChild;
            } else {
                parent.rightChild = current.rightChild;
            }
        } else { // two children, so replace with inorder successor
            // get successor of node to delete (current)
            Node successor = getSuccessor(current);
            // connect parent of current to successor instead
            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.leftChild = successor;
            } else {
                parent.rightChild = successor;
            }
            //connect successor to current's left child
            successor.leftChild = current.leftChild;
        } // end else two children
        // (successor cannot have a left child)
        isDeleted = true;
    }

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
                    //System.out.print(getBF(temp));
                    //System.out.print(temp.getHeight());
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
