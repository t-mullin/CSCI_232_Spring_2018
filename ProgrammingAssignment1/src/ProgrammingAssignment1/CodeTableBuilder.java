/*
 * Author: Tristan Mullin
 * Date: 2/5/2018
 * Overview: This program reads in a text file and encoded the contents using a Huffman tree and outputs the encoded contents to a file.
 * It also decodes the encoded content using a code table
 * References: (Robert Lafore. 2002. Data Structures and Algorithms in Java (2 ed.). Sams, Indianapolis, IN, US
 */
package ProgrammingAssignment1;

public class CodeTableBuilder {
    //private variables
    private Tree huffmanTree;
    private String[] codeTable = new String[127];

    //constructor for a new instance of the codetablebuilder class
    public CodeTableBuilder(Tree huffmanTree) {
        this.huffmanTree = huffmanTree;
    }

    //helper method
    public String[] buildCodeTable() {
        buildCodeTable(huffmanTree.getRoot(), "");
        return codeTable;
    }

    private void buildCodeTable(Node localRoot, String code) {
        //checks if the local root is a leaf
        if ((localRoot.getLeft() != null) && (localRoot.getRight() != null)) {
            //recurse through the possible paths down the huffman tree
            buildCodeTable(localRoot.getLeft(), (code+"0"));
            buildCodeTable(localRoot.getRight(), (code+"1"));
        } else {
            //found a leaf and adds the huffman code into the array indexed at the int value of the char found
            codeTable[(int) localRoot.getCharacter()] = code;
        }
    }
}
