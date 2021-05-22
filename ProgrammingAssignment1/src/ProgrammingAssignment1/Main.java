/*
* Author: Tristan Mullin
* Date: 2/5/2018
* Overview: This program reads in a text file and encoded the contents using a Huffman tree and outputs the encoded contents to a file.
* It also decodes the encoded content using a code table
* References: (Robert Lafore. 2002. Data Structures and Algorithms in Java (2 ed.). Sams, Indianapolis, IN, US
*/

package ProgrammingAssignment1;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        //variables for the paths of the input and output files
        Path inputPath = Paths.get("././input/input.txt");
        Path outputPath = Paths.get("././output/output.txt");

        //encodes the data in the input file and outputs the encoded data into the output file
        Encode encode = new Encode(inputPath, outputPath);
        encode.encodeData();

        //decodes the data in the output file
        Decode decode = new Decode(outputPath, encode.getHuffmanTree());
        decode.decodeData();
    }
}
